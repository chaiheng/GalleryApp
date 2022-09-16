package com.example.modulebase.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.format.DateUtils;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveImageUt {

    /**
     * android 11及以上保存图片到相册
     *
     * @param context
     * @param image
     */
    @SuppressLint("NewApi")
    public static void saveImageToGallery2(Context context, Bitmap image) {
        Long mImageTime = System.currentTimeMillis();
        String imageDate = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(mImageTime));
        String SCREENSHOT_FILE_NAME_TEMPLATE = "winetalk_%s.png";//图片名称，以"winetalk"+时间戳命名
        String mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, imageDate);

        final ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES
                + File.separator + "winetalk"); //Environment.DIRECTORY_SCREENSHOTS:截图,图库中显示的文件夹名。"dh"
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, mImageFileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        values.put(MediaStore.MediaColumns.DATE_ADDED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_MODIFIED, mImageTime / 1000);
        values.put(MediaStore.MediaColumns.DATE_EXPIRES, (mImageTime + DateUtils.DAY_IN_MILLIS) / 1000);
        values.put(MediaStore.MediaColumns.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        final Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            // First, write the actual data for our screenshot
            try (OutputStream out = resolver.openOutputStream(uri)) {
                if (!image.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                    throw new IOException("Failed to compress");
                }
            }
            // Everything went well above, publish it!
            values.clear();
            values.put(MediaStore.MediaColumns.IS_PENDING, 0);
            values.putNull(MediaStore.MediaColumns.DATE_EXPIRES);
            resolver.update(uri, values, null, null);
        } catch (IOException e) {
            resolver.delete(uri, null);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void saveImgToMediaStore(Context context, String fileName) {

        long startTime = System.currentTimeMillis();
        ContentValues values = new ContentValues();
        String name = startTime + "-photo.jpg";
        values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        Uri item = resolver.insert(collection, values);

        try (ParcelFileDescriptor pfd = resolver.openFileDescriptor(item, "w", null)) {
            // Write data into the pending image.
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
            ParcelFileDescriptor.AutoCloseOutputStream outputStream = new ParcelFileDescriptor.AutoCloseOutputStream(pfd);
            BufferedOutputStream bot = new BufferedOutputStream(outputStream);
            byte[] bt = new byte[2048];
            int len;
            while ((len = bin.read(bt)) >= 0) {
                bot.write(bt, 0, len);
                bot.flush();
            }
            bin.close();
            bot.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now that we're finished, release the "pending" status, and allow other apps
        // to view the image.
        values.clear();
        values.put(MediaStore.Images.Media.IS_PENDING, 0);
        resolver.update(item, values, null, null);
        //打印写入时间
    }

    /**
     * AndroidQ 保存视频到媒体库
     *
     * @param context  Context
     * @param fileName 要刷新的视频路径
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static void saveVideoToMediaStore(Context context, String fileName) {

        long startTime = System.currentTimeMillis();
        ContentValues values = new ContentValues();
        String name = startTime + "-little-download-video.mp4";
        values.put(MediaStore.Video.Media.DISPLAY_NAME, name);
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Video.Media.IS_PENDING, 1);

        ContentResolver resolver = context.getContentResolver();
        Uri collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        Uri item = resolver.insert(collection, values);

        try (ParcelFileDescriptor pfd = resolver.openFileDescriptor(item, "w", null)) {
            // Write data into the pending video.
            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
            ParcelFileDescriptor.AutoCloseOutputStream outputStream = new ParcelFileDescriptor.AutoCloseOutputStream(pfd);
            BufferedOutputStream bot = new BufferedOutputStream(outputStream);
            byte[] bt = new byte[2048];
            int len;
            while ((len = bin.read(bt)) >= 0) {
                bot.write(bt, 0, len);
                bot.flush();
            }
            bin.close();
            bot.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        values.clear();
        values.put(MediaStore.Video.Media.IS_PENDING, 0);
        resolver.update(item, values, null, null);
        //打印写入时间
    }

    public static String getDir(Context context) {
        String dir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dir = context.getExternalFilesDir("") + File.separator + "Media" + File.separator;
        } else {
            dir = Environment.getExternalStorageDirectory() + File.separator + "DCIM"
                    + File.separator + "Camera" + File.separator;
        }
        File file = new File(dir);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
        return dir;
    }

    public static String getDir(Context context, String path) {
        String dir;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dir = context.getExternalFilesDir("") + path;
        } else {
            dir = Environment.getExternalStorageDirectory() + path;
        }
        File file = new File(dir);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
        return dir;
    }

}
