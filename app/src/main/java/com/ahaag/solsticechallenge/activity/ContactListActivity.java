package com.ahaag.solsticechallenge.activity;


import android.support.v4.app.Fragment;

import com.ahaag.solsticechallenge.fragment.ContactListFragment;

public class ContactListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ContactListFragment();
    }
}
