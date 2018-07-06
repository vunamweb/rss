package com.example.nvu7.readrss.core.Adapter;

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

import java.util.List;

/**
 * Created by nvu7 on 7/5/2018.
 */

public class AdapterRss extends AdapterBasic {


    public AdapterRss(List<?> data, Context context) {
        super(data, context);
    }

    public AdapterRss setLayoutItem(int layoutItem)
    {
        this.layoutItem=layoutItem;
        return this;
    }

    public AdapterRss setLayoutFooter(int layoutFooter)
    {
        this.layoutFooter=layoutFooter;
        return this;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case TYPE_ITEM:
                return new RecyclerViewHolder(inflater.inflate(layoutItem, parent, false));
            case TYPE_FOOTER:
                return new RecyclerViewHolderFooter(inflater.inflate(layoutFooter, parent, false));
            //case TYPE_HEADER:
            //  return new RecyclerViewHolderFooter(inflater.inflate(R.layout.list_footer, parent, false));
            default:
                return new RecyclerViewHolder(inflater.inflate(layoutItem, parent, false));
        }
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof RecyclerViewHolder)
        {
            String urlImg= StringUtils.getUrlimgFromRssDes24h(((Rss)data.get(position)).getDescription());
            ((RecyclerViewHolder)holder).txtTitle.setText(((Rss)data.get(position)).getTitle());
            ((RecyclerViewHolder)holder).txtDescription.setText(StringUtils.getStringDesFromTagDes24h(((Rss)data.get(position)).getDescription()));
            new ImgPicasso(context).load(urlImg,((RecyclerViewHolder)holder).imgRss);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle=new Bundle();
                    bundle.putString(Constants.URL,((Rss)data.get(position)).getLink());
                    Goto.startActivity(context,DetailActivity.class,bundle);
                }
            });
        }
    }


    public class RecyclerViewHolderFooter extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public RecyclerViewHolderFooter(View itemView) {
            super(itemView);
            progressBar=(ProgressBar) itemView.findViewById(R.id.progressBar);
        }
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
