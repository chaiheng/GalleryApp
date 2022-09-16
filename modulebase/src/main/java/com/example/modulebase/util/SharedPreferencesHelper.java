package com.example.modulebase.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private Context mContext;
    private SharedPreferences mShared;
    private String mName;

    public SharedPreferencesHelper(Context context, String name) {
        mContext = context;
        mName = name;
        mShared = mContext.getSharedPreferences(mName, Context.MODE_PRIVATE);
    }

    public boolean getBoolean(String key, boolean value) {
        if (mShared != null) {
            return mShared.getBoolean(key, value);
        }
        return false;
    }

    public boolean putBoolean(String key, boolean value) {
        if (mShared != null) {
            SharedPreferences.Editor editor = mShared.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }
        return false;
    }

    public int getInt(String key, int value) {
        if (mShared != null) {
            return mShared.getInt(key, value);
        }
        return 0;
    }

    public boolean putInt(String key, int value) {
        if (mShared != null) {
            SharedPreferences.Editor editor = mShared.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }

    public String getString(String key, String value) {
        if (mShared != null) {
            return mShared.getString(key, value);
        }
        return null;
    }

    public boolean putString(String key, String value) {
        if (mShared != null) {
            SharedPreferences.Editor editor = mShared.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public long getLong(String key, long value) {
        if (mShared != null) {
            return mShared.getLong(key, value);
        }
        return 0;
    }

    public boolean putLong(String key, long value) {
        if (mShared != null) {
            SharedPreferences.Editor editor = mShared.edit();
            editor.putLong(key, value);
            return editor.commit();
        }
        return false;
    }

    public float getFloat(String key, float value) {
        if (mShared != null) {
            return mShared.getFloat(key, value);
        }
        return 0.00f;
    }

    public boolean putFloat(String key, float value) {
        if (mShared != null) {
            SharedPreferences.Editor editor = mShared.edit();
            editor.putFloat(key, value);
            return editor.commit();
        }
        return false;
    }

    public boolean contains(String key) {
        if (mShared != null) {
            return mShared.contains(key);
        }
        return false;
    }
}
