package com.kadiraltinok.flickrclient.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kadiraltinok.flickrclient.R;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class FullScreenActivity extends BaseActivity {
    private static final String EXTRA_DETAIL = "extra.detail";
    private String mImageUrl;
    private ProgressBar mProgress;

    public static Intent getIntent(BaseActivity activity, String url) {
        Intent intent = new Intent(activity, FullScreenActivity.class);
        intent.putExtra(EXTRA_DETAIL, url);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mImageUrl = extras.getString(EXTRA_DETAIL);
        }
        if (!TextUtils.isEmpty(mImageUrl)) {
            mProgress = (ProgressBar) findViewById(R.id.pb_full);
            ImageView ivPhoto = (ImageView) findViewById(R.id.iv_photo);
            Glide.with(getBaseContext())
                    .load((mImageUrl)).asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            mProgress.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            mProgress.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPhoto);
        }
    }

    @NonNull
    @Override
    public int getLayoutResId() {
        return R.layout.activity_fullscreen;
    }
}
