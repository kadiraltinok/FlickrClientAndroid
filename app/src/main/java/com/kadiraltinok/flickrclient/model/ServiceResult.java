package com.kadiraltinok.flickrclient.model;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class ServiceResult {

    private Photos photos;

    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
