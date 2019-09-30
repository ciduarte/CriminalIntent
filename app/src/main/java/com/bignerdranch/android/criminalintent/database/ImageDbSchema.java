package com.bignerdranch.android.criminalintent.database;

public class ImageDbSchema {
    public static final class ImageTable{
        public static final String NAME = "images";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PATH = "path";
            public static final String FACEPATH = "facePath";
            public static final String THUMBNAIL = "thumbnail";
            public static final String Data = "date"
        }
    }
}
