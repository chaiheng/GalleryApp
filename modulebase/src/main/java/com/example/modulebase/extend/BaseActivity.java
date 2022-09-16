package com.example.modulebase.extend;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

import com.example.modulebase.util.StatusBarUtil;
import com.hjq.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseActivity<T extends ViewBinding> extends ToolbarActivity<T> {
    protected Handler mHandler = new Handler();

    protected FragmentActivity mActivity;
    protected ProgressDialog mProgressDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            savedInstanceState = null;
        }

        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView();
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(0xff16181C);
        }
//        setContentView();
        mActivity = this;
        StatusBarUtil.setStatusBarDarkTheme(mActivity, true);
//        StatusBarUtil.setStatusBarColor(this, 0x00000000);
        initView();
        initData();
    }


    protected abstract void initView();

    protected abstract void initData();

    public boolean isEventBusRegistered(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegistered(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegistered(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }

    public void showToast(String r) {
        ToastUtils.show(r);
    }


    public String getMyString(int StrId) {
        return getResources().getString(StrId);
    }

    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (mProgressDialog.isShowing() || isFinishing()) {
            return;
        }
        mProgressDialog.show();
    }


    public void showProgress(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        if (mProgressDialog.isShowing() || isFinishing()) {
            return;
        }
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void dismissProgress() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
