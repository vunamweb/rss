package com.example.nvu7.readrss.model;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by nvu7 on 6/21/2018.
 */

public class HandlerMessage {
  private List<Rss> items;
  private RecyclerView recyclerView;

    public HandlerMessage(List<Rss> items, RecyclerView recyclerView) {
        this.items = items;
        this.recyclerView = recyclerView;
    }

    public List<Rss> getItems() {
        return items;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
