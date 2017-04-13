package com.example.huaweilibrary;

import android.content.Context;
import android.text.TextUtils;

import com.huawei.android.pushagent.PushManager;
import com.platforms.baselibrary.platform.moduleBase.module.pushModule.PushModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.platforms.baselibrary.utils.LogDebug;

/**
 * Created by bigwen on 2016/12/21.
 */

public class HuaWeiPushModule extends PushModuleBase<HuaWeiPlatform> {

    /**
     * @param context
     */
    public HuaWeiPushModule(
            Context context,
            HuaWeiPlatform platform,
            PlatformConfig config) {
        super(context, platform, config);
        init();
    }

    /**
     * 初始化
     */
    private void init () {
        // 获取客户端AccessToken,获取之前请先确定该应用（包名）已经在开发者联盟上创建成功，并申请、审核通过Push权益
        //token共有32位，前16位和手机的IMEI相关，后16位和应用的包名相关
        String regId = getToken();
        //保证每次启动只获取一次
        if (TextUtils.isEmpty(regId)) {
            PushManager.requestToken(mContext);
            LogDebug.e("HuaWeiPushModule", "enableReceiveNotifyMsg : ");
            PushManager.enableReceiveNotifyMsg(mContext, true);
            LogDebug.e("HuaWeiPushModule", "enableReceiveNormalMsg : ");
            PushManager.enableReceiveNormalMsg(mContext, true);
        }
    }

}
