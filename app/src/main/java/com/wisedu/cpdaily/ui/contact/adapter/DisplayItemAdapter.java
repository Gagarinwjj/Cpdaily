package com.wisedu.cpdaily.ui.contact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.model.DisplayItem;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动态菜单
 * Created by wjj on 2017/7/21 16:09.
 */

public class DisplayItemAdapter extends ItemClickAdapter<DisplayItemAdapter.ViewHolder> {
    private List<DisplayItem> displayItemList;

    public DisplayItemAdapter(List<DisplayItem> displayItemList) {
        this.displayItemList = displayItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_display,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAdapterBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(displayItemList.get(position).getName());
        holder.ivImg.setImageResource(displayItemList.get(position).getImgResId());
    }

    @Override
    public int getItemCount() {
        return displayItemList == null ? 0 : displayItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
