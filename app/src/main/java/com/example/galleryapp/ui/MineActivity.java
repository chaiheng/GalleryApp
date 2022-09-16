package com.example.galleryapp.ui;

import android.content.Intent;
import android.view.View;

import com.example.galleryapp.databinding.ActivityMineBinding;
import com.example.modulebase.extend.BaseActivity;
import com.example.modulebase.extend.WebActivity;

public class MineActivity extends BaseActivity<ActivityMineBinding> implements View.OnClickListener {


    @Override
    protected void initView() {
        binding.ivMineBack.setOnClickListener(this);
        binding.tvMineBtnlogin.setOnClickListener(this);
        binding.tvMineLogout.setOnClickListener(this);
        binding.tvMinePrivacypolicy.setOnClickListener(this);
        binding.tvMineUseragreement.setOnClickListener(this);
        binding.tvMineMyfavorite.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v == binding.ivMineBack) {
            finish();
        } else if (v == binding.tvMineBtnlogin) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (v == binding.tvMineLogout) {
            //注销
            startActivity(new Intent(this, LogoutActivity.class));
        } else if (v == binding.tvMinePrivacypolicy) {
            startActivity(new Intent(mActivity, WebActivity.class)
                    .putExtra(WebActivity.webTitle, "隐私协议")
                    .putExtra(WebActivity.webUrl, "https://www.vod.dianzhenbox.com/privacy.html"));
        } else if (v == binding.tvMineUseragreement) {
            startActivity(new Intent(mActivity, WebActivity.class)
                    .putExtra(WebActivity.webTitle, "用户协议")
                    .putExtra(WebActivity.webUrl, "https://www.vod.dianzhenbox.com/user.html"));
        } else if (v == binding.tvMineMyfavorite) {
            //收藏
            startActivity(new Intent(this, CollectionActivity.class));
        }

    }
}
