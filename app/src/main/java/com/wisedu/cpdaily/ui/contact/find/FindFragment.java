package com.wisedu.cpdaily.ui.contact.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;
import com.wisedu.cpdaily.model.Discover;
import com.wisedu.cpdaily.model.DisplayItem;
import com.wisedu.cpdaily.model.Statistic;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.DisplayItemAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.UserCompleteAdapter;
import com.wisedu.cpdaily.ui.contact.classmate.ClassmateActivity;
import com.wisedu.cpdaily.ui.contact.school.SchoolActivity;
import com.wisedu.cpdaily.ui.contact.search.SearchActivity;
import com.wisedu.cpdaily.ui.contact.search.SearchFragment;
import com.wisedu.cpdaily.ui.contact.teacher.TeacherActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 通讯录首页，我的关注，界面实现
 */
public class FindFragment extends BaseFragment implements FindContract.View {
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.rv_items)
    RecyclerView rvItems;
    @BindView(R.id.rv_follows)
    RecyclerView rvFollows;
    @BindView(R.id.tv_my_follower)
    TextView tvMyFollower;
    @BindView(R.id.twink_refresh)
    TwinklingRefreshLayout twinkRefresh;

    @Inject
    FindPresenter presenter;
    List<DisplayItem> mDisplayItemList;
    DisplayItemAdapter displayItemAdapter;
    List<UserComplete> mUserCompleteList;
    UserCompleteAdapter userCompleteAdapter;
    boolean isTeacherOn = false;//是否开启找教职工

    public FindFragment() {
    }

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public void inject() {
        DaggerFindComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .findModule(new FindModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();

    }

    private void initViews() {

        twinkRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                presenter.getFollowers(mUserCompleteList.size());
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                mUserCompleteList.clear();
                presenter.getStatistic();
                presenter.getFollowers(0);
            }
        });
    }

    private void initData() {
        mDisplayItemList = new ArrayList<>(4);
        mUserCompleteList = new ArrayList<>(Constants.PAGE.LIMIT);
        presenter.getDiscover();//变化少，刷新时不需要重新获取
        presenter.getStatistic();
        presenter.getFollowers(0);
    }

    @Override
    public void showDiscover(Discover discover) {
        String displayItems = discover.getDisplayItems();
        String[] items = displayItems.split(",");
        for (String item : items) {
            if (Discover.FIND_CLASSMATE.equalsIgnoreCase(item)) {
                mDisplayItemList.add(new DisplayItem("找同学", item, R.drawable.contact_classmate));
            } else if (Discover.FIND_TEACHER.equalsIgnoreCase(item)) {
                mDisplayItemList.add(new DisplayItem("教职工", item, R.drawable.contact_teacher));
                isTeacherOn = true;
            } else if (Discover.FIND_MEDIA.equalsIgnoreCase(item)) {
                mDisplayItemList.add(new DisplayItem("校园号", item, R.drawable.contact_meida));
            }
        }
        //群聊默认都有
        mDisplayItemList.add(new DisplayItem("群聊", Discover.FIND_TRIBE, R.drawable.contact_tribe));
        displayItemAdapter = new DisplayItemAdapter(mDisplayItemList);
        displayItemAdapter.setOnItemClickListener(new ItemClickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DisplayItem displayItem = mDisplayItemList.get(position);
                if (Discover.FIND_CLASSMATE.equalsIgnoreCase(displayItem.getFlag())) {
                    FindFragment.this.startActivity(new Intent(FindFragment.this.getContext(),
                            ClassmateActivity.class));
                } else if (Discover.FIND_TEACHER.equalsIgnoreCase(displayItem.getFlag())) {
                    FindFragment.this.startActivity(new Intent(FindFragment.this.getContext(),
                            TeacherActivity.class));
                } else if (Discover.FIND_MEDIA.equalsIgnoreCase(displayItem.getFlag())) {
                    FindFragment.this.startActivity(new Intent(FindFragment.this.getContext(),
                            SchoolActivity.class));
                } else if (Discover.FIND_TRIBE.equalsIgnoreCase(displayItem.getFlag())) {
                    toast("群聊");
                }
            }
        });
        rvItems.setAdapter(displayItemAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager
                .VERTICAL, false));
        rvItems.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(this.getActivity())
                .color(getResources().getColor(R.color.contact_divider))
                .sizeResId(R.dimen.contact_divider)
                .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                .build());
    }

    @Override
    public void showStatistic(Statistic statistic) {
        tvMyFollower.setText(String.format(Locale.CHINA, "我的关注%d", statistic.getFocus()));
    }

    @Override
    public void showFollowers(List<UserComplete> userCompleteList) {
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
            rvFollows.setAdapter(userCompleteAdapter);
            rvFollows.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            rvFollows.addItemDecoration(new HorizontalDividerItemDecoration
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
        twinkRefresh.finishLoadmore();
        twinkRefresh.setEnableLoadmore(userCompleteList.size() >= Constants.PAGE.LIMIT);
    }

    @OnClick(R.id.fl_search)
    public void jumpSearch() {
        if (isTeacherOn) {//搜索同学/教职工
            startActivity(new Intent(this.getContext(), SearchActivity.class));
        } else {//搜索同学
            startActivity(new Intent(this.getContext(), SearchActivity.class).putExtra
                    (SearchFragment.SEARCH_TYPE, SearchFragment.TYPE_STUDENT));
        }
    }
}