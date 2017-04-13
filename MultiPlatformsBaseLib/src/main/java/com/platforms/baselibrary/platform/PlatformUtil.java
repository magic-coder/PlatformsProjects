package com.platforms.baselibrary.platform;

import android.content.Context;

import com.platforms.baselibrary.platform.platformBase.PlatformBase;

import java.util.HashMap;

/**
 * Created by nightq on 2017/4/9.
 * 平台管理
 *
 */

public class PlatformUtil {

    /**
     * 实例
     */
    static PlatformUtil sInstance;

    /**
     *
     */
    protected Context mContext;

    /**
     * 需要初始化有哪些平台
     * kay：{@link PlatformKeys}
     */
    protected HashMap<String, PlatformBase> allPlatforms;

    /**
     * 默认平台
     */
    protected PlatformBase defaultPlatform;

    /**
     * 建议 application
     * @param context
     */
    private PlatformUtil(Context context) {
        this.mContext = context;
        allPlatforms = new HashMap<>();
    }

    /**
     * 初始化，没有任何平台
     * @param context
     */
    public static void init(Context context) {
        if (sInstance == null) {
            sInstance = new PlatformUtil(context);
        }
    }

    /**
     * 使用新的配置初始化
     * @param context
     */
    public static void init(Context context,
                            PlatformsConfigBase config) {
        init(context);
        // 直接覆盖
        if (config != null && config.platforms != null) {
            sInstance.allPlatforms = config.platforms;
        }
    }

    /**
     */
    public static PlatformUtil getInstance() {
        if (sInstance == null) {
            throw new ExceptionInInitializerError("没有初始化");
        }
        return sInstance;
    }

    /**
     * 是否可以访问
     * @return
     */
    public boolean isValid(String key) {
        return allPlatforms.containsKey(key);
    }

    /**
     * 获取默认平台
     */
    public static PlatformBase getDefault() {
        return getInstance().defaultPlatform;
    }

    /**
     * 在哪个平台上
     * @return
     */
    public static PlatformBase on(String platformName) {
        if (!getInstance().isValid(platformName)) {
            throw new RuntimeException("没有" + platformName + "平台");
        }
        return getInstance().allPlatforms.get(platformName);
    }

    /**
     * 注册
     *
     * @return
     */
    public void register(String key, PlatformBase data) {
        if (allPlatforms.containsKey(key)
                || data == null) {
            return;
        }
        defaultPlatform = data;
        allPlatforms.put(key, data);
    }

}

