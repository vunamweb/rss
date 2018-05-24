package com.example.nvu7.readrss.multithreading;

import com.example.nvu7.readrss.module.RssService;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by nvu7 on 5/24/2018.
 */

public class ProcessThread extends Thread {
    @Override
    public void run() {
        try  {
            XmlPullParser response = RssService.getInstance().getRss24h();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
