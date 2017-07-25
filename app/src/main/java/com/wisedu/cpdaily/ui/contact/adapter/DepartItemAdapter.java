package com.wisedu.cpdaily.ui.contact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 教职工部门
 * Created by wjj on 2017/7/21 16:09.
 */

public class DepartItemAdapter extends ItemClickAdapter<DepartItemAdapter.ViewHolder> {

    private List<DepartVo> departVoList;

    public DepartItemAdapter(List<DepartVo> departVoList) {
        this.departVoList = departVoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_depart,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAdapterBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(departVoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return departVoList == null ? 0 : departVoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
