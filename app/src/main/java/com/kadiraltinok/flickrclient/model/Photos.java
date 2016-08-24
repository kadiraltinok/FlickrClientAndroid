package com.kadiraltinok.flickrclient.model;

import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class Photos {
    private int total;

    private int page;

    private int pages;

    private List<Photo> photo;

    private int perpage;

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }
}
