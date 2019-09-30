package com.bignerdranch.android.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.criminalintent.database.ImageDbSchema.ImageTable;

public class ImageDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "ImageDBHelper";
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "imageDB.db";

    public ImageDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + ImageTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ImageTable.Cols.UUID + ", " +
                ImageTable.Cols.PATH + ", " +
                ImageTable.Cols.FACEPATH + ", " +
                ImageTable.Cols.DATE + ", " +
                ImageTable.Cols.THUMBNAIL +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
