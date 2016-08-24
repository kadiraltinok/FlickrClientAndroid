package com.kadiraltinok.flickrclient.model;

/**
 * Created by kadiraltinok on 21/08/16.
 */


public class AdapterRow {
    private String mUserImageUrl;
    private String mUserName;
    private String mImageUrl;
    private String mDate;
    private String mTag;

    public AdapterRow(String userImageUrl, String userName, String imageUrl, String date, String tag) {
        this.mUserImageUrl = userImageUrl;
        this.mUserName = userName;
        this.mImageUrl = imageUrl;
        this.mDate = date;
        this.mTag = tag;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = mDate;
    }

    public String getUserImageUrl() {
        return mUserImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.mUserImageUrl = userImageUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }
}
