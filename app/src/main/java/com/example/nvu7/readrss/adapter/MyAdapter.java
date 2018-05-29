package com.example.nvu7.readrss.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.model.Rss;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvu7 on 5/28/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerViewHolder>{

    private List<Rss> data = new ArrayList<>();

    public MyAdapter(List<Rss> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.txtTitle.setText(data.get(position).getTitle());
        holder.txtDescription.setText(data.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
