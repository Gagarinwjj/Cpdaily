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
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户列表 - 关注列表
 * Created by wjj on 2017/07/22 22:40:14.
 */

public class UserCompleteAdapter extends ItemClickAdapter<UserCompleteAdapter.ViewHolder> {
    private List<UserComplete> userCompleteList;
    private boolean isNeedAlias;
    private Fragment fragment;
    private RequestOptions options;

    public UserCompleteAdapter(Fragment fragment, List<UserComplete> userCompleteList) {
        this.fragment = fragment;
        this.userCompleteList = userCompleteList;
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
        UserComplete userComplete = userCompleteList.get(position);
        boolean isStudent = UserComplete.USERROLE_STUDENT.equalsIgnoreCase(userComplete
                .getUserRole());
        boolean isTeacher = UserComplete.USERROLE_TEACHER.equalsIgnoreCase(userComplete
                .getUserRole());
        boolean isMedia = UserComplete.USERROLE_MEDIA.equalsIgnoreCase(userComplete.getUserRole());
        if (isStudent || isTeacher) {
            if (isNeedAlias) {//开启昵称
                String alias = userComplete.getAlias();
                holder.tvName1.setText(alias);
                if (!TextUtils.isEmpty(alias)) {//昵称非空
                    if (!alias.equalsIgnoreCase(userComplete.getName())) {//姓名与昵称不等
                        holder.tvName2.setVisibility(View.VISIBLE);
                        holder.tvName2.setText(userComplete.getName());
                    } else {//姓名与昵称相等
                        holder.tvName2.setVisibility(View.GONE);
                    }
                } else {//昵称为空
                    //1、仅显示真实姓名
                    holder.tvName1.setText(userComplete.getName());
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
                holder.tvName1.setText(userComplete.getName());
                holder.tvName2.setVisibility(View.GONE);
            }
            holder.ivMediaV.setVisibility(View.GONE);
            if (UserComplete.GENDER_MALE.equalsIgnoreCase(userComplete.getGender())) {
                holder.ivGender.setVisibility(View.VISIBLE);
                holder.ivGender.setImageResource(R.drawable.man_logo);
                GlideApp.with(fragment)
                        .load((userComplete.getSmallImg()))
                        .apply(options)
                        .placeholder(R.drawable.default_man)
                        .error(R.drawable.default_man)
                        .into(holder.ivAvatar);
            } else if (UserComplete.GENDER_FEMALE.equalsIgnoreCase(userComplete.getGender())) {
                holder.ivGender.setVisibility(View.VISIBLE);
                holder.ivGender.setImageResource(R.drawable.woman_logo);
                GlideApp.with(fragment)
                        .load((userComplete.getSmallImg()))
                        .apply(options)
                        .placeholder(R.drawable.default_women)
                        .error(R.drawable.default_women)
                        .into(holder.ivAvatar);
            } else {//未知性别->默认为男
                //holder.ivGender.setVisibility(View.GONE);
                holder.ivGender.setVisibility(View.VISIBLE);
                holder.ivGender.setImageResource(R.drawable.man_logo);
                GlideApp.with(fragment)
                        .load((userComplete.getSmallImg()))
                        .apply(options)
                        .placeholder(R.drawable.default_man)
                        .error(R.drawable.default_man)
                        .into(holder.ivAvatar);
            }
            if (isStudent) {
                String grade = userComplete.getGrade();
                if (!TextUtils.isEmpty(grade) && grade.startsWith("20")) {
                    grade = grade.replace("20", "");
                }
                holder.tvInfo.setText(grade + "级 " + userComplete.getAcademy());
            } else {
                if (!TextUtils.isEmpty(userComplete.getJob())) {//优先显示job
                    holder.tvInfo.setText(userComplete.getJob());
                } else {//其次显示部门
                    holder.tvInfo.setText(userComplete.getDepart());
                }
            }
        } else if (isMedia) {
            GlideApp.with(fragment)
                    .load((userComplete.getSmallImg()))
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.default_meida)
                    .error(R.drawable.default_meida)
                    .into(holder.ivAvatar);
            holder.ivMediaV.setVisibility(View.VISIBLE);
            holder.tvName1.setText(userComplete.getName());
            holder.tvName2.setVisibility(View.GONE);
            holder.ivGender.setVisibility(View.GONE);
            holder.tvInfo.setText(TextUtils.isEmpty(userComplete.getSignature()) ? "校园号" :
                    userComplete.getSignature());
        }
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
                UserComplete userComplete = userCompleteList.get((int) v.getTag(R.id.iv_avatar));
                Toast.makeText(fragment.getContext(), userComplete.getName(), Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                super.onClick(v);
        }
    }

    @Override
    public int getItemCount() {
        return userCompleteList == null ? 0 : userCompleteList.size();
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
