package com.bignerdranch.android.criminalintent;

public class ImageObj {
    private UUID crimeId;
    private Bitmap thumbnail;
    private Date date;
    private String path;
    private String facePath;

    public ImageObj(UUID id, Bitmap thumbnail){
        this.crimeId = id;
        this.thumbnail = thumbnail;
    }
    
}
