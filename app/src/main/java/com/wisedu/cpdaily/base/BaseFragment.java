package com.wisedu.cpdaily.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wisedu.cpdaily.CpdailyApplication;
import com.wisedu.cpdaily.di.components.NetComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    public NetComponent mBaseNetComponent;
    public Unbinder mBaseUnBinder;
    public BasePresenter mBasePresenter;
    public String TAG = this.getClass().getSimpleName();

    public BaseFragment() {
        // Required empty public constructor
    }

    public abstract void inject();


    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseNetComponent = ((CpdailyApplication) getActivity().getApplication()).getNetComponent();
        inject();
    }


    /**
     * onCreateView()移到BaseFragment中
     * 好处:可以简化子类的代码，因为子类不用重写这个方法了
     * 坏处:对于IDE而言，Fragment→布局XML可以；布局XML→Fragment不可以
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBaseUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBaseUnBinder != null) {
            mBaseUnBinder.unbind();
        }
        if (mBasePresenter != null) {
            mBasePresenter.detachView();
        }
    }


    @Override
    public void toast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int resId) {
        Toast.makeText(this.getContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
