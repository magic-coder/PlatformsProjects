package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

/**
 * Created by nightq on 2017/4/13.
 */
public class Event {
    // todo
    public String message;

    public Event (String message) {
        this.message = message;
    }

    public static Event newInstance (String message) {
        return new Event(message);
    }

}
