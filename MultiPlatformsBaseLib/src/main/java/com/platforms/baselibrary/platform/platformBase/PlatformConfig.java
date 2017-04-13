package com.platforms.baselibrary.platform.platformBase;

/**
 * Created by nightq on 2017/4/9.
 * 单个平台配置
 */

public class PlatformConfig {

    public String appId;
    public String appKey;


    public PlatformConfig () {
    }

    public PlatformConfig (String appId) {
        this.appId = appId;
    }

    public PlatformConfig (String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

}

