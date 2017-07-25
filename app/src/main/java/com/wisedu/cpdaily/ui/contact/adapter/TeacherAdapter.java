package com.wisedu.cpdaily.ui.contact.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wisedu.cpdaily.GlideApp;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 老师列表
 */

public class TeacherAdapter extends ItemClickAdapter<TeacherAdapter.ViewHolder> {
    private List<TeacherVo> teacherVoList;
    private boolean isNeedAlias;
    private Fragment fragment;
    private RequestOptions options;

    public TeacherAdapter(Fragment fragment, List<TeacherVo> teacherVoList) {
        this.fragment = fragment;
        this.teacherVoList = teacherVoList;
        options = new RequestOptions().centerCrop().transform(new RoundedCorners(16));
        isNeedAlias = true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_userinfo,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onAdapterBindViewHolder(ViewHolder holder, int position) {
        TeacherVo teacherVo = teacherVoList.get(position);
        if (isNeedAlias) {//开启昵称
            String alias = teacherVo.getAlias();
            holder.tvName1.setText(alias);
            if (!TextUtils.isEmpty(alias)) {//昵称非空
                if (!alias.equalsIgnoreCase(teacherVo.getName())) {//姓名与昵称不等
                    holder.tvName2.setVisibility(View.VISIBLE);
                    holder.tvName2.setText(teacherVo.getName());
                } else {//姓名与昵称相等
                    holder.tvName2.setVisibility(View.GONE);
                }
            } else {//昵称为空
                //1、仅显示真实姓名
                holder.tvName1.setText(teacherVo.getName());
                holder.tvName2.setVisibility(View.GONE);
                //2、真实姓名非空则显示
                    /*if (!TextUtils.isEmpty(userComplete.getName())) {
                        holder.tvName2.setVisibility(View.VISIBLE);
                        holder.tvName2.setText(userComplete.getName());
                    } else {
                        holder.tvName2.setVisibility(View.GONE);
                    }*/
            }
        } else {//未开启昵称
            holder.tvName1.setText(teacherVo.getName());
            holder.tvName2.setVisibility(View.GONE);
        }
        holder.ivMediaV.setVisibility(View.GONE);
        if (UserComplete.GENDER_MALE.equalsIgnoreCase(teacherVo.getGender())) {
            holder.ivGender.setVisibility(View.VISIBLE);
            holder.ivGender.setImageResource(R.drawable.man_logo);
            GlideApp.with(fragment)
                    .load((teacherVo.getSmallImg()))
                    .apply(options)
                    .placeholder(R.drawable.default_man)
                    .error(R.drawable.default_man)
                    .into(holder.ivAvatar);
        } else if (UserComplete.GENDER_FEMALE.equalsIgnoreCase(teacherVo.getGender())) {
            holder.ivGender.setVisibility(View.VISIBLE);
            holder.ivGender.setImageResource(R.drawable.woman_logo);
            GlideApp.with(fragment)
                    .load((teacherVo.getSmallImg()))
                    .apply(options)
                    .placeholder(R.drawable.default_women)
                    .error(R.drawable.default_women)
                    .into(holder.ivAvatar);
        } else {//未知性别->默认为男
            //holder.ivGender.setVisibility(View.GONE);
            holder.ivGender.setVisibility(View.VISIBLE);
            holder.ivGender.setImageResource(R.drawable.man_logo);
            GlideApp.with(fragment)
                    .load((teacherVo.getSmallImg()))
                    .apply(options)
                    .placeholder(R.drawable.default_man)
                    .error(R.drawable.default_man)
                    .into(holder.ivAvatar);
        }
        holder.tvInfo.setText(teacherVo.getJob());
        holder.ivMsg.setOnClickListener(this);
        holder.ivMsg.setTag(position);
        holder.ivAvatar.setOnClickListener(this);
        holder.ivAvatar.setTag(R.id.iv_avatar, position);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_msg://内部消化 or 接口转发
                int position = (int) v.getTag();
                Toast.makeText(fragment.getContext(), "position=" + position, Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.iv_avatar:
                TeacherVo teacherVo = teacherVoList.get((int) v.getTag(R.id.iv_avatar));
                Toast.makeText(fragment.getContext(), teacherVo.getName(), Toast.LENGTH_SHORT)
                        .show();

                break;
            default:
                super.onClick(v);
        }
    }

    @Override
    public int getItemCount() {
        return teacherVoList == null ? 0 : teacherVoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.iv_media_v)
        ImageView ivMediaV;
        @BindView(R.id.iv_msg)
        ImageView ivMsg;
        @BindView(R.id.tv_name1)
        TextView tvName1;
        @BindView(R.id.tv_name2)
        TextView tvName2;
        @BindView(R.id.tv_info)
        TextView tvInfo;
        @BindView(R.id.iv_gender)
        ImageView ivGender;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
