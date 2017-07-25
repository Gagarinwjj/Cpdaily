package com.wisedu.cpdaily.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 实现item点击监听功能
 */
public abstract class ItemClickAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView
        .Adapter<VH>
        implements View.OnClickListener {
    OnItemClickListener onItemClickListener;

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onAdapterBindViewHolder(holder, position);
        if (onItemClickListener != null) {
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
        }
    }

    public abstract void onAdapterBindViewHolder(VH holder, int position);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public interface OnItemClickListener {//内部监听,接口外发

        void onItemClick(View view, int position);
    }
}