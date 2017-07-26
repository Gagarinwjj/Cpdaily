package com.wisedu.cpdaily.ui.contact.school;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.UserCompleteAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * 界面实现
 */
public class SchoolFragment extends BaseFragment implements SchoolContract.View {
    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.twink_refresh)
    TwinklingRefreshLayout twinkRefresh;
    @Inject
    SchoolPresenter presenter;
    List<UserComplete> mUserCompleteList;
    UserCompleteAdapter userCompleteAdapter;

    public SchoolFragment() {
    }

    public static SchoolFragment newInstance() {
        return new SchoolFragment();
    }

    @Override
    public void inject() {
        DaggerSchoolComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .schoolModule(new SchoolModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_school;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        twinkRefresh.setEnableLoadmore(false);
        twinkRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mUserCompleteList.clear();
                presenter.getCampusMedia();
            }
        });
    }

    private void initData() {
        mUserCompleteList = new ArrayList<>(Constants.PAGE.LIMIT);
        presenter.getCampusMedia();
    }

    @Override
    public void showCampusMedia(List<UserComplete> userCompleteList) {
        if (userCompleteAdapter == null) {//首次加载
            mUserCompleteList.addAll(userCompleteList);
            userCompleteAdapter = new UserCompleteAdapter(this, mUserCompleteList);
            userCompleteAdapter.setOnItemClickListener(new ItemClickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    UserComplete userComplete = mUserCompleteList.get(position);
                    toast(userComplete.getName());
                }
            });
            EmptyWrapper mEmptyWrapper = new EmptyWrapper(userCompleteAdapter);
            mEmptyWrapper.setEmptyView(R.layout.item_contact_empty);
            rvUser.setAdapter(mEmptyWrapper);
            rvUser.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            rvUser.addItemDecoration(new HorizontalDividerItemDecoration
                    .Builder(this.getActivity())
                    .color(getResources().getColor(R.color.contact_divider))
                    .sizeResId(R.dimen.contact_divider)
                    .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                    .build());
        } else {
            mUserCompleteList.addAll(userCompleteList);
            userCompleteAdapter.notifyDataSetChanged();
        }
        twinkRefresh.finishRefreshing();
    }
}
