package com.example.huaweilibrary;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.huawei.android.pushagent.api.PushEventReceiver;
import com.platforms.baselibrary.platform.PlatformKeys;
import com.platforms.baselibrary.platform.PlatformUtil;
import com.platforms.baselibrary.platform.moduleBase.module.pushModule.Message;
import com.platforms.baselibrary.platform.moduleBase.module.pushModule.Token;
import com.platforms.baselibrary.utils.LogDebug;

/**
 * Created by chriswong on 8/20/16.
 */
public class HWPushReceiver extends PushEventReceiver {

    @Override
    public void onToken(Context context, String token, Bundle extras){
        PlatformUtil.on(PlatformKeys.HuaWei).usePush().onToken(Token.newInstance(token));
    }

    /**
     * 透传消息
     * @return
     */
    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        String content = null;
        try {
            content = new String(msg, "UTF-8");
            LogDebug.e("onPushMsg", content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        PlatformUtil.on(PlatformKeys.HuaWei).usePush()
                .onMessage(Message.newInstance(content));
        return false;
    }

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
            int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
            if (0 != notifyId) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(notifyId);
            }
            String content = "收到通知附加消息： " + extras.getString(BOUND_KEY.pushMsgKey);
            LogDebug.e("onEvent", content+"   tab="+extras.getString("tab"));
        } else if (Event.PLUGINRSP.equals(event)) {
            final int TYPE_LBS = 1;
            final int TYPE_TAG = 2;
            int reportType = extras.getInt(BOUND_KEY.PLUGINREPORTTYPE, -1);
            boolean isSuccess = extras.getBoolean(BOUND_KEY.PLUGINREPORTRESULT, false);
            String message = "";
            if (TYPE_LBS == reportType) {
                message = "LBS report result :";
            } else if(TYPE_TAG == reportType) {
                message = "TAG report result :";
            }
            LogDebug.e("onEvent", message + isSuccess);
        }
        PlatformUtil.on(PlatformKeys.HuaWei).usePush()
                .onEvent(com.platforms.baselibrary.platform.moduleBase.module.pushModule.Event.newInstance("" + event));
        super.onEvent(context, event, extras);
    }


}
