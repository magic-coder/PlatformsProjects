package com.platforms.baselibrary.utils.sharePreferenceUtil;

import android.content.SharedPreferences;

import java.util.Date;
import java.util.Map;

/**
 * Created by nightq on 2017/3/24.
 */

public class SharedPreferencesBase {
    private SharedPreferences sharedPreferences;

    private SharedPreferencesBase(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     *
     * @param sharedPreferences
     * @return
     */
    public static SharedPreferencesBase newInstance (SharedPreferences sharedPreferences) {
        return new SharedPreferencesBase(sharedPreferences);
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public <T> void put(String key, T object, Class<T> tClass) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (tClass == String.class) {
            editor.putString(key, (String) object);
        } else if (tClass == Integer.class) {
            editor.putInt(key, (Integer) object);
        } else if (tClass == Boolean.class) {
            editor.putBoolean(key, (Boolean) object);
        } else if (tClass == Float.class) {
            editor.putFloat(key, (Float) object);
        } else if (tClass == Long.class) {
            editor.putLong(key, (Long) object);
        } else if (tClass == Date.class) {
            if (object == null) {
                remove(key);
                return;
            } else {
                editor.putLong(key, ((Date)object).getTime());
            }
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */
    /**
     * @return
     */

    public <T> T get(String key, T defaultObject, Class<T> tClass) {
        if (tClass == String.class) {
            return (T) sharedPreferences.getString(key, (String) defaultObject);
        } else if (tClass == Integer.class) {
            return (T) (Integer) sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (tClass == Boolean.class) {
            return (T) (Boolean) sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (tClass == Float.class) {
            return (T) (Float) sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (tClass == Long.class) {
            return (T) (Long) sharedPreferences.getLong(key, (Long) defaultObject);
        } else if (tClass == Date.class) {
            long time = sharedPreferences.getLong(key, 0l);
            if (time != 0) {
                return (T) new Date(time);
            }
            return defaultObject;
        } else {
            return (T) sharedPreferences.getString(key, null);
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * 清除所有的数据
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}
