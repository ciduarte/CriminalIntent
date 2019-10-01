package com.bignerdranch.android.criminalintent;

import android.os.StrictMode;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Context;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public class CrimeGalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";

    public static CrimeGalleryFragment newInstance() {
        CrimeGalleryFragment fragment = new CrimeGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_gallery, container, false);
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
