package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

/**
 * Created by nightq on 2017/4/13.
 */
public class Message {
    // todo

    public String message;

    public Message (String message) {
        this.message = message;
    }

    public static Message newInstance (String message) {
        return new Message(message);
    }
}
