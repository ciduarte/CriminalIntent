package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Crime;

import java.util.Date;
import java.util.UUID;

import com.bignerdranch.android.criminalintent.database.ImageDbSchema.ImageTable;

public class ImageCursorWrapper extends CursorWrapper {
    public ImageCursorWrapper(Cursor cursor){super(cursor);}

    //public
}
