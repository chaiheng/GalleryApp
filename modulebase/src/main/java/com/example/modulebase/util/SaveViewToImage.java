package com.example.modulebase.util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.hjq.toast.ToastUtils;

import java.io.OutputStream;

public class SaveViewToImage {
    @SuppressLint("NewApi")
    public static void viewSaveToImage(View view, Context context) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);

//        FileOutputStream fos = null;
//        String outpath = SaveImageUt.getDir(this) + System.currentTimeMillis() + "sharecode.jpg";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            SaveImageUt.saveImageToGallery2(context, cachebmp);
            ToastUtils.show("保存成功");
        } else {
            try {

                // SD卡根目录

                Uri insertUri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
                OutputStream outputStream = context.getContentResolver().openOutputStream(insertUri, "rw");
                if (cachebmp.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)) {
                    Log.e("保存成功", "success");
                } else {
                    Log.e("保存失败", "fail");
                }
                ToastUtils.show("保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.show("保存失败");
            }
        }


        view.destroyDrawingCache();
    }

    private static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.BLACK);
        /** 如果不设置canvas画布为白色，则生成透明 */

//        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }
}
