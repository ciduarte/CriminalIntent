package com.bignerdranch.android.criminalintent;

import java.util.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageAdapter extends BaseAdapter {
    private ArrayList<Bitmap> images;
    private Context mContext;

    public ImageAdapter(Context c, ArrayList<Bitmap> images) {
        mContext = c;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setPadding(8, 8, 8, 8);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext.this, "Selected image", Toast.LENGTH_SHORT).show();
//                });
//            });
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(images.get(position));
        return imageView;
    }
}
