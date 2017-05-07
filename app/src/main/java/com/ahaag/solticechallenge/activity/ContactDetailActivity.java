package com.ahaag.solticechallenge.activity;

import android.support.v4.app.Fragment;

import com.ahaag.solticechallenge.fragment.ContactDetailFragment;

public class ContactDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ContactDetailFragment();
    }
}
