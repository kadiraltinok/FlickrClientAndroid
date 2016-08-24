package com.kadiraltinok.flickrclient.presenter;

import android.content.Intent;

import com.kadiraltinok.flickrclient.activity.BaseActivity;
import com.kadiraltinok.flickrclient.activity.FullScreenActivity;
import com.kadiraltinok.flickrclient.constant.AppConstant;
import com.kadiraltinok.flickrclient.interactor.MainInteractor;
import com.kadiraltinok.flickrclient.interactor.MainInteractorImpl;
import com.kadiraltinok.flickrclient.listener.MainPageListener;
import com.kadiraltinok.flickrclient.model.AdapterRow;
import com.kadiraltinok.flickrclient.model.Photo;
import com.kadiraltinok.flickrclient.utils.Utils;
import com.kadiraltinok.flickrclient.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class MainPresenterImpl implements MainPresenter, MainPageListener {
    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mView = mainView;
        mInteractor = new MainInteractorImpl();
    }

    @Override
    public void onItemClicked(BaseActivity activity, int position, AdapterRow photo) {
        //go to detail
        Intent intent = FullScreenActivity.getIntent(activity, photo.getImageUrl());
        activity.startActivity(intent);
    }

    @Override
    public void getTimeLine(int page) {
        if (mView != null) {
            mView.showProgress();
        }
        mInteractor.serviceTimeLine(page, this);
    }

    @Override
    public void getFilterByTag(int page, String tag) {
        if (mView != null) {
            mView.showProgress();
        }
        mInteractor.serviceFilterByTag(page, tag, this);
    }

    @Override
    public void onFinished(List<Photo> list) {
        if (mView != null) {
            List<AdapterRow> tempList = new ArrayList<>();
            for (Photo photo : list) {
                if (photo.getMedia().equals(AppConstant.MEDIA_TYPE))
                    tempList.add(new AdapterRow(Utils.getUserImageUrl(photo), photo.getOwnername(), photo.getImageUrl(), Utils.getFormattedDate(photo), Utils.getFirstTag(photo)));
            }
            mView.setItems(tempList);
            mView.dismissProgress();
        }
    }

    @Override
    public void onFinishedFilterByTag(List<Photo> list, String tag) {
        if (mView != null) {
            List<AdapterRow> tempList = new ArrayList<>();
            for (Photo photo : list) {
                if (photo.getMedia().equals(AppConstant.MEDIA_TYPE))
                    tempList.add(new AdapterRow(Utils.getUserImageUrl(photo), photo.getOwnername(), photo.getImageUrl(), Utils.getFormattedDate(photo), tag));
            }
            mView.setItems(tempList);
            mView.dismissProgress();
        }
    }

    @Override
    public void empty() {
        if (mView != null) {
            mView.dismissProgress();
            mView.emptyMode();
        }
    }

    @Override
    public void onDefaultError(String message) {
        if (mView != null) {
            mView.onDefaultAlert(message);
        }
    }
}
