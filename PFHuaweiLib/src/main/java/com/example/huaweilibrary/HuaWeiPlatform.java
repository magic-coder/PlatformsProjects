package com.example.huaweilibrary;

import android.content.Context;

import com.platforms.baselibrary.platform.PlatformKeys;
import com.platforms.baselibrary.platform.moduleBase.module.pushModule.PushModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;

/**
 * Created by nightq on 2017/4/10.
 */

public class HuaWeiPlatform extends PlatformBase {

    /**
     * 建议 application
     *
     * @param context
     */
    public HuaWeiPlatform(Context context, PlatformConfig config) {
        super(context, config);
    }

    @Override
    protected void onCreate() {

    }

    /**
     * 注册 qq 平台的模块
     */
    @Override
    protected void registerAllModules(PlatformConfig config) {
        register(PushModuleBase.class, new HuaWeiPushModule(mContext, this, config));
    }

    @Override
    public String getName() {
        return PlatformKeys.HuaWei;
    }
}
