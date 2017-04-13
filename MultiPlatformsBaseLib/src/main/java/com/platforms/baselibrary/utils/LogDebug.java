package com.platforms.baselibrary.utils;

import android.util.Log;

/**
 * Created by nightq on 2017/4/13.
 */

public class LogDebug {

    private static final String Tag = "BaseLibrary";

    /**
     * @param msg The message you would like logged.
     */
    public static int e(String msg) {
        return Log.e(Tag, msg);
    }

    /**
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg) {
        return Log.e(tag, msg);
    }
}
