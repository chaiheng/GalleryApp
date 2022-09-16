package com.example.modulebase.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.modulebase.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class GlideUtils {


    public static void setImagePic(Context context, String path, ImageView imageView) {
        try {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    //                .dontAnimate()
                    //                .error(android.R.drawable.stat_notify_error)
                    .priority(Priority.HIGH)
                    //.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

            Glide.with(context)
                    .load(path)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .apply(options)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Glide.with(context).load(path).placeholder(R.mipmap.ic_ml_placeholder).dontAnimate().centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public static void setRoundImagePic(Context context, String path, ImageView imageView) {
        try {
            Glide.with(context).load(path)
                    .placeholder(R.mipmap.ic_default_ava).transform(new GlideRoundTransform(context, 5)).centerCrop().into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setUserImagePic(Context context, String path, ImageView imageView) {
        try {
            Glide.with(context).load(path)
                    .placeholder(R.mipmap.ic_default_ava).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUserImagePic(Context context, Object res, ImageView imageView) {
        try {
            Glide.with(context).load(res)
                    .placeholder(R.mipmap.ic_default_ava).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //高斯模糊背景
    public static void setDimBackground(Context context, Object path, ImageView imageView) {
        try {
            Glide.with(context).load(path).dontAnimate().transform(new BlurTransformation(25, 3)).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
