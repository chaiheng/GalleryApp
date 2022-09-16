package com.example.galleryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.modulebase.bean.TestBean;

import java.util.List;

public class GalleryAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {

    private Point mScreenPoint = new Point();

    public GalleryAdapter(Context context, @Nullable List<TestBean> data) {
        super(R.layout.item_home, data);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenPoint.x = displayMetrics.widthPixels;
        mScreenPoint.y = displayMetrics.heightPixels;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, TestBean item) {
        TextView itemType = helper.getView(R.id.tv_item_type);
        ImageView pic = helper.getView(R.id.iv_item_pic);
        helper.setText(R.id.tv_item_name, item.getName())
                .setText(R.id.tv_item_writer, item.getWriter())
                .setText(R.id.tv_item_content, item.getContent());

        try {
            Glide.with(mContext).asBitmap().load(item.getRes()).placeholder(android.R.color.white).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    if (resource.getWidth() > resource.getHeight()) {
                        float height = resource.getHeight();
                        float width = mScreenPoint.x;
                        ViewGroup.LayoutParams layoutParams = pic.getLayoutParams();
                        layoutParams.width = (int) width;
                        layoutParams.height = (int) height;
                        pic.setScaleType(ImageView.ScaleType.FIT_XY);
                        pic.setLayoutParams(layoutParams);
//                        GlideUtils.setImagePic(mContext,item.getRes());
                        Glide.with(mContext).load(item.getRes()).into(pic);
                    } else {

                        //获取屏幕宽度
                        float screenWith = mScreenPoint.x;
                        ViewGroup.LayoutParams layoutParams = pic.getLayoutParams();
                        //获取imageview的高度
                        float height = resource.getWidth() + 300;
//                        layoutParams.width = (int) (mScreenPoint.x > 2000 ? screenWith * 0.7 : screenWith);
                        layoutParams.width = (int) screenWith;
                        layoutParams.height = (int) height;
                        pic.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        pic.setLayoutParams(layoutParams);
                        Glide.with(mContext).load(item.getRes()).centerCrop().into(pic);
                    }
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
//            Glide.with(mContext).asBitmap().load(item.getRes()).placeholder(android.R.color.white).thumbnail(0.1f).addListener(new RequestListener<Bitmap>() {
//                @Override
//                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
//                    return false;
//                }
//
//                @Override
//                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
//                    if (resource.getWidth() > resource.getHeight()) {
//                        float height = resource.getHeight();
//                        float width = mScreenPoint.x;
//                        ViewGroup.LayoutParams layoutParams = pic.getLayoutParams();
//                        layoutParams.width = (int) width;
//                        layoutParams.height = (int) height;
//                        pic.setScaleType(ImageView.ScaleType.FIT_XY);
//                        pic.setLayoutParams(layoutParams);
//
//                    } else {
//
//                        //获取屏幕宽度
//                        float screenWith = mScreenPoint.x;
//                        ViewGroup.LayoutParams layoutParams = pic.getLayoutParams();
//                        //获取imageview的高度
//                        float height = resource.getWidth() + 300;
////                        layoutParams.width = (int) (mScreenPoint.x > 2000 ? screenWith * 0.7 : screenWith);
//                        layoutParams.width = (int) screenWith;
//                        layoutParams.height = (int) height;
//                        pic.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                        pic.setLayoutParams(layoutParams);
//                    }
//
//                    return false;
//                }
//            }).into(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (item.getType() == 1) {
            itemType.setText("油画");
            itemType.setTextColor(0xff5D4EEE);
            itemType.setBackgroundResource(R.drawable.item_type_bg);
        } else {
            itemType.setText("壁画");
            itemType.setTextColor(0xffCB6F4A);
            itemType.setBackgroundResource(R.drawable.item_type_bg2);
        }


    }
}
