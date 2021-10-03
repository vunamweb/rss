package com.vunam.nvu7.readrss.core.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 7/4/2018.
 */

public class RecyclerViewAdapterBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<?> data;
    protected List<?> copyData;
    protected Context context;
    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;
    protected static final int TYPE_FOOTER = 2;
    protected int layoutItem;
    protected int layoutFooter;

    public RecyclerViewAdapterBasic(List<?> data, Context context) {
        this.data = data;
        this.copyData=data;
        this.context = context;
    }

    public RecyclerViewAdapterBasic setLayoutItem(int layoutItem)
    {
        this.layoutItem=layoutItem;
        return this;
    }

    public RecyclerViewAdapterBasic setLayoutFooter(int layoutFooter)
    {
        this.layoutFooter=layoutFooter;
        return this;
    }

    public List<?> getData()
    {
        return copyData;
    }
    public void setData(List<?> data)
    {
        this.copyData=data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return copyData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;

        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position > data.size()-2;
    }
}
