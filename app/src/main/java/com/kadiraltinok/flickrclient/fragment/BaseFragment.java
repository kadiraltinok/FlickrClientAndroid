package com.kadiraltinok.flickrclient.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kadiraltinok.flickrclient.activity.BaseActivity;

/**
 * Created by kadiraltinok on 21/08/16.
 */
public abstract class BaseFragment extends Fragment {
    @NonNull
    @LayoutRes
    public abstract int getLayoutResId();

    @Nullable
    @IdRes
    public abstract int getToolbar();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutResId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar toolbar = (Toolbar) getView().findViewById(getToolbar());
        if (toolbar != null) {
            ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        }

    }
}
