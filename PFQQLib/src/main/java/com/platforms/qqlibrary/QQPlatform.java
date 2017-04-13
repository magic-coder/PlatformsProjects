package com.platforms.qqlibrary;

import android.content.Context;

import com.platforms.baselibrary.platform.PlatformKeys;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.tencent.tauth.Tencent;

/**
 * Created by nightq on 2017/4/10.
 */

public class QQPlatform extends PlatformBase {


    /**
     * 只使用 {@link com.tencent.tauth.Tencent}
     * 他的里面 包含了 {@link com.tencent.connect.auth.QQAuth}
     */
    private Tencent mTencent;

    /**
     * 建议 application
     *
     * @param context
     */
    public QQPlatform(Context context, PlatformConfig config) {
        super(context, config);
    }

    public Tencent getTencent() {
        return mTencent;
    }

    @Override
    protected void onCreate() {
        mTencent = Tencent.createInstance(mConfig.appId, mContext);
    }

    /**
     * 注册 qq 平台的模块
     */
    @Override
    protected void registerAllModules(PlatformConfig config) {
        register(LoginModuleBase.class, new QQLoginModule(mContext, this, config));
    }

    @Override
    public String getName() {
        return PlatformKeys.QQ;
    }
}
