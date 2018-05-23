package com.example.nvu7.readrss.module;

import com.example.nvu7.readrss.api.RssAPIUtils;

/**
 * Created by nvu7 on 5/23/2018.
 */

public class RssService {
    private static RssService mInstance;

    public static synchronized RssService getInstance()
    {
        if(mInstance == null){
            mInstance = new RssService();
        }
        return mInstance;
    }
    public String get24h()
    {
        return RssAPIUtils.read24H();
    }
}
