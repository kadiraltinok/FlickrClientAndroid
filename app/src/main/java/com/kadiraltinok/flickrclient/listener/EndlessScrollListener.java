package com.kadiraltinok.flickrclient.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.kadiraltinok.flickrclient.utils.Utils;


/**
 * Created by kadiraltinok on 21/08/16.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;
    private int[] mFirstVisibleItems;
    private int mToolbarOffset = 0;
    private int mToolbarHeight;
    private int mTotalScrolledDistance;
    private int mPpreviousTotal = 0;
    private boolean mLoading = true;
    private int mVisibleThreshold = 8;
    private StaggeredGridLayoutManager mLayoutManager;

    /**
     * if load more required trigger load more
     *
     * @param context
     * @param layoutManager
     */
    public EndlessScrollListener(Context context, StaggeredGridLayoutManager layoutManager) {
        mToolbarHeight = Utils.getToolbarHeight(context);
        this.mLayoutManager = layoutManager;
    }

    public void setPreviousTotal() {
        mPpreviousTotal = 0;
        mTotalItemCount = 0;
        mLoading = true;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();

        if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            mToolbarOffset += dy;
        }
        if (mTotalScrolledDistance < 0) {
            mTotalScrolledDistance = 0;
        } else {
            mTotalScrolledDistance += dy;
        }
        // for load more
        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLayoutManager.getItemCount();
        mFirstVisibleItems = mLayoutManager.findLastVisibleItemPositions(null);
        mFirstVisibleItem = getLastVisibleItem(mFirstVisibleItems);
        if (mLoading) {
            if (mTotalItemCount > mPpreviousTotal) {
                mLoading = false;
                mPpreviousTotal = mTotalItemCount;
            }
        }
        if (!mLoading && (mTotalItemCount - mVisibleItemCount) <= (mFirstVisibleItem + mVisibleThreshold)) {
            // End has been reached

            Log.i("...", "end called");

            // Do something

            mLoading = true;
            onLoadMore();
        }
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    public abstract void onLoadMore();
}
