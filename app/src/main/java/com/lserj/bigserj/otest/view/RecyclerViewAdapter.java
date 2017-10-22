package com.lserj.bigserj.otest.view;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lserj.bigserj.otest.R;
import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    private ArrayList<JsonPlaceHolder> items;
    private OnItemClickListener listener;
    private Context context;

    public RecyclerViewAdapter(ArrayList<JsonPlaceHolder> items) {
        this.items = items;
    }

    public void swap(List<JsonPlaceHolder> newItems) {

        if (items != null && items.size() >= 0)
            items.clear();
        if (newItems.size() != 0)
            items.addAll(newItems);
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycle_view, parent, false);
        Log.i("AAA", "onCreateViewHolder()");
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.i("AAA", "onBindViewHolder() position = " + position);

        final ImageView imageView = holder.imageView;
        final ProgressBar progressView = holder.progressBar;

        Glide.with(context)
                .load(items.get(position).getPhotos().getThumb())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);


        holder.textView.setText(items.get(position).getName());

        final int itemCount = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onItemClick(itemCount);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        ProgressBar progressBar;


        public Holder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageItem);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            textView = (TextView) itemView.findViewById(R.id.nameItem);
        }

    }


    interface OnItemClickListener {
        void onItemClick(Integer item);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
