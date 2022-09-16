package com.example.modulebase.extend;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;

import com.example.modulebase.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class ToolbarActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T binding;
    protected ViewGroup mContainer;
    protected LinearLayout top_ll;
    protected ConstraintLayout clTitle;
    protected ImageView ivBack;
    protected TextView tvTitle;
    protected View titleLine;
    private TextView mTvTitleSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setContentView() {
        initContentView();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initContentView() {
        super.setContentView(R.layout.activity_toolbar);
        mContainer = findViewById(R.id.container);
        top_ll = findViewById(R.id.toolbar_top_ll);
        clTitle = findViewById(R.id.cl_toolbar_title);
        mTvTitleSub = findViewById(R.id.toolbar_tv_title_sub);
        ivBack = findViewById(R.id.iv_toolbar_back);
        tvTitle = findViewById(R.id.toolbar_tv_title);
        titleLine = findViewById(R.id.title_bottom_line);
        ivBack.setOnClickListener(v -> onIvBackClick());
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> aClass = (Class<T>) superclass.getActualTypeArguments()[0];
        try {
            for (Method method : aClass.getMethods()) {
                System.out.println("Methods_debug: " + method.getName());
            }
            Method method = aClass.getMethod("inflate", LayoutInflater.class,
                    ViewGroup.class, boolean.class);
            binding = (T) method.invoke(null, getLayoutInflater(), mContainer, true);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void onIvBackClick() {
        finish();
    }

    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
        visTopLayout();
    }

    public void setTitle(int resId) {
        tvTitle.setText(resId);
        visTopLayout();
    }

    private void visTopLayout() {
        if (null != top_ll) {
            top_ll.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleSub(String title, View.OnClickListener listener) {
        setTitleSub(title, 0, listener);
        visTopLayout();
    }

    /**
     * 右侧按钮
     *
     * @param title
     * @param colorRes
     * @param listener
     */
    public void setTitleSub(String title, @ColorInt int colorRes, View.OnClickListener listener) {
        if (null != mTvTitleSub) {
            mTvTitleSub.setVisibility(View.VISIBLE);
            if (colorRes != 0) {
                mTvTitleSub.setTextColor(colorRes);
            }
            mTvTitleSub.setText(title);
            if (null != listener) {
                mTvTitleSub.setOnClickListener(listener);
            }
        }
    }

    /**
     * 左侧返回按钮
     *
     * @param res
     * @param listener
     */
    public void setBack(int res, View.OnClickListener listener) {
        if (null != ivBack) {
            ivBack.setImageResource(res);
            if (null != listener) {
                ivBack.setOnClickListener(listener);
            }
        }
    }

    /**
     * 左侧返回按钮
     *
     * @param res
     */
    public void setBack(int res) {
        if (null != ivBack) {
            ivBack.setImageResource(res);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
