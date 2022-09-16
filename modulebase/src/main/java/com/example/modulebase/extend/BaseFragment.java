package com.example.modulebase.extend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjq.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseFragment extends LazyLoadFragment {
    public Activity mContext;
    private View rootView;
    private ProgressDialog mWaitingDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getRootViewLayout(), container, false);
            initview();
        }
        return rootView;

    }

    @Nullable
    public <T extends View> T findViewById(int resourceId) {
        if (resourceId < 0) {
            return null;
        }
        return rootView.findViewById(resourceId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract int getRootViewLayout();

    protected abstract void initview();


    @Override
    protected void onFragmentFirstVisible() {
        //当第一次可见的时候，加载数据
        loadData();
    }


    //加载数据
    protected void loadData() {

    }


    public void onPauseFragment() {
        //处理切换fragment时 需要处理的问题
    }

    public void onResumeFragment() {
        //处理onResume
    }

    public void showToast(String text) {
        ToastUtils.show(text);
    }


    public String getMyString(int strId) {
        return mContext.getResources().getString(strId);
    }


    public void showLoading() {
        if (mWaitingDialog == null) {
            mWaitingDialog = new ProgressDialog(mContext);
        }
        mWaitingDialog.setMessage("");
        mWaitingDialog.setCancelable(true);
        mWaitingDialog.show();
    }

    public void showLoading(String msg) {
        if (mWaitingDialog == null) {
            mWaitingDialog = new ProgressDialog(mContext);
        }
        mWaitingDialog.setMessage(msg);
        mWaitingDialog.setCancelable(true);
        mWaitingDialog.show();
    }

    public void dismissLoading() {
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
    }


}
