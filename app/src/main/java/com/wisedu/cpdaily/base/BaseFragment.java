package com.wisedu.cpdaily.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.wisedu.cpdaily.CpdailyApplication;
import com.wisedu.cpdaily.di.components.NetComponent;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseNetComponent = ((CpdailyApplication) getActivity().getApplication()).getNetComponent();
        inject();
    }

    public abstract void inject();

    @Override
    public void toast(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(int resId) {
        Toast.makeText(this.getContext(), resId, Toast.LENGTH_SHORT).show();
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
}
