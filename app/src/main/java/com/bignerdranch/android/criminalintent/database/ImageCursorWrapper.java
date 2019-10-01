package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bignerdranch.android.criminalintent.ImageObj;

import java.util.Date;
import java.util.UUID;

import com.bignerdranch.android.criminalintent.database.ImageDbSchema.ImageTable;

public class ImageCursorWrapper extends CursorWrapper            {
    public ImageCursorWrapper(Cursor cursor){super(cursor);}

    public ImageObj getImage() {
        String uuidString = getString(getColumnIndex(ImageDbSchema.ImageTable.Cols.UUID));
        String path = getString(getColumnIndex(ImageTable.Cols.PATH));
        long date = getLong(getColumnIndex(ImageDbSchema.ImageTable.Cols.DATE));
        String facePath = getString(getColumnIndex(ImageTable.Cols.FACEPATH));
        byte[] rawThumb = getBlob(getColumnIndex(ImageTable.Cols.THUMBNAIL));

        Bitmap thumbnail = BitmapFactory.decodeByteArray(rawThumb, 0, rawThumb.length);

        ImageObj image = new ImageObj(UUID.fromString(uuidString), thumbnail);
        image.setPath(path);
        image.setDate(new Date(date));
        image.setFacePath(facePath);

        return image;
    }
}
