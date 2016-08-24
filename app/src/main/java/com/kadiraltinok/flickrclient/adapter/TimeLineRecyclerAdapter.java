package com.kadiraltinok.flickrclient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kadiraltinok.flickrclient.R;
import com.kadiraltinok.flickrclient.model.AdapterRow;

import java.util.List;

/**
 * Created by kadiraltinok on 21/08/16.
 */

public class TimeLineRecyclerAdapter extends RecyclerView.Adapter<TimeLineRecyclerAdapter.Holder> {
    private Context mContext;
    private List<AdapterRow> mList;
    private RowClickListener mListener;

    public TimeLineRecyclerAdapter(Context context, List<AdapterRow> list, RowClickListener callback) {
        this.mContext = context;
        this.mList = list;
        this.mListener = callback;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.row_time_line, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        AdapterRow item = mList.get(position);
        if (item != null) {
            holder.tvUserName.setText(item.getUserName());
            holder.tvDate.setText(item.getDate());

            if (!TextUtils.isEmpty(item.getTag())) {
                holder.tvTag.setVisibility(View.VISIBLE);
                holder.tvTag.setText(item.getTag());
            } else {
                holder.tvTag.setVisibility(View.GONE);
            }

            holder.pbImage.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load((item.getImageUrl())).asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            holder.pbImage.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.pbImage.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivPhoto);

            Glide.with(mContext).load(item.getUserImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivUserPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RowClickListener {
        void onItemClickListener(int position, AdapterRow item);

        void onTagClickListener(int position, AdapterRow item);
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivPhoto;
        private ImageView ivUserPhoto;
        private TextView tvUserName;
        private TextView tvDate;
        private ProgressBar pbImage;
        private TextView tvTag;

        public Holder(View view) {
            super(view);
            ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
            ivUserPhoto = (ImageView) view.findViewById(R.id.iv_user_photo);
            tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            pbImage = (ProgressBar) view.findViewById(R.id.pb_row);
            tvTag = (TextView) view.findViewById(R.id.tvTag);
            ivPhoto.setOnClickListener(this);
            tvTag.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.iv_photo:
                    mListener.onItemClickListener(position, mList.get(position));
                    break;
                case R.id.tvTag:
                    mListener.onTagClickListener(position, mList.get(position));
                    break;
            }
        }
    }
}

