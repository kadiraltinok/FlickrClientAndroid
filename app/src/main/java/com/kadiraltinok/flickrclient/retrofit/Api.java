package com.kadiraltinok.flickrclient.retrofit;

import com.kadiraltinok.flickrclient.constant.AppConstant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class Api {
    private Services mService;

    public Api() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mService = retrofit.create(Services.class);
    }

    public Services getServices() {
        return mService;
    }
}
