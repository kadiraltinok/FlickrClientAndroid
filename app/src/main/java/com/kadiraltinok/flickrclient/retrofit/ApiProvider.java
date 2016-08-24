package com.kadiraltinok.flickrclient.retrofit;

/**
 * Created by kadiraltinok on 21/08/16.
 */
public class ApiProvider {
    private Api mApi;

    public synchronized Api getApi() {
        if (mApi == null) {
            mApi = new Api();
        }
        return mApi;
    }
}
