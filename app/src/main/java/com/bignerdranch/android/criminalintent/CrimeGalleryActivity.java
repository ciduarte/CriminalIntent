package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class CrimeGalleryActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId, boolean detectionChecked) {
        Intent intent = new Intent(packageContext, CrimeGalleryActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra("FACE_DETECT", detectionChecked);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        boolean faceDetection = (boolean) getIntent().getExtras().getBoolean("FACE_DETECT");

        FragmentManager manager = getSupportFragmentManager();
        CrimeGalleryFragment fragment = CrimeGalleryFragment.newInstance(crimeId, faceDetection);
        manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }
}
