package com.example.modulebase.bean;


import android.os.Build;
import android.util.ArrayMap;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class BaseRequestBean {


    public String action;//方法名
    public ArrayMap<String, Object> object = new ArrayMap<>();//参数集合

    public ArrayMap<String, Object> getObject() {
        return object;
    }

    public void setObject(ArrayMap<String, Object> object) {
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public BaseRequestBean setAction(String action) {
        this.action = action;
        return this;
    }

    public BaseRequestBean setRequestParams(String key, Object value) {
        this.object.put(key, value);
        return this;
    }


}
