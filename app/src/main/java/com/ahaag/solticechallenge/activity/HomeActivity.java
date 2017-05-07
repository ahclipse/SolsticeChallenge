package com.ahaag.solticechallenge.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahaag.solticechallenge.R;
import com.ahaag.solticechallenge.fragment.ContactDetailFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.home_fragment_container);

        if (fragment == null) {
            fragment = new ContactDetailFragment();
            fm.beginTransaction().add(R.id.home_fragment_container, fragment).commit();
        }
    }
}
