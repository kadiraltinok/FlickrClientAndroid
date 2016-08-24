package com.kadiraltinok.flickrclient.interactor;


import com.kadiraltinok.flickrclient.App;
import com.kadiraltinok.flickrclient.listener.MainPageListener;
import com.kadiraltinok.flickrclient.model.ServiceResult;
import com.kadiraltinok.flickrclient.presenter.MainPresenterImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class MainInteractorImpl implements MainInteractor {
    @Override
    public void serviceTimeLine(int page, final MainPageListener listener) {
        Call<ServiceResult> call = App.apiProvider.getApi().getServices().getTimeLine(page);
        call.enqueue(new Callback<ServiceResult>() {
            @Override
            public void onResponse(Call<ServiceResult> call, Response<ServiceResult> response) {
                if (response != null && response.body() != null && response.body().getPhotos() != null &&
                        response.body().getPhotos().getPhoto().size() > 0) {
                    listener.onFinished(response.body().getPhotos().getPhoto());
                } else {
                    listener.empty();
                }
            }

            @Override
            public void onFailure(Call<ServiceResult> call, Throwable t) {
                listener.onDefaultError(t.getMessage());
            }
        });
    }

    @Override
    public void serviceFilterByTag(int page, final String tag, final MainPresenterImpl listener) {
        Call<ServiceResult> call = App.apiProvider.getApi().getServices().getFilterByTag(page, tag);
        call.enqueue(new Callback<ServiceResult>() {
            @Override
            public void onResponse(Call<ServiceResult> call, Response<ServiceResult> response) {
                if (response != null && response.body() != null && response.body().getPhotos() != null && response.body()
                        .getPhotos().getPhoto()
                        .size() > 0) {
                    listener.onFinishedFilterByTag(response.body().getPhotos().getPhoto(), tag);
                } else {
                    listener.empty();
                }
            }

            @Override
            public void onFailure(Call<ServiceResult> call, Throwable t) {
                listener.onDefaultError(t.getMessage());
            }
        });
    }
}
