
package com.kadiraltinok.flickrclient.listener;


import com.kadiraltinok.flickrclient.model.Photo;

import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public interface MainPageListener {
    void onFinished(List<Photo> list);

    void onFinishedFilterByTag(List<Photo> list, String tag);

    void empty();

    void onDefaultError(String message);
}
