package com.kadiraltinok.flickrclient.view;

/**
 * Created by kadiraltinok on 21/08/16.
 */
public interface BaseView {
    void showProgress();

    void dismissProgress();

    void onDefaultAlert(String message);
}
