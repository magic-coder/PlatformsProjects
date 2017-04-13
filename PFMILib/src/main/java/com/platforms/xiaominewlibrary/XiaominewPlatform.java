package com.platforms.xiaominewlibrary;

import android.content.Context;

import com.platforms.baselibrary.platform.PlatformKeys;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.xiaomi.gamecenter.sdk.entry.MiAppType;

/**
 * Created by nightq on 2017/4/10.
 */

public class XiaominewPlatform extends PlatformBase {

    /**
     * 建议 application
     *
     * @param context
     */
    public XiaominewPlatform(Context context, PlatformConfig config) {
        super(context, config);
    }

    @Override
    protected void onCreate() {
        MiAppInfo appInfo = new MiAppInfo();
        appInfo.setAppId(mConfig.appId);
        appInfo.setAppKey(mConfig.appKey);
        appInfo.setAppType(MiAppType.offline); // 单机游戏
        MiCommplatform.Init(mContext, appInfo);
    }

    /**
     * 注册 qq 平台的模块
     */
    @Override
    protected void registerAllModules(PlatformConfig config) {
        register(LoginModuleBase.class, new XiaominewLoginModule(mContext, this, config));
    }

    @Override
    public String getName() {
        return PlatformKeys.Xiaominew;
    }
}
