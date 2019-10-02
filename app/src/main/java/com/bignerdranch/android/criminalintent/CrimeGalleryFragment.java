package com.bignerdranch.android.criminalintent;

import java.util.*;
import android.os.StrictMode;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.BaseAdapter;
import android.content.Context;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class CrimeGalleryFragment extends Fragment {

    private UUID crimeId;
    private boolean faceDetection;
    private static final String TAG = "GalleryFragment";
    private ArrayList<Bitmap> crimePhotos = new ArrayList<Bitmap>();
    public Integer[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image11,
            R.drawable.image12,
            R.drawable.image13,
            R.drawable.image14,
            R.drawable.image15,
            R.drawable.image16,
            R.drawable.image17,
            R.drawable.image18,
            R.drawable.image19,
            R.drawable.image20
    };

    public static CrimeGalleryFragment newInstance(UUID crimeId, boolean faceDetection) {
        CrimeGalleryFragment fragment = new CrimeGalleryFragment();
        crimeId = crimeId;
        faceDetection = faceDetection;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i=0; i<images.length; i++) {
            final Bitmap myBitmap = BitmapFactory.decodeResource(getActivity().getApplicationContext().getResources(),images[i]);
            crimePhotos.add(myBitmap);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_gallery, container, false);

        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(getActivity().getApplicationContext(), crimePhotos, faceDetection));

        if (faceDetection) {
            Toast.makeText(getActivity().getApplicationContext(), "True", Toast.LENGTH_LONG).show();
        }

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onStart();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        String greeting = (savedInstanceState != null) ? savedInstanceState.getString("greeting") : null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("greeting", "Hello");
    }
}
