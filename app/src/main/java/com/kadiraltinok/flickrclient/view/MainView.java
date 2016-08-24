package com.kadiraltinok.flickrclient.view;


import com.kadiraltinok.flickrclient.model.AdapterRow;

import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */
public interface MainView extends BaseView {
    void setItems(List<AdapterRow> list);

    void emptyMode();
}
