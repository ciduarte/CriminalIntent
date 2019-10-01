package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;

import com.bignerdranch.android.criminalintent.database.ImageDBHelper;
import com.bignerdranch.android.criminalintent.database.ImageCursorWrapper;
import com.bignerdranch.android.criminalintent.database.ImageDbSchema;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ImageLab {
    private static ImageLab sImageLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ImageLab get(Context context) {
        if (sImageLab == null) {
            sImageLab = new ImageLab(context);
        }
        return sImageLab;
    }

    private ImageLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ImageDBHelper(mContext)
                .getWritableDatabase();
    }


    public void addImage(ImageObj c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(ImageDbSchema.ImageTable.NAME, null, values);
    }

    public List<ImageObj> getImages() {
        List<ImageObj> images = new ArrayList<>();

        ImageCursorWrapper cursor = queryImages(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            images.add(cursor.getImage());
            cursor.moveToNext();
        }
        cursor.close();

        return images;
    }

    public ImageObj getImage(UUID id) {
        ImageCursorWrapper cursor = queryImages(
                ImageDbSchema.ImageTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getImage();
        } finally {
            cursor.close();
        }
    }


    private static ContentValues getContentValues(ImageObj image) {
        ContentValues values = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.getThumbnail().compress(Bitmap.CompressFormat.PNG, 100, stream);
        values.put(ImageDbSchema.ImageTable.Cols.UUID, image.getCrimeId().toString());
        values.put(ImageDbSchema.ImageTable.Cols.THUMBNAIL, stream.toByteArray());
        values.put(ImageDbSchema.ImageTable.Cols.DATE, image.getDate().getTime());

        return values;
    }

    private ImageCursorWrapper queryImages(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ImageDbSchema.ImageTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new ImageCursorWrapper(cursor);
    }
}
