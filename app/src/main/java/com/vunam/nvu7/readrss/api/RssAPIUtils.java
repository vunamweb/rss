package com.vunam.nvu7.readrss.api;

import com.vunam.nvu7.readrss.network.NetworkUtils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by nvu7 on 5/23/2018.
 */

public class RssAPIUtils {
    public static XmlPullParser readRss24H(String url)  {
        return NetworkUtils.getInstance().getRss(url);
        //return NetworkUtils.getInstance().getResponse(url,null);
    }
}
