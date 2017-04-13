package com.platforms.baselibrary.platform.callbackBase;

import com.platforms.baselibrary.platform.responseBase.Response;

/**
 * Created by nightq on 2017/4/9.
 * 登陆回调
 */

public interface CallbackBase<R extends Response> {

    void onCompleted (R response);

    void onFailed (R response, Throwable throwable);

    void onCancel();

}
