package com.vunam.nvu7.readrss.view;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.vunam.nvu7.readrss.core.SwipeRefreshLayout.SwipeRefreshLayoutBasic;
import com.vunam.nvu7.readrss.multithreading.ProcessThread;
import com.vunam.nvu7.readrss.network.NetworkConstants;

/**
 * Created by nvu7 on 7/11/2018.
 */

public class SwipeRefreshLayoutRss extends SwipeRefreshLayoutBasic {
    private Handler handler;
    private RecyclerView.Adapter adapter;

    public SwipeRefreshLayoutRss setHandler(Handler handler)
    {
        this.handler=handler;
        return this;
    }

    public SwipeRefreshLayoutRss setAdapter(RecyclerView.Adapter adapter)
    {
        this.adapter=adapter;
        return this;
    }
    @Override
    public void setOnRefresh()
    {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ProcessThread(handler, NetworkConstants.RSS_24H_WORLDCUP2018,adapter,swipeRefreshLayout).start();
                //swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
