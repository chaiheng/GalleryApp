package com.example.galleryapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.example.modulebase.util.SharePreferenceUtils;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ydnxy.zhouyou.http.EasyHttp;


public class MApplication extends Application {

    private MApplication mMutiApplication;
    private Context mContext;
    private int foregroundCount = 0;
    private boolean firstLaunching = true;
    private static long stoptime = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        fixOppoAssetManager();
        mContext = this;
        mMutiApplication = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(mContext);
            if (!"com.example.galleryapp".equals(processName)) {//判断不等于默认进程名称
                WebView.setDataDirectorySuffix(processName);
            }
        }
        initAll();
    }

    private void initAll() {
//        DeviceIdentifier.register(this);
//        SVGAParser.Companion.shareParser().init(this);
//        CrashReport.initCrashReport(mContext, "5642a606d9", true);
//        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
//            ARouter.openLog();     // Print log
//            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
//        }
        ToastUtils.init(this);
        EasyHttp.init(this);
//        ARouter.init(this);
//        NetStateReceiver.registerNetworkStateReceiver(mContext);
        if (isMainProcess()) {
            SharePreferenceUtils.open(mContext);
        }
        initEHttp();


    }


    private String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(mContext).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == this.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(mContext).clearMemory();
        }
        Glide.get(mContext).trimMemory(level);
    }
    //

    private void fixOppoAssetManager() {
        String device = Build.MODEL;
        if (!TextUtils.isEmpty(device)) {
            if (device.contains("OPPO R9") || device.contains("OPPO A5")) {
                try {
                    // 关闭掉FinalizerWatchdogDaemon
                    Class clazz = Class.forName("java.lang.Daemons$FinalizerWatchdogDaemon");
                    Method method = clazz.getSuperclass().getDeclaredMethod("stop");
                    method.setAccessible(true);
                    Field field = clazz.getDeclaredField("INSTANCE");
                    field.setAccessible(true);
                    method.invoke(field.get(null));
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        if (null == activityManager || null == activityManager.getRunningAppProcesses()) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess != null) {
                if (appProcess.pid == pid) {
                    return this.getApplicationInfo().packageName.equals(appProcess.processName);
                }
            }
        }
        return false;
    }


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new BezierRadarHeader(context);//
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new BallPulseFooter(context);
            }
        });
    }


    private void initEHttp() {
        //全局设置请求头
        EasyHttp.getInstance()
                .debug("EasyHttp", true)//调试打印
                .setReadTimeOut(30 * 1000)
                .setWriteTimeOut(30 * 100)
                .setConnectTimeout(30 * 100)
                .setRetryCount(3)//网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setCacheTime(43200);//缓存时间12小时
//        if (ApiContact.isOnline) {
//        EasyHttp.getInstance().addInterceptor(new SecurityInterceptor());
//        }
//        RetrofitClient.getInstance();
//        if (ApiContact.isOnline) {
//            RetrofitClient.getInstance()
//                    .addInterceptor(securityInterceptor);
//        }


    }


}
