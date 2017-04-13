package com.platforms.baselibrary.platform.moduleBase.module;

import android.app.Activity;
import android.content.Context;

import com.platforms.baselibrary.platform.callbackBase.CallbackBase;
import com.platforms.baselibrary.platform.moduleBase.base.ModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;

/**
 * Created by nightq on 2017/4/9.
 * 模块的功能实现
 */

public abstract class ADModuleBase<T extends PlatformBase> extends ModuleBase<T> {

    public ADModuleBase (Context context, T platform, PlatformConfig config) {
        super(context, platform, config);
    }

    /**
     */
    public abstract void getAD(Activity activity, CallbackBase callback);

}
