package com.kadiraltinok.flickrclient.utils;

import android.content.Context;
import android.text.TextUtils;

import com.kadiraltinok.flickrclient.R;
import com.kadiraltinok.flickrclient.model.Photo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class Utils {
    /**
     * @param photo
     * @return formatted date from ms
     */
    public static String getFormattedDate(Photo photo) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        long milliSeconds = Long.parseLong(photo.getDateupload()) * 1000;
        System.out.println(milliSeconds);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return (formatter.format(calendar.getTime()));
    }

    /**
     * @param photo
     * @return first tag from photo
     */
    public static String getFirstTag(Photo photo) {
        if (!TextUtils.isEmpty(photo.getTags())) {
            return photo.getTags().split(" ")[0];
        }
        return "";
    }

    /**
     * create user image url
     *
     * @param photo
     * @return user image url
     */
    public static String getUserImageUrl(Photo photo) {
        StringBuilder builder = new StringBuilder();
        builder.append("http://farm")
                .append(photo.getIconfarm())
                .append(".staticflickr.com/")
                .append(photo.getIconserver())
                .append("/buddyicons/")
                .append(photo.getOwner())
                .append(".jpg");
        return builder.toString();
    }

    /**
     * @param context
     * @return calculated toolbar height
     */
    public static int getToolbarHeight(Context context) {
        int toolbarHeight = (int) context.getResources().getDimension(R.dimen.toolbar_height);
        return toolbarHeight;
    }
}
