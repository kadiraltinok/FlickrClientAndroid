package com.kadiraltinok.flickrclient.interactor;


import com.kadiraltinok.flickrclient.listener.MainPageListener;
import com.kadiraltinok.flickrclient.presenter.MainPresenterImpl;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public interface MainInteractor {
    void serviceTimeLine(int page, MainPageListener listener);

    void serviceFilterByTag(int page, String tag, MainPresenterImpl listener);
}
