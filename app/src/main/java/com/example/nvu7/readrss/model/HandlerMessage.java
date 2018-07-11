package com.example.nvu7.readrss.model;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by nvu7 on 6/21/2018.
 */

public class HandlerMessage {
  private List<Rss> items;
  private RecyclerView recyclerView;
  private RecyclerView.Adapter myAdapter;
  private ProgressBar progressBar;
  private SwipeRefreshLayout swipeRefreshLayout;

    public HandlerMessage(List<Rss> items, RecyclerView recyclerView, RecyclerView.Adapter myAdapter, ProgressBar progressBar, SwipeRefreshLayout swipeRefreshLayout) {
        this.items = items;
        this.recyclerView = recyclerView;
        this.myAdapter=myAdapter;
        this.progressBar=progressBar;
        this.swipeRefreshLayout=swipeRefreshLayout;
    }

    public boolean checkUpdateData()
    {
        return (recyclerView!=null || swipeRefreshLayout!=null);
    }
    public List<Rss> getItems() {
        return items;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerView.Adapter getMyAdapter() {
        return myAdapter;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }
}
