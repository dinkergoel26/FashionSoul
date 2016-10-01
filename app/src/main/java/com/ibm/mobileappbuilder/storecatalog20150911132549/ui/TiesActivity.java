

package com.ibm.mobileappbuilder.storecatalog20150911132549.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.storecatalog20150911132549.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * TiesActivity list activity
 */
public class TiesActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.tiesActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return TiesFragment.class;
    }

}

