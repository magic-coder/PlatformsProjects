package com.platforms.baselibrary.platform.platformBase;

import android.content.Context;

import com.platforms.baselibrary.platform.moduleBase.base.ModuleBase;
import com.platforms.baselibrary.platform.moduleBase.module.ADModuleBase;
import com.platforms.baselibrary.platform.moduleBase.module.LoginModuleBase;
import com.platforms.baselibrary.platform.moduleBase.module.PayModuleBase;
import com.platforms.baselibrary.platform.moduleBase.module.pushModule.PushModuleBase;
import com.platforms.baselibrary.platform.moduleBase.module.ShareModuleBase;

import java.util.HashMap;

/**
 * Created by nightq on 2017/4/9.
 */

public abstract class PlatformBase {

    /**
     *
     */
    protected Context mContext;

    /**
     * 需要初始化这个平台具备的能力
     * key：{@link LoginModuleBase}
     *      应该是一个功能模块的基类。为了不和平台耦合。
     * value：@link com.example.wechatlibrary.WXLoginModule
     *        @link com.example.qqlib.QQLoginModule
     *      应该是一个功能模块的实现类，为了调用实现了的方法
     */
    protected HashMap<Class<? extends ModuleBase>, ModuleBase> allModules;

    protected PlatformConfig mConfig;

    /**
     * 是否默认注册全部模块
     * 不用全部注册
     */
    public static boolean DefaultRegisterAllModule = false;

    /**
     * 建议 application
     * @param context
     */
    public PlatformBase(Context context, PlatformConfig config) {
        this.mContext = context;
        this.mConfig = config;
        allModules = new HashMap<>();
        onCreate();
        if (DefaultRegisterAllModule) {
            registerAllModules(config);
        }
    }

    /**
     * 初始化一些 平台配置
     */
    protected abstract void onCreate ();

    /**
     * 注册模块
     * 在 onCreate 之后调用
     */
    protected abstract void registerAllModules(PlatformConfig config);

    public PlatformConfig getConfig() {
        return mConfig;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * @param key：{@link LoginModuleBase}
     *      应该是一个功能模块的基类。为了不和平台耦合。
     * @return
     */
    private <T extends ModuleBase> T module(Class<T> key) {
        if (!isValid(key)) {
            throw new RuntimeException("没有" + key + "模块");
        }
        return (T) allModules.get(key);
    }

    /**
     * 是否具备某种能力
     * @param key：{@link LoginModuleBase}
     *      应该是一个功能模块的基类。为了不和平台耦合。
     * @return
     */
    public boolean isValid(Class key) {
        return allModules.containsKey(key);
    }

    /**
     * 注册某种功能
     * @param classType：{@link LoginModuleBase}
     *      应该是一个功能模块的基类。为了不和平台耦合。
     * @param module：@link com.example.wechatlibrary.WXLoginModule}
     *              @link com.example.qqlib.QQLoginModule}
     *      应该是一个功能模块的实现类，为了调用实现了的方法
     */
    public <F extends ModuleBase> void register(Class<F> classType, F module) {
        if (classType == null
                || allModules.containsKey(classType)) {
            return;
        }
        allModules.put(classType, module);
    }

    /**
     * 获取平台名字
     */
    public abstract String getName();

    /**
     * 这里不太确定。这样更方便，但是耦合性高一点
     * 使用登陆模块
     */
    public LoginModuleBase useLogin () {
        return module(LoginModuleBase.class);
    }

    /**
     * 使用分享模块
     */
    public ShareModuleBase useShare () {
        return module(ShareModuleBase.class);
    }

    /**
     * 使用广告模块
     */
    public ADModuleBase useAD () {
        return module(ADModuleBase.class);
    }

    /**
     * 使用支付模块
     */
    public PayModuleBase usePay () {
        return module(PayModuleBase.class);
    }

    /**
     * 使用推送模块
     */
    public PushModuleBase usePush () {
        return module(PushModuleBase.class);
    }
    // todo 没增加一个模块需要在这里增加 使用方法
}

