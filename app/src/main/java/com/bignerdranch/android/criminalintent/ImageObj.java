package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;
import android.graphics.Bitmap;

public class ImageObj {
    private UUID crimeId;
    private Bitmap thumbnail;
    private Date date;
    private String path;
    private String facePath;

    public ImageObj(UUID id, Bitmap thumbnail){
        this.crimeId = id;
        this.thumbnail = thumbnail;
        date = new Date();
    }

    public UUID getCrimeId(){
        return this.crimeID;
    }

    public Bitmap getThumbnail(){
        return this.thumbnail;
    }

    public Date getDate(){
        return this.date
    }

    public

}
