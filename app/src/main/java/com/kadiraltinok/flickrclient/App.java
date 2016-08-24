package com.kadiraltinok.flickrclient;

import android.app.Application;

import com.kadiraltinok.flickrclient.retrofit.ApiProvider;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class App extends Application {
    public static ApiProvider apiProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        apiProvider = new ApiProvider();
    }
}
