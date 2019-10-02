package com.bignerdranch.android.criminalintent;

import java.util.*;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;


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
        } else {
            imageView = (ImageView) convertView;
        }
        detectFace(images.get(position), imageView, faceDetection);
        return imageView;
    }

    public void detectFace(Bitmap bitmap, ImageView v, boolean faceDetectionChecked) {
        if (faceDetectionChecked) {

            final Bitmap tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
            final Canvas canvas = new Canvas(tempBitmap);
            canvas.drawBitmap(bitmap, 0, 0, null);

            FaceDetector detector = new FaceDetector.Builder(mContext)
                    .setTrackingEnabled(false)
                    .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                    .build();

            // Create a frame from the bitmap and run face detection on the frame.
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<Face> faces = detector.detect(frame);

            if ((bitmap != null) && (faces != null)) {
                double scale = drawBitmap(canvas, bitmap, faces);
                drawFaceAnnotations(canvas, scale, faces);
                v.setImageDrawable(new BitmapDrawable(mContext.getResources(), tempBitmap));
            }

            detector.release();

        } else {
            v.setImageBitmap(bitmap);
        }
    }

    private double drawBitmap(Canvas canvas, Bitmap mBitmap, SparseArray<Face> mFaces) {
        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        double scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight);

        Rect destBounds = new Rect(0, 0, (int)(imageWidth * scale), (int)(imageHeight * scale));
        canvas.drawBitmap(mBitmap, null, destBounds, null);
        return scale;
    }

    private void drawFaceAnnotations(Canvas canvas, double scale, SparseArray<Face> mFaces) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        for (int i = 0; i < mFaces.size(); ++i) {
            Face face = mFaces.valueAt(i);
            float x1=face.getPosition().x;
            float y1=face.getPosition().y;
            float x2=x1+face.getWidth();
            float y2=y1+face.getHeight();
            RectF rectF = new RectF(x1,y1,x2,y2);
            canvas.drawRoundRect(rectF,2,2,paint);
        }
    }
}
