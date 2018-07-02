package com.example.nvu7.readrss.model;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.nvu7.readrss.adapter.MyAdapter;

import java.util.List;

/**
 * Created by nvu7 on 6/21/2018.
 */

public class HandlerMessage {
  private List<Rss> items;
  private RecyclerView recyclerView;
  private MyAdapter myAdapter;
  private ProgressBar progressBar;

    public HandlerMessage(List<Rss> items, RecyclerView recyclerView,MyAdapter myAdapter,ProgressBar progressBar) {
        this.items = items;
        this.recyclerView = recyclerView;
        this.myAdapter=myAdapter;
        this.progressBar=progressBar;
    }

    public List<Rss> getItems() {
        return items;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public MyAdapter getMyAdapter() {
        return myAdapter;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
