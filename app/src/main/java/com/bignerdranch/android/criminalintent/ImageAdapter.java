package com.bignerdranch.android.criminalintent;

import java.util.*;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;

public class ImageAdapter extends BaseAdapter {
    private ArrayList<Bitmap> images;
    private Context mContext;
    private boolean faceDetection;

    public ImageAdapter(Context c, ArrayList<Bitmap> images, boolean faceDetection) {
        mContext = c;
        this.images = images;
        this.faceDetection = faceDetection;
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
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        detectFace(images.get(position), imageView, faceDetection);
//        imageView.setImageBitmap(images.get(position)); //replace with face detection method
        return imageView;
    }

    // face detection method will go here
    private void detectFace(Bitmap bitmap, ImageView v, boolean faceDetectionChecked) {
        if (faceDetectionChecked) {

            final Paint rectPaint = new Paint();

            rectPaint.setStrokeWidth(20);
            rectPaint.setColor(Color.RED);
            rectPaint.setStyle(Paint.Style.STROKE);

            final Bitmap tempBitmap = Bitmap.createBitmap(inputBitmap.getWidth(),inputBitmap.getHeight(),Bitmap.Config.RGB_565);
            final Canvas canvas = new Canvas(tempBitMap);
            canvas.drawBitmap(inputBitmap,0,0,null);

            FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                    .setTrackingEnabled(false)
                    .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                    .build();

            // Create a frame from the bitmap and run face detection on the frame.
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<Face> faces = detector.detect(frame);

            if ((bitmap != null) && (faces != null)) {
                double scale = drawBitmap(canvas);
                drawFaceAnnotations(canvas, scale);
                v.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap))
            }

        } else {
            v.setImageBitmap(bitmap);
        }
//        if (faceDetectionChecked) {
//            Toast.makeText(mContext, "True", Toast.LENGTH_SHORT).show();
//        }
    }

    private double drawBitmap(Canvas canvas) {
        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        double scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight);

        Rect destBounds = new Rect(0, 0, (int)(imageWidth * scale), (int)(imageHeight * scale));
        canvas.drawBitmap(mBitmap, null, destBounds, null);
        return scale;
    }

    private void drawFaceAnnotations(Canvas canvas, double scale) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        for (int i = 0; i < mFaces.size(); ++i) {
            Face face = mFaces.valueAt(i);
            for (Landmark landmark : face.getLandmarks()) {
                int cx = (int) (landmark.getPosition().x * scale);
                int cy = (int) (landmark.getPosition().y * scale);
                canvas.drawCircle(cx, cy, 10, paint);
            }
        }
    }
}
