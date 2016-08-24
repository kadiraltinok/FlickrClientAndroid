package com.kadiraltinok.flickrclient.presenter;

import android.app.Activity;

import com.kadiraltinok.flickrclient.activity.BaseActivity;
import com.kadiraltinok.flickrclient.model.AdapterRow;
import com.kadiraltinok.flickrclient.model.Photo;


/**
 * Created by kadiraltinok on 21/08/16.
 */

public interface MainPresenter {
    void onItemClicked(BaseActivity activity, int position, AdapterRow photo);

    void getTimeLine(int page);
    void getFilterByTag(int page,String tag);
}
