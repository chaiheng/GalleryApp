package com.example.galleryapp.ui;

import android.content.Intent;
import android.view.View;

import com.example.galleryapp.databinding.ActivityDetailBinding;
import com.example.modulebase.extend.BaseActivity;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> implements View.OnClickListener {


    @Override
    protected void initView() {
        binding.ivDetailBack.setOnClickListener(this);
        binding.ivDetailSc.setOnClickListener(this);
        binding.tvDetailShare.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v == binding.ivDetailBack) {
            finish();

        } else if (v == binding.tvDetailShare) {
            startActivity(new Intent(this, PerViewActivity.class));

        } else if (v == binding.ivDetailSc) {

        }

    }
}
