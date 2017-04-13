package com.platforms.qqlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.platforms.baselibrary.platform.callbackBase.CallbackBase;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.platforms.baselibrary.platform.responseBase.Response;
import com.platforms.baselibrary.utils.Hashon;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.HashMap;

public class QQLoginModule extends LoginModuleBase<QQPlatform> {

    /**
     * 只使用 {@link com.tencent.tauth.Tencent}
     * 他的里面 包含了 {@link com.tencent.connect.auth.QQAuth}
     */
	private Tencent mTencent;

    /**
     * @param context
     */
    public QQLoginModule(
            Context context,
            QQPlatform platform,
            PlatformConfig config) {
        super(context, platform, config);
        this.mTencent = platform.getTencent();
    }

    @Override
    public void login(Activity activity, CallbackBase callback) {
        if(mTencent.isSessionValid()){
            mTencent.logout(activity);
        }
        mTencent.login(activity, "all", convertCallback(callback));
    }

    @Override
    public void logout(Activity activity, CallbackBase callback) {
        mTencent.logout(activity);
        if (callback != null) {
            callback.onCompleted(null);
        }
    }

    @Override
    public boolean isLogin(Activity activity) {
        return mTencent.isSessionValid();
    }

    /**
     * qq 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data,
            CallbackBase callback){
        Tencent.onActivityResultData(requestCode, resultCode, data, convertCallback(callback));
    }

    /**
     * 转换 callback
     * @param callback
     * @return
     */
    private IUiListener convertCallback (final CallbackBase callback) {
        return new IUiListener() {
            @Override
            public void onComplete(Object arg0) {
                HashMap<String, Object> map = new Hashon().fromJson(arg0.toString());
//                    JSONObject json = new JSONObject(arg0.toString());
//                    String openid = json.getString("openid");
//                    String access_token = json.getString("access_token");
                Response response = new Response(map);
                if (callback != null) {
                    callback.onCompleted(response);
                }
            }

            @Override
            public void onError(UiError uiError) {
                if (callback != null) {
                    callback.onFailed(null, (uiError == null ? null :
                            new Exception(" code = " + uiError.errorCode
                                    + " msg = " + uiError.errorMessage
                                    + " detail = " + uiError.errorDetail)));
                }
            }

            @Override
            public void onCancel() {
                if (callback != null) {
                    callback.onCancel();
                }
            }
        };
    }

}