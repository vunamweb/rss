package com.example.nvu7.readrss.api;

import com.example.nvu7.readrss.network.NetworkConstants;
import com.example.nvu7.readrss.network.NetworkUtils;

/**
 * Created by nvu7 on 5/23/2018.
 */

public class RssAPIUtils {
    public static String read24H()  {
        String url= NetworkConstants.RSS_24H;
        return NetworkUtils.getInstance().getResponse(url,null);
    }
}
