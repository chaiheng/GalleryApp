package com.example.modulebase.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.RequiresApi;


public class SharePreferenceUtils {
    public static final String FILE_NAME = "miledata";

    private static SharedPreferencesHelper mShared;

    public static void open(Context context) {
        mShared = new SharedPreferencesHelper(context, FILE_NAME);
    }
    public static boolean getBoolean(String key, boolean value) {
        if (mShared != null) {
            return mShared.getBoolean(key, value);
        }
        return false;
    }

    public static boolean putBoolean(String key, boolean value) {
        if (mShared != null) {
            return mShared.putBoolean(key, value);
        }
        return false;
    }

    public static int getInt(String key, int def) {
        if (mShared != null) {
            return mShared.getInt(key, def);
        }
        return 0;
    }

    public static boolean putInt(String key, int value) {
        if (mShared != null) {
            return mShared.putInt(key, value);
        }
        return false;
    }

    public static String getString(String key, String value) {
        if (mShared != null) {
            return mShared.getString(key, value);
        }
        return null;
    }

    public static boolean putString(String key, String value) {
        if (mShared != null) {
            return mShared.putString(key, value);
        }
        return false;
    }

    public static long getLong(String key, long value) {
        if (mShared != null) {
            return mShared.getLong(key, value);
        }
        return 0;
    }

    public static boolean putLong(String key, long value) {
        if (mShared != null) {
            return mShared.putLong(key, value);
        }
        return false;
    }

    public static float getFloat(String key, Float value) {
        if (mShared != null) {
            return mShared.getFloat(key, value);
        }
        return 0.00f;
    }

    public static boolean putFloat(String key, Float value) {
        if (mShared != null) {
            return mShared.putFloat(key, value);
        }
        return false;
    }
    /**
     * 保存数据
     *
     * @param context
     * @param key
     * @param object
     */

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void put(Context context, String key, Object object) {
        SharedPreferences.Editor editor = getEditor(context);

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        editor.apply();

    }

    /**
     * 获取数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */

    public static Object get(Context context, String key, Object defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }

        return null;
    }

    /**
     * remove key
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        try {
            SharedPreferences.Editor editor = getEditor(context);
            editor.remove(key);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否包含key
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 清空数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.clear();
        editor.apply();

    }


    public static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.edit();
    }
}
