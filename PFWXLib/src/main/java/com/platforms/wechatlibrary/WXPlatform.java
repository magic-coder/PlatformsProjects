package com.platforms.wechatlibrary;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.platforms.baselibrary.platform.PlatformKeys;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by nightq on 2017/4/10.
 */

public class WXPlatform extends PlatformBase implements IWXAPIEventHandler {

    /**
     * 登陆调用
     */
    protected static final String StateLogin = "login";

    /**
     * 分享调用
     */
    protected static final String StateShare = "share";

    /**
     * 支付调用
     */
    protected static final String StatePay = "pay";

    IWXAPI mApi;

    /**
     * 建议 application
     *
     * @param context
     */
    public WXPlatform(Context context, PlatformConfig config) {
        super(context, config);
    }

    /**
     * 公用，提供给 module 调用
     * @return
     */
    public IWXAPI getApi () {
        return mApi;
    }

    @Override
    protected void onCreate() {
        if (mApi != null) {
            return;
        }
        mApi = WXAPIFactory.createWXAPI(mContext, mConfig.appId, true);
        if (!mApi.isWXAppInstalled()) {
            Toast.makeText(mContext, "请先安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /**
     * 注册 qq 平台的模块
     */
    @Override
    protected void registerAllModules(PlatformConfig config) {
        register(LoginModuleBase.class, new WXLoginModule(mContext, this, config));
    }

    @Override
    public String getName() {
        return PlatformKeys.Wechat;
    }

    /**
     * 微信需要 单独 统一 处理回调的地方。
     * 来自于 WXEntryActivity 的回调
     */
    public void handleResponse (Intent intent) {
        mApi.handleIntent(intent, this);
    }

    /**
     * todo 暂时没有用到
     * @param req
     */
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    /**
     *
     useLogin().handleWX(intent);
     useShare().handleWX(intent);
     usePay().handleWX(intent);
     * @param resp
     */
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                resp.toString();
//                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                result = R.string.errcode_deny;
                break;
            default:
//                result = R.string.errcode_unknown;
                break;
        }
    }

}
