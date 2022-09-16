package com.example.modulebase.extend;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.modulebase.R;
import com.example.modulebase.databinding.ActivityWebBinding;


public class WebActivity extends BaseActivity<ActivityWebBinding> {

    private String title;
    private String url;
    public static String webTitle = "intentWebTitle";
    public static String webUrl = "intentWebUrl";


    @Override
    protected void initData() {

    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(webTitle);
            url = bundle.getString(webUrl);
        }
        setTitle(title);

        binding.webview.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        // 设置屏幕自适应。
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        // 建议禁止缓存加载，以确保在攻击发生时可快速获取最新的滑动验证组件进行对抗。
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        binding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!TextUtils.isEmpty(url)) {
                    view.loadUrl(url);
                    return true;
                } else {
                    return false;
                }
            }
        });
        // 设置WebView组件支持加载JavaScript。
        binding.webview.getSettings().setJavaScriptEnabled(true);

        // 加载业务页面。
        binding.webview.loadUrl(url);
    }

}
