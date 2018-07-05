package com.example.nvu7.readrss.core.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by nvu7 on 7/5/2018.
 */

public class AdapterRss extends AdapterBasic {
    public AdapterRss(List<?> data, Context context) {
        super(data, context);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

    }
}
