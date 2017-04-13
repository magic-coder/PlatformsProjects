package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

/**
 * 用于 封装后的 平台库进行 推送模块的 接收器实现
 */
interface PushReceiverImpl {

    /**
     * 收到 token
     * @param pushModuleBase
     * @param token
     */
    void onToken (PushModuleBase pushModuleBase, Token token);

    /**
     * 收到推送
     * @param pushModuleBase
     * @param message
     */
    void onMessage (PushModuleBase pushModuleBase, Message message);

    /**
     * 收到事件
     * @param pushModuleBase
     * @param event
     */
    void onEvent (PushModuleBase pushModuleBase, Event event);

}
