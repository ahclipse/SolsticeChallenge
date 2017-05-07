package com.ahaag.solticechallenge.activity;


import android.support.v4.app.Fragment;

import com.ahaag.solticechallenge.fragment.ContactListFragment;

public class ContactListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ContactListFragment();
    }
}
