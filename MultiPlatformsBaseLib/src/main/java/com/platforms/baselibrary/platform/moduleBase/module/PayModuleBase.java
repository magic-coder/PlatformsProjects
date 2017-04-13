package com.platforms.baselibrary.platform.moduleBase.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.platforms.baselibrary.platform.callbackBase.CallbackBase;
import com.platforms.baselibrary.platform.moduleBase.base.ActionBase;
import com.platforms.baselibrary.platform.moduleBase.base.ModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;

/**
 * Created by nightq on 2017/4/9.
 * 模块的功能实现
 */

public abstract class PayModuleBase<T extends PlatformBase> extends ModuleBase<T> {

    public PayModuleBase (Context context, T platform, PlatformConfig config) {
        super(context, platform, config);
    }

    /**
     */
    public abstract void pay(
            Activity activity,
            PayInfo payInfo,
            CallbackBase callback);

    /**
     * 一些 sdk 需要在 onActivityResult 方法中处理回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data,
            CallbackBase callbackBase) {}

    /**
     * 微信处理回调
     * @param data
     */
    public void handleWX(
            Intent data) {}

    /**
     * 支付的参数等
     */
    public static class PayInfo extends ActionBase {

        public String info;

    }

}
