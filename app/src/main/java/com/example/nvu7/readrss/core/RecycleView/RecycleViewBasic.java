package com.example.nvu7.readrss.core.RecycleView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nvu7.readrss.R;

/**
 * Created by nvu7 on 7/3/2018.
 */

abstract public class RecycleViewBasic {
    protected Context context;
    protected RecyclerView recyclerView;
    protected  LinearLayoutManager linearLayout;
    protected  RecyclerView.Adapter adapter;
    protected  DividerItemDecoration dividerItemDecoration;

    public RecycleViewBasic(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    public void init()
    {
        // init LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.linearLayout=layoutManager;

        //init DividerItemDecoration
        DividerItemDecoration divider = new DividerItemDecoration(
                recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_bottom_recycleview));
        this.dividerItemDecoration=divider;

        //init recycleview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(divider);
        addScroll();
    }
    abstract public void  addScroll();
}
