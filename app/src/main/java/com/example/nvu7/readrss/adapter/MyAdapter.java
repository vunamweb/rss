package com.example.nvu7.readrss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.core.LoadImg.ImgPicasso;
import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvu7 on 5/28/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerViewHolder>{

    private List<Rss> data = new ArrayList<>();
    private Context context;

    public MyAdapter(List<Rss> data,Context context)
    {
        this.data = data;
        this.context=context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        String urlImg= StringUtils.getUrlimgFromRssDes24h(data.get(position).getDescription());
        holder.txtTitle.setText(data.get(position).getTitle());
        holder.txtDescription.setText(StringUtils.getStringDesFromTagDes24h(data.get(position).getDescription()));
        new ImgPicasso(context).load(urlImg,holder.imgRss);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView imgRss;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.title);
            txtDescription = (TextView) itemView.findViewById(R.id.description);
            imgRss=(ImageView)itemView.findViewById(R.id.imgRss);
        }
    }
}
