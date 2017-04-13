package com.platforms.wechatlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.platforms.baselibrary.platform.callbackBase.CallbackBase;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WXLoginModule extends LoginModuleBase<WXPlatform> {


    protected IWXAPI mApi;
    List<CallbackBase> callbacks;

    /**
     * @param context
     */
    public WXLoginModule(
            Context context,
            WXPlatform platform,
            PlatformConfig config) {
        super(context, platform, config);
        this.mApi = platform.getApi();
        callbacks = new CopyOnWriteArrayList<>();
    }

    /**
     * 注册
     */
    private void registerCallback (CallbackBase callback) {
        if (!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    /**
     * 取消注册
     */
    private void unregisterCallback (CallbackBase callback) {
        if (callbacks.contains(callback)) {
            callbacks.remove(callback);
        }
    }

    @Override
    public void login(Activity activity, CallbackBase callback) {
        registerCallback(callback);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = WXPlatform.StateLogin;
        mApi.sendReq(req);
    }

    @Override
    public void handleWX(Intent data) {
        for (CallbackBase callbackBase : callbacks) {
            callbackBase.onCompleted(null);
        }
    }
}