package com.ahaag.solsticechallenge.activity;

import android.support.v4.app.Fragment;

import com.ahaag.solsticechallenge.fragment.ContactDetailFragment;

public class ContactDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ContactDetailFragment();
    }
}
