package com.platforms.baselibrary.platform.responseBase;

/**
 * Created by nightq on 2017/4/10.
 * 平台的某个模块的功能直接返回的结果。
 */

public class Response<D> {

    D result;

    public Response (D d) {
        this.result = d;
    }

}
