package com.platforms.xiaominewlibrary;

import android.app.Activity;
import android.content.Context;

import com.platforms.baselibrary.platform.callbackBase.CallbackBase;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;

/**
 * Created by bigwen on 2016/12/21.
 */

public class XiaominewLoginModule extends LoginModuleBase<XiaominewPlatform> {

    public static long miUid;
    public static String miSession;
    public static String miNickname;

    /**
     * @param context
     */
    public XiaominewLoginModule(
            Context context,
            XiaominewPlatform platform,
            PlatformConfig config) {
        super(context, platform, config);
    }

    @Override
    public void login(Activity activity,
                      final CallbackBase callback) {
        MiCommplatform.getInstance().miLogin(activity, new OnLoginProcessListener() {
            @Override
            public void finishLoginProcess(int code, MiAccountInfo miAccountInfo) {
                switch (code) {
                    case MiErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS:
                        // 登陆成功
                        //获取用户的登陆后的 UID(即用户唯一标识)
                        miUid = miAccountInfo.getUid();
                        miSession = miAccountInfo.getSessionId();
                        miNickname = miAccountInfo.getNikename();
                        if (callback != null) callback.onCompleted(null);
                        break;
                    case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGIN_FAIL:
                        // 登陆失败
                        if (callback != null) callback.onFailed(null, new Exception("登陆失败"));
                        break;
                    case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_CANCEL:
                        // 取消登录
                        if (callback != null) callback.onFailed(null, new Exception("取消登录"));
                        break;
                    case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_ACTION_EXECUTED:
                        // 登录操作正在进行中
                        //("登录操作正在进行中");
                        break;
                    default:
                        // 登录失败
                        if (callback != null) callback.onFailed(null, new Exception("登陆失败"));
                        break;
                }
            }
        });
    }

}
