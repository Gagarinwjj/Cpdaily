package com.wisedu.cpdaily.ui.contact.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.TeacherAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.UserCompleteAdapter;
import com.wisedu.cpdaily.utils.LogUtils;
import com.wisedu.cpdaily.widget.TitleBar;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * 界面实现
 */
public class SearchFragment extends BaseFragment implements SearchContract.View, View
        .OnClickListener {
    public static final String SEARCH_TYPE = "search_type";
    public static final String TYPE_ALL = "all";
    public static final String TYPE_STUDENT = "student";
    public static final String TYPE_TEACHER = "teacher";
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_search_option)
    LinearLayout llSearchOption;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_search_student)
    TextView tvSearchStudent;
    @BindView(R.id.rl_search_student)
    RelativeLayout rlSearchStudent;
    @BindView(R.id.tv_search_teacher)
    TextView tvSearchTeacher;
    @BindView(R.id.rl_search_teacher)
    RelativeLayout rlSearchTeacher;
    @BindView(R.id.rv_student)
    RecyclerView rvStudent;
    @BindView(R.id.rv_teacher)
    RecyclerView rvTeacher;
    @BindView(R.id.twink_refresh)
    TwinklingRefreshLayout twinkRefresh;

    @Inject
    SearchPresenter presenter;
    List<UserComplete> mUserCompleteList;
    UserCompleteAdapter userCompleteAdapter;
    List<TeacherVo> mTeacherVoList;
    TeacherAdapter teacherAdapter;
    boolean isCodeRefresh;//代码触发的刷新
    private String searchType;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String searchType) {
        SearchFragment searchFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_TYPE, searchType);
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    public void inject() {
        DaggerSearchComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        searchType = getArguments().getString(SEARCH_TYPE, TYPE_ALL);
        if (searchType.equalsIgnoreCase(TYPE_STUDENT)) {
            titleBar.setTitleName(getString(R.string.search_classmate));
            etSearch.setHint(getString(R.string.search_classmate));
        } else if (searchType.equalsIgnoreCase(TYPE_TEACHER)) {
            titleBar.setTitleName(getString(R.string.search_teacher));
            etSearch.setHint(getString(R.string.search_teacher));
        }
        twinkRefresh.setEnableRefresh(false);//禁止手动刷新
        twinkRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                String searchText = etSearch.getText().toString().trim();
                if (rvStudent.getVisibility() == View.VISIBLE) {
                    presenter.getClassmates(searchText, mUserCompleteList.size());
                } else {
                    presenter.getTeacher(searchText, mTeacherVoList.size());
                }
            }

            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {//手动时，不是每次都触发，why？
                if (isCodeRefresh) {
                    isCodeRefresh = false;
                    return;
                }
                toast("手动触发onRefresh:" + etSearch.getText().toString());
                if (rvStudent.getVisibility() == View.VISIBLE) {
                    rlSearchStudent.performClick();
                } else {
                    rlSearchTeacher.performClick();
                }
            }
        });
        ivClose.setOnClickListener(this);
        rlSearchStudent.setOnClickListener(this);
        rlSearchTeacher.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String s = etSearch.getText().toString().trim();
                if (!TextUtils.isEmpty(s) && actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchType.equalsIgnoreCase(TYPE_STUDENT)) {
                        rlSearchStudent.performClick();
                        return true;
                    } else if (searchType.equalsIgnoreCase(TYPE_TEACHER)) {
                        rlSearchTeacher.performClick();
                        return true;
                    }
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.d(TAG, "etSearch afterTextChanged");
                String searchText = s.toString();
                if (TextUtils.isEmpty(searchText)) {
                    ivClose.setVisibility(View.INVISIBLE);
                    llSearchOption.setVisibility(View.GONE);
                } else {
                    ivClose.setVisibility(View.VISIBLE);
                    if (TYPE_ALL.equalsIgnoreCase(searchType)) {
                        llSearchOption.setVisibility(View.VISIBLE);
                        tvSearchStudent.setText(Html.fromHtml(String.format(Locale.CHINA, "找同学 " +
                                "<font " +
                                "color=\"#58C7C9\">%s</font>", searchText)));
                        tvSearchTeacher.setText(Html.fromHtml(String.format(Locale.CHINA, "找教职工 " +
                                "<font" +
                                " " +
                                "color=\"#58C7C9\">%s</font>", searchText)));
                    }
                }
            }
        });
    }

    private void initData() {
        mUserCompleteList = new ArrayList<>(Constants.PAGE.LIMIT);
        mTeacherVoList = new ArrayList<>(Constants.PAGE.LIMIT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_search:
                String searchText = etSearch.getText().toString().trim();
                if (TYPE_ALL.equalsIgnoreCase(searchType) && !TextUtils.isEmpty(searchText)) {
                    ivClose.setVisibility(View.VISIBLE);
                    llSearchOption.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_close:
                etSearch.setText("");
                break;
            case R.id.rl_search_student:
                rvStudent.setVisibility(View.VISIBLE);
                rvTeacher.setVisibility(View.GONE);
                llSearchOption.setVisibility(View.GONE);
                isCodeRefresh = true;//仅仅只是动画效果，忽略处理事件
                twinkRefresh.setEnableRefresh(true);
                twinkRefresh.startRefresh();
                mUserCompleteList.clear();
                etSearch.clearFocus();
                presenter.getClassmates(etSearch.getText().toString().trim(), 0);
                break;
            case R.id.rl_search_teacher:
                rvStudent.setVisibility(View.GONE);
                rvTeacher.setVisibility(View.VISIBLE);
                llSearchOption.setVisibility(View.GONE);
                isCodeRefresh = true;//仅仅只是动画效果，忽略处理事件
                twinkRefresh.setEnableRefresh(true);
                twinkRefresh.startRefresh();
                mTeacherVoList.clear();
                etSearch.clearFocus();
                presenter.getTeacher(etSearch.getText().toString().trim(), 0);
                break;
        }
    }

    @Override
    public void showClassmates(List<UserComplete> userCompleteList) {
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
            mEmptyWrapper.setEmptyView(R.layout.item_contact_empty_student);
            rvStudent.setAdapter(mEmptyWrapper);
            rvStudent.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            rvStudent.addItemDecoration(new HorizontalDividerItemDecoration
                    .Builder(this.getActivity())
                    .color(getResources().getColor(R.color.contact_divider))
                    .sizeResId(R.dimen.contact_divider)
                    .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                    .build());
        } else {
            mUserCompleteList.addAll(userCompleteList);
            rvStudent.getAdapter().notifyDataSetChanged();
        }
        twinkRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                twinkRefresh.finishRefreshing();
                twinkRefresh.setEnableRefresh(false);//禁止手动刷新
            }
        }, 200);
        twinkRefresh.finishLoadmore();
        twinkRefresh.setEnableLoadmore(userCompleteList.size() >= Constants.PAGE.LIMIT);
    }

    @Override
    public void showTeacher(List<TeacherVo> teacherVoList) {
        if (teacherAdapter == null) {//首次加载
            mTeacherVoList.addAll(teacherVoList);
            teacherAdapter = new TeacherAdapter(this, mTeacherVoList);
            teacherAdapter.setOnItemClickListener(new ItemClickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    TeacherVo teacherVo = mTeacherVoList.get(position);
                    toast(teacherVo.getName());
                }
            });
            EmptyWrapper mEmptyWrapper = new EmptyWrapper(teacherAdapter);
            mEmptyWrapper.setEmptyView(R.layout.item_contact_empty_teacher);
            rvTeacher.setAdapter(mEmptyWrapper);
            rvTeacher.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            rvTeacher.addItemDecoration(new HorizontalDividerItemDecoration
                    .Builder(this.getActivity())
                    .color(getResources().getColor(R.color.contact_divider))
                    .sizeResId(R.dimen.contact_divider)
                    .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                    .build());
        } else {
            mTeacherVoList.addAll(teacherVoList);
            rvTeacher.getAdapter().notifyDataSetChanged();
        }
        twinkRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                twinkRefresh.finishRefreshing();
                twinkRefresh.setEnableRefresh(false);//禁止手动刷新
            }
        }, 200);
        twinkRefresh.finishLoadmore();
        twinkRefresh.setEnableLoadmore(mTeacherVoList.size() >= Constants.PAGE.LIMIT);
    }
}
