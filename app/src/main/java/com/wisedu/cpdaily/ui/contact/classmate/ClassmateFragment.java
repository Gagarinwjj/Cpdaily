package com.wisedu.cpdaily.ui.contact.classmate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.wisedu.cpdaily.Constants;
import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;
import com.wisedu.cpdaily.model.Academy;
import com.wisedu.cpdaily.model.PickItem;
import com.wisedu.cpdaily.model.UserComplete;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.UserCompleteAdapter;
import com.wisedu.cpdaily.ui.contact.search.SearchActivity;
import com.wisedu.cpdaily.ui.contact.search.SearchFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 界面实现
 */
public class ClassmateFragment extends BaseFragment implements ClassmateContract.View, View
        .OnClickListener {
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_academy_major)
    TextView tvAcademyMajor;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.twink_refresh)
    TwinklingRefreshLayout twinkRefresh;

    @Inject
    ClassmatePresenter presenter;
    List<UserComplete> mUserCompleteList;
    UserCompleteAdapter userCompleteAdapter;
    String grade, academy, major, sex, keyword;
    ArrayList<PickItem> gradeList;
    ArrayList<PickItem> sexList;
    OptionsPickerView<PickItem> gradePickerView;
    OptionsPickerView<PickItem> academyPickerView;
    OptionsPickerView<PickItem> sexPickerView;
    boolean isAcademyPickerViewAutoShow = false;

    public ClassmateFragment() {
    }

    public static ClassmateFragment newInstance() {
        return new ClassmateFragment();
    }

    @Override
    public void inject() {
        DaggerClassmateComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .classmateModule(new ClassmateModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classmate, container, false);
        mBaseUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        twinkRefresh.setEnableRefresh(false);
        twinkRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                presenter.getClassmates(grade, academy, major, sex, null, mUserCompleteList.size());
            }
        });

        tvGrade.setOnClickListener(this);
        tvAcademyMajor.setOnClickListener(this);
        tvSex.setOnClickListener(this);
    }

    private void initData() {
        initGradeList();
        initSexList();
        presenter.getAcademy();
        mUserCompleteList = new ArrayList<>(Constants.PAGE.LIMIT);
        presenter.getClassmates(grade, academy, major, sex, null, 0);
    }

    /**
     * 初始化年份选择
     */
    private void initGradeList() {
        gradeList = new ArrayList<>(11);
        gradeList.add(new PickItem(null, "年份不限"));
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            int showYear = currentYear - i;
            gradeList.add(new PickItem(String.valueOf(showYear), showYear + "年入学"));
        }
    }

    /**
     * 初始化性别选择
     */
    private void initSexList() {
        sexList = new ArrayList<>(3);
        sexList.add(new PickItem(null, "性别不限"));
        sexList.add(new PickItem(UserComplete.GENDER_MALE, "男"));
        sexList.add(new PickItem(UserComplete.GENDER_FEMALE, "女"));
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
            rvUser.setAdapter(userCompleteAdapter);
            rvUser.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
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
        twinkRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                twinkRefresh.finishRefreshing();
                twinkRefresh.setEnableRefresh(false);
            }
        }, 200);
        twinkRefresh.finishLoadmore();
        twinkRefresh.setEnableLoadmore(userCompleteList.size() >= Constants.PAGE.LIMIT);

    }

    @Override
    public void initAcademy(List<Academy> academyList) {
        final List<PickItem> option1List = new ArrayList<>();
        final List<List<PickItem>> option2List = new ArrayList<>();
        option1List.add(new PickItem(null, "全部学院"));
        option2List.add(new ArrayList<PickItem>(1) {{
            add(new PickItem(null, "全部专业"));
        }});
        for (Academy academy : academyList) {
            option1List.add(new PickItem(academy.getId(), academy.getName()));
            academy.getMajors().add(0, new PickItem(null, "全部专业"));
            option2List.add(academy.getMajors());
        }

        academyPickerView = new OptionsPickerView<>(new OptionsPickerView.Builder(
                this.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvAcademyMajor.setText(option2List.get(options1).get(options2).getName());
                academy = option1List.get(options1).getId();
                major = option2List.get(options1).get(options2).getId();
                search();
            }
        }).setCyclic(false, false, false));
        academyPickerView.setPicker(option1List, option2List);

        if (isAcademyPickerViewAutoShow) {
            academyPickerView.show();
            isAcademyPickerViewAutoShow = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_grade:
                if (gradePickerView == null) {
                    gradePickerView = new OptionsPickerView<>(new OptionsPickerView.Builder(
                            this.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3,
                                                    View v) {
                            tvGrade.setText(gradeList.get(options1).getName());
                            grade = gradeList.get(options1).getId();
                            search();
                        }
                    }).setCyclic(false, false, false));
                    gradePickerView.setPicker(gradeList);
                }
                gradePickerView.show();
                break;
            case R.id.tv_academy_major:
                if (academyPickerView != null) {
                    academyPickerView.show();
                } else {
                    toast("获取学院信息...");
                    isAcademyPickerViewAutoShow = true;
                    presenter.getAcademy();
                }
                break;
            case R.id.tv_sex:
                if (sexPickerView == null) {
                    sexPickerView = new OptionsPickerView<>(new OptionsPickerView.Builder(
                            this.getContext(), new OptionsPickerView.OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3,
                                                    View v) {
                            tvSex.setText(sexList.get(options1).getName());
                            sex = sexList.get(options1).getId();
                            search();
                        }
                    }).setCyclic(false, false, false));
                    sexPickerView.setPicker(sexList);

                }
                sexPickerView.show();
                break;
        }

    }

    public void search() {
        mUserCompleteList.clear();
        twinkRefresh.setEnableRefresh(true);
        twinkRefresh.startRefresh();
        presenter.getClassmates(grade, academy, major, sex, null, 0);
    }

    @OnClick(R.id.fl_search)
    public void jumpSearch() {
        startActivity(new Intent(this.getContext(), SearchActivity.class).putExtra(SearchFragment
                .SEARCH_TYPE, SearchFragment.TYPE_STUDENT));
    }

}
