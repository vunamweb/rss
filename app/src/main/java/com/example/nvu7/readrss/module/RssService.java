package com.example.nvu7.readrss.module;

import android.support.v7.widget.RecyclerView;

import com.example.nvu7.readrss.api.RssAPIUtils;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by nvu7 on 5/23/2018.
 */

public class RssService {
    private static RssService mInstance;
    private RecyclerView.Adapter adapter;

    public static RssService getInstance()
    {
        if(mInstance == null){
            mInstance = new RssService();
        }
        return mInstance;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public XmlPullParser getRss24h(String url)
    {
        return RssAPIUtils.readRss24H(url);
    }
}
