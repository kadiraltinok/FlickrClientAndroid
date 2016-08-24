package com.kadiraltinok.flickrclient.retrofit;


import com.kadiraltinok.flickrclient.model.ServiceResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public interface Services {
    @GET("/services/rest/?method=flickr.photos.getRecent&api_key=0fd569c68eebef669019aeb7ce5cdf1b&per_page=20&format=json&extras=url_l,media, owner_name, date_upload, tags,icon_server&sort=date-posted-asc&nojsoncallback=1")
    Call<ServiceResult> getTimeLine(@Query("page") int page);

    @GET("/services/rest/?method=flickr.photos.getRecent&api_key=0fd569c68eebef669019aeb7ce5cdf1b&per_page=20&format=json&extras=url_l,media, owner_name, date_upload, tags,icon_server&sort=date-posted-asc&nojsoncallback=1")
    Call<ServiceResult> getFilterByTag(@Query("page") int page, @Query("tags") String tag);
}
