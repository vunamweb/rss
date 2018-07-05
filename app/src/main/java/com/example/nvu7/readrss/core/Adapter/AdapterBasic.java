package com.example.nvu7.readrss.core.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/4/2018.
 */

public class AdapterBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<?> data = new ArrayList<>();
    private Context context;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;
    protected Class<?> ClassHeader;

    public AdapterBasic(List<?> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case TYPE_ITEM:
                ClassHeader= RecyclerView.ViewHolder;
                return ClassHeader(inflater.inflate(R.layout.list_item, parent, false));
            case TYPE_FOOTER:
                return new MyAdapter.RecyclerViewHolderFooter(inflater.inflate(R.layout.list_footer, parent, false));
            //case TYPE_HEADER:
            //  return new RecyclerViewHolderFooter(inflater.inflate(R.layout.list_footer, parent, false));
            default:
                return new MyAdapter.RecyclerViewHolder(inflater.inflate(R.layout.list_item, parent, false));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
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
