package com.example.galleryapp;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modulebase.bean.TestBean;

import java.util.List;

public class CollectionAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {


    public CollectionAdapter(Context context, @Nullable List<TestBean> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TestBean item) {
        ImageView pic = helper.getView(R.id.iv_item_pic);
        helper.setText(R.id.tv_item_name, item.getName());
        Glide.with(mContext).load(item.getRes()).into(pic);


    }
}
