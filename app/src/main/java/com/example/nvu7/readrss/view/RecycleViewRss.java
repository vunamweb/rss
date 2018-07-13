package com.example.nvu7.readrss.view;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.nvu7.readrss.app.Application;
import com.example.nvu7.readrss.core.RecycleView.RecycleViewBasic;
import com.example.nvu7.readrss.multithreading.ProcessThread;
import com.example.nvu7.readrss.network.NetworkConstants;

/**
 * Created by nvu7 on 7/3/2018.
 */

public class RecycleViewRss extends RecycleViewBasic {

    private ProgressBar progressBar;
    private Handler handler;

    public RecycleViewRss(Context context) {
        super(context);
    }

    public RecycleViewRss setProgressbar(ProgressBar progressbar)
    {
        this.progressBar=progressbar;
        return this;
    }

    public RecycleViewRss setHandle(Handler handle)
    {
        this.handler=handle;
        return this;
    }


    @Override
    public void  addScroll()
    {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pastVisiblesItems, visibleItemCount, totalItemCount;
                visibleItemCount = linearLayout.getChildCount();
                totalItemCount = linearLayout.getItemCount();
                pastVisiblesItems = linearLayout.findFirstVisibleItemPosition();
                boolean loading = true;
                boolean loadingHeader= Application.getInstance().isLoadingHeader();
                //Log.v("...", "nambuuu !");
                if(dy>0)
                {
                    Application.getInstance().setLoadingHeader(true);
                    if (loading)
                    {
                        //if is endless
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            //Log.v("...", "Last Item Wow !");
                            new ProcessThread(handler, NetworkConstants.RSS_24H_WORLDCUP2018,adapter).start();
                        }
                    }
                }
                else if(dy<=0)
                {
                    //if is top
                    if(pastVisiblesItems==0 &&loadingHeader)
                    {
                        Application.getInstance().setLoadingHeader(false);
                        //progressBar.setVisibility(View.VISIBLE);
                        //new ProcessThread(handler,NetworkConstants.RSS_24H_WORLDCUP2018,adapter,progressBar).start();
                    }
                }
            }
        });
    }
}
