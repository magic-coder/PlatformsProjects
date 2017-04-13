package com.platforms.baselibrary.utils.sharePreferenceUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.platforms.baselibrary.platform.platformBase.PlatformBase;

import java.lang.ref.SoftReference;
import java.security.InvalidParameterException;
import java.util.HashMap;

/**
 * Created by nightq on 2017/3/24.
 * 为了方便，每个平台单独存放内容。
 */

public class SharedPreferencesUtil {

    /**
     * 平台的 sp 缓存
     */
    public static HashMap<String, SoftReference<SharedPreferencesBase>> spCaspCache = new HashMap<>();

    /**
     * 获取 本地存储帮助类
     * @param platform
     * @return
     */
    public static SharedPreferencesBase get (PlatformBase platform) {
        return get(platform.getContext(), platform.getName());
    }

    /**
     * 获取 本地存储帮助类
     * @param context
     * @param platform
     * @return
     */
    private static SharedPreferencesBase get (Context context, String platform) {
        String key = getKeyForPlatform(platform);
        SoftReference<SharedPreferencesBase> softReference = spCaspCache.get(key);
        SharedPreferencesBase sharedPreferencesBase;
        if (softReference != null
                && (sharedPreferencesBase =softReference.get()) != null) {
            return sharedPreferencesBase;
        }
        return createSharedPreferencesBase(context, key);
    }

    /**
     * 生成对应 key 的存储文件帮助类
     * @param key
     * @return
     */
    private static synchronized SharedPreferencesBase createSharedPreferencesBase (
            Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferencesBase sharedPreferencesBase = SharedPreferencesBase.newInstance(sharedPreferences);
        spCaspCache.put(key, new SoftReference<>(sharedPreferencesBase));
        return sharedPreferencesBase;
    }

    /**
     * 获取平台对应的 key
     * @return
     */
    private static String getKeyForPlatform (String platform) {
        if (TextUtils.isEmpty(platform)) {
            throw new InvalidParameterException("请指定平台后再获取本地存储");
        }
        return "PlatformsLibrary_" + platform + "_SP";
    }

}
