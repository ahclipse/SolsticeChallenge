package com.ahaag.solsticechallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ahaag.solsticechallenge.fragment.ContactDetailFragment;

/**
 * Creates ContactDetailFragment and passes along work phone number under the
 * assumption this will be used as an unique identifier to determine which contact
 * to show
 *
 * @author  Adam Haag
 * @version 1.0
 * @since   2017-05-8
 */
public class ContactDetailActivity extends SingleFragmentActivity {

    private static final String INTENT_EXTRA_WORK_PHONE_NUMBER =
            "com.ahaag.solsticechallenge.android.phone.work";

    public static Intent newIntent(Context packageContext, String workPhone) {
        Intent intent = new Intent(packageContext, ContactDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_WORK_PHONE_NUMBER, workPhone);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        String workPhone = getIntent().getStringExtra(INTENT_EXTRA_WORK_PHONE_NUMBER);
        return ContactDetailFragment.newInstance(workPhone);
    }
}
