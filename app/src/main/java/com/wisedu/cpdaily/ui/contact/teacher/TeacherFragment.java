package com.wisedu.cpdaily.ui.contact.teacher;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.base.BaseFragment;
import com.wisedu.cpdaily.model.DepartVo;
import com.wisedu.cpdaily.model.TeacherVo;
import com.wisedu.cpdaily.ui.adapter.ItemClickAdapter;
import com.wisedu.cpdaily.ui.common.ContainerActivity;
import com.wisedu.cpdaily.ui.contact.adapter.DepartItemAdapter;
import com.wisedu.cpdaily.ui.contact.adapter.TeacherAdapter;
import com.wisedu.cpdaily.ui.contact.search.SearchFragment;
import com.wisedu.cpdaily.widget.TitleBar;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wisedu.cpdaily.ui.contact.search.SearchFragment.TYPE_TEACHER;


/**
 * 界面实现
 */
public class TeacherFragment extends BaseFragment implements TeacherContract.View {

    public static final String DEPARTMENT = "DEPART";
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.fl_search)
    FrameLayout flSearch;
    @BindView(R.id.rv_items)
    RecyclerView rvItems;

    @Inject
    TeacherPresenter presenter;

    List<DepartVo> mDeportVoList;
    DepartItemAdapter departItemAdapter;
    List<TeacherVo> mTeacherVoList;
    TeacherAdapter teacherAdapter;
    DepartVo departVo;

    public TeacherFragment() {
    }

    public static TeacherFragment newInstance(DepartVo departVo) {
        TeacherFragment fragment = new TeacherFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DEPARTMENT, departVo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void inject() {
        DaggerTeacherComponent
                .builder()
                .netComponent(mBaseNetComponent)
                .teacherModule(new TeacherModule(this))
                .build()
                .inject(this);
        mBasePresenter = presenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_teacher;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        departVo = getArguments().getParcelable(DEPARTMENT);
        if (departVo != null) {
            titleBar.setTitleName(departVo.getName());
            presenter.getDeportVo(departVo.getId());
            flSearch.setVisibility(View.GONE);
        } else {
            presenter.getDeportVo(null);
        }
        mDeportVoList = new ArrayList<>();
        mTeacherVoList = new ArrayList<>();
    }

    @Override
    public void showDeportVo(List<DepartVo> deportVoList) {
        if (deportVoList.size() > 0) {//加载该部门下的：子部门
            mDeportVoList.addAll(deportVoList);
            departItemAdapter = new DepartItemAdapter(mDeportVoList);
            departItemAdapter.setOnItemClickListener(new ItemClickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    startActivity(ContainerActivity.getIntent(getContext(), TeacherFragment
                            .class).putExtra(TeacherFragment.DEPARTMENT, mDeportVoList.get
                            (position)));
                }
            });
            rvItems.setAdapter(departItemAdapter);
            rvItems.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                    LinearLayoutManager.VERTICAL, false));
            rvItems.addItemDecoration(new HorizontalDividerItemDecoration
                    .Builder(this.getActivity())
                    .color(getResources().getColor(R.color.contact_divider))
                    .sizeResId(R.dimen.contact_divider)
                    .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                    .build());
        } else {//加载该部门下的：人员
            if (departVo != null) {
                presenter.getTeacher(departVo.getId(), 0);
            }
        }
    }

    @Override
    public void showTeacher(List<TeacherVo> teacherVoList) {
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
        mEmptyWrapper.setEmptyView(R.layout.item_contact_empty);
        rvItems.setAdapter(mEmptyWrapper);
        rvItems.setLayoutManager(new LinearLayoutManager(this.getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rvItems.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(this.getActivity())
                .color(getResources().getColor(R.color.contact_divider))
                .sizeResId(R.dimen.contact_divider)
                .marginResId(R.dimen.contact_space, R.dimen.contact_space)
                .build());
    }

    @OnClick(R.id.fl_search)
    public void jumpSearch() {
        startActivity(ContainerActivity.getIntent(getContext(), SearchFragment.class)
                .putExtra(SearchFragment.SEARCH_TYPE, TYPE_TEACHER));
    }
}
