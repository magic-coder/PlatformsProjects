package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

import android.content.Context;

import com.platforms.baselibrary.platform.moduleBase.base.ModuleBase;
import com.platforms.baselibrary.platform.platformBase.PlatformBase;
import com.platforms.baselibrary.platform.platformBase.PlatformConfig;
import com.platforms.baselibrary.utils.sharePreferenceUtil.SharedPreferencesBase;
import com.platforms.baselibrary.utils.sharePreferenceUtil.SharedPreferencesUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by nightq on 2017/4/9.
 * 模块的功能实现
 * 推送功能需要：推送和设置
 */

public abstract class PushModuleBase<T extends PlatformBase>
        extends ModuleBase<T> implements PushReceiverInnerImpl {

    /**
     * 本地存储 推送token key
     */
    public static final String SP_Token_Key = "Push_Token";

    /**
     * 本地数据，可以考虑去掉
     */
    protected SharedPreferencesBase mLocalData;
    /**
     * 管理推送接收器
     */
    protected List<PushReceiverImpl> receivers;

    public PushModuleBase(Context context, T platform, PlatformConfig config) {
        super(context, platform, config);
        mLocalData = SharedPreferencesUtil.get(platform);
        receivers = new CopyOnWriteArrayList<>();
    }

    public SharedPreferencesBase getLocalData() {
        return mLocalData;
    }

    /**
     * 取推送token
     * @return token
     */
    public String getToken () {
        return mLocalData.get(SP_Token_Key, null, String.class);
    }

    /**
     * 存推送token
     */
    public void setToken (String token) {
        mLocalData.put(SP_Token_Key, token, String.class);
    }

    /**
     * 注册推送接收器
     * @warn 注意，这是强引用，请不要内存泄漏。
     *
     * @param receiver
     */
    public void register(PushReceiverImpl receiver) {
        if (receivers.contains(receiver)) {
            return;
        }
        receivers.add(receiver);
    }

    /**
     * 取消注册推送接收器
     *
     * @param receiver
     */
    public void unregister(PushReceiverImpl receiver) {
        if (!receivers.contains(receiver)) {
            return;
        }
        receivers.remove(receiver);
    }

    @Override
    public void onToken(Token token) {
        for (PushReceiverImpl receiverImpl : receivers) {
            receiverImpl.onToken(this, token);
        }
    }

    @Override
    public void onMessage(Message message) {
        for (PushReceiverImpl receiverImpl : receivers) {
            receiverImpl.onMessage(this, message);
        }
    }

    @Override
    public void onEvent(Event event) {
        for (PushReceiverImpl receiverImpl : receivers) {
            receiverImpl.onEvent(this, event);
        }
    }

}
