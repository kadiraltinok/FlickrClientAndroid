package com.kadiraltinok.flickrclient.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.kadiraltinok.flickrclient.R;
import com.kadiraltinok.flickrclient.fragment.ListFragment;
//Key:
//        0fd569c68eebef669019aeb7ce5cdf1b
//
//        Secret:
//        2eba90a3aa12a6a2

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    ListFragment.newInstance(ListFragment.FULL_MODE, null), ListFragment.TAG).commit();
        }
    }

    @NonNull
    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }


}
