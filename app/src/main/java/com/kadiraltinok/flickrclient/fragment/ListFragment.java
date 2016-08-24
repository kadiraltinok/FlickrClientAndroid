package com.kadiraltinok.flickrclient.fragment;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.kadiraltinok.flickrclient.R;
import com.kadiraltinok.flickrclient.activity.BaseActivity;
import com.kadiraltinok.flickrclient.adapter.TimeLineRecyclerAdapter;
import com.kadiraltinok.flickrclient.listener.EndlessScrollListener;
import com.kadiraltinok.flickrclient.model.AdapterRow;
import com.kadiraltinok.flickrclient.presenter.MainPresenter;
import com.kadiraltinok.flickrclient.presenter.MainPresenterImpl;
import com.kadiraltinok.flickrclient.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class ListFragment extends BaseFragment implements TimeLineRecyclerAdapter.RowClickListener,
        SwipeRefreshLayout.OnRefreshListener, MainView {
    public static String TAG = ListFragment.class.getSimpleName();

    public static final int FULL_MODE = 1;
    public static final int FILTER_MODE = 2;
    public static final String EXTRA_MODE = "extra.mode";
    public static final String EXTRA_TAG = "extra.tag";

    private List<AdapterRow> mList;
    private TimeLineRecyclerAdapter mAdapter;
    private MainPresenter mPresenter;
    private int mPage = 1;
    private EndlessScrollListener mScrollListener;
    private SwipeRefreshLayout mRefreshLayout;
    private int mMode;
    private String mTag;

    @IntDef({FILTER_MODE, FULL_MODE})
    public @interface mode {
    }

    public static ListFragment newInstance(@mode int mode, String tag) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_MODE, mode);
        bundle.putString(EXTRA_TAG, tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mMode = bundle.getInt(EXTRA_MODE, FULL_MODE);
            mTag = bundle.getString(EXTRA_TAG, "");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();

        mPresenter = new MainPresenterImpl(this);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sf_main);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new TimeLineRecyclerAdapter(getContext(), mList, this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_main);


        //if orientation is portrait spancount=1 else landscape spancount=2
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (getResources().getInteger(R.integer.main_span_count), StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mScrollListener = new EndlessScrollListener(getContext(), layoutManager) {
            @Override
            public void onLoadMore() {
                mPage++;
                Log.d("kadir", "load more = " + mPage);
                mPresenter.getTimeLine(mPage);
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);

        switch (mMode) {
            case FULL_MODE:
                mPresenter.getTimeLine(mPage);
                break;
            case FILTER_MODE:
                if (!TextUtils.isEmpty(mTag)) {
                    mPresenter.getFilterByTag(mPage, mTag);
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mTag))
            ((BaseActivity) getActivity()).getSupportActionBar().setTitle(mTag);
    }

    @NonNull
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_list;
    }

    @Nullable
    @Override
    public int getToolbar() {
        return R.id.toolbar;
    }

    @Override
    public void onItemClickListener(int position, AdapterRow item) {
        mPresenter.onItemClicked((BaseActivity) getActivity(), position, item);
    }

    @Override
    public void onTagClickListener(int position, AdapterRow item) {
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fl_container,
                ListFragment.newInstance(ListFragment.FILTER_MODE, item.getTag()), ListFragment.TAG).addToBackStack(null).commit();
    }

    @Override
    public void onRefresh() {
        mScrollListener.setPreviousTotal();
        mPage = 1;
        mList.clear();
        mPresenter.getTimeLine(mPage);
    }

    @Override
    public void setItems(List<AdapterRow> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void emptyMode() {
        //service result ok but service result mList empty
    }

    @Override
    public void showProgress() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissProgress() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDefaultAlert(String message) {
        // service result faile
    }
}
