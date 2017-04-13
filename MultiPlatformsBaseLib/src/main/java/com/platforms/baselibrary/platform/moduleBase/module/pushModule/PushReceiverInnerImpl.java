package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

/**
 * 用于第三方 推送接收器调用 封装基础库的 内部接口
 */
interface PushReceiverInnerImpl {

    /**
     * 收到 token
     * @param token
     */
    void onToken(Token token);

    /**
     * 收到推送
     * @param message
     */
    void onMessage(Message message);

    /**
     * 收到事件
     * @param event
     */
    void onEvent(Event event);

}
