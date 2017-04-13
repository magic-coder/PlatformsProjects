package com.platforms.baselibrary.platform.moduleBase.base;

import android.content.Context;

import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;

/**
 * Created by nightq on 2017/4/9.
 * 某个平台的某个模块的功能；
 * 比如：微信平台的登陆模块的各种功能：登陆，退出，判断是否登陆
 *
 * 后期可以加上 功能的禁用开启等功能
 */

public abstract class ModuleBase<T extends PlatformBase> {

    protected PlatformConfig mConfig;
    protected T mPlatform;
    protected Context mContext;

    public ModuleBase (Context context, T platform, PlatformConfig config) {
        this.mContext = context;
        this.mPlatform = platform;
        this.mConfig = config;
    }

}
