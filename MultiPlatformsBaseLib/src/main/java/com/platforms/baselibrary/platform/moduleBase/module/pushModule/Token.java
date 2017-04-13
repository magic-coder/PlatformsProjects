package com.platforms.baselibrary.platform.moduleBase.module.pushModule;

/**
 * Created by nightq on 2017/4/13.
 */
public class Token {
    // todo

    public String token;

    public Token (String token) {
        this.token = token;
    }

    public static Token newInstance (String token) {
        return new Token(token);
    }

}
