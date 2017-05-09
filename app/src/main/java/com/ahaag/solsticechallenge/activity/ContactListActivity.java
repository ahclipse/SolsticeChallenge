package com.ahaag.solsticechallenge.activity;


import android.support.v4.app.Fragment;

import com.ahaag.solsticechallenge.fragment.ContactListFragment;

/**
 * Activity hosting ContactListFragment
 *
 * @author  Adam Haag
 * @version 1.0
 * @since   2017-05-7
 */
public class ContactListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ContactListFragment();
    }
}
