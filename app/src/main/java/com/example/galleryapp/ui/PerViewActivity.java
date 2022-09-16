package com.example.galleryapp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.View;

import com.example.galleryapp.databinding.ActivityPreviewBinding;
import com.example.modulebase.extend.BaseActivity;
import com.example.modulebase.util.DensityUtil;
import com.example.modulebase.util.SaveViewToImage;
import com.king.zxing.util.CodeUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class PerViewActivity extends BaseActivity<ActivityPreviewBinding> {

    private RxPermissions mRxPermissions;

    @Override
    protected void initView() {
        setTitle("预览");
        mRxPermissions = new RxPermissions(this);
        binding.tvPreviewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhotoCode();
            }
        });
    }

    @Override
    protected void initData() {

        Bitmap bitmap = CodeUtils.createQRCode("www.baidu.com", DensityUtil.dip2px(mActivity, 60));
        binding.ivPreviewQrcode.setImageBitmap(bitmap);

    }

    @SuppressLint("CheckResult")
    private void savePhotoCode() {


        mRxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        SaveViewToImage.viewSaveToImage(binding.llPerview, mActivity);
                    } else {
                        showToast("请打开存储权限");
                    }
                });

    }
}
