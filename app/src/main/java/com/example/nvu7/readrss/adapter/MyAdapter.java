package com.example.nvu7.readrss.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.app.DetailActivity;
import com.example.nvu7.readrss.common.Constants;
import com.example.nvu7.readrss.core.Activity.Goto;
import com.example.nvu7.readrss.core.LoadImg.ImgPicasso;
import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvu7 on 5/28/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Rss> data = new ArrayList<>();
    private Context context;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public MyAdapter(List<Rss> data,Context context)
    {
        this.data = data;
        this.context=context;
    }
    public List<Rss> getData()
    {
        return data;
    }
    public void setData(List<Rss> data)
    {
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case TYPE_ITEM:
              return new RecyclerViewHolder(inflater.inflate(R.layout.list_item, parent, false));
            case TYPE_FOOTER:
                return new RecyclerViewHolderFooter(inflater.inflate(R.layout.list_footer, parent, false));
            //case TYPE_HEADER:
              //  return new RecyclerViewHolderFooter(inflater.inflate(R.layout.list_footer, parent, false));
            default:
                return new RecyclerViewHolder(inflater.inflate(R.layout.list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof  RecyclerViewHolder)
        {
            String urlImg= StringUtils.getUrlimgFromRssDes24h(data.get(position).getDescription());
            ((RecyclerViewHolder)holder).txtTitle.setText(data.get(position).getTitle());
            ((RecyclerViewHolder)holder).txtDescription.setText(StringUtils.getStringDesFromTagDes24h(data.get(position).getDescription()));
            new ImgPicasso(context).load(urlImg,((RecyclerViewHolder)holder).imgRss);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString(Constants.URL,data.get(position).getLink());
                    Goto.startActivity(context,DetailActivity.class,bundle);
                }
            });
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
    public class RecyclerViewHolderFooter extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public RecyclerViewHolderFooter(View itemView) {
            super(itemView);
            progressBar=(ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
    public class RecyclerViewHolderFooter_1 extends RecyclerView.ViewHolder {
        TextView textView;
        public RecyclerViewHolderFooter_1(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.textView2);
        }
    }
}
