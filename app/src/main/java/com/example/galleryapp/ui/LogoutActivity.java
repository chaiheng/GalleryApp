package com.example.galleryapp.ui;

import android.content.Intent;
import android.view.View;

import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityLogoutBinding;
import com.example.modulebase.extend.BaseActivity;

public class LogoutActivity extends BaseActivity<ActivityLogoutBinding> implements View.OnClickListener {

    private boolean isAgree = false;

    @Override
    protected void initView() {

        setTitle("用户注销");
        binding.certIvAgree.setOnClickListener(this);
        binding.tvLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v == binding.certIvAgree) {
            if (isAgree) {
                isAgree = false;
                binding.certIvAgree.setImageResource(R.mipmap.ic_cert_noagree);
            } else {
                isAgree = true;
                binding.certIvAgree.setImageResource(R.mipmap.ic_cert_agree);
            }

        } else if (v == binding.tvLogout) {
            if (isAgree) {
                showToast("提交成功");
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            } else {
                showToast("请先同意以上须知");
            }

        }
    }
}
