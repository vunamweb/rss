package com.vunam.nvu7.readrss.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vunam.nvu7.readrss.R;
import com.vunam.nvu7.readrss.app.DetailActivity;
import com.vunam.nvu7.readrss.common.Constants;
import com.vunam.nvu7.readrss.core.Activity.Goto;
import com.vunam.nvu7.readrss.core.Adapter.RecyclerViewAdapterBasic;
import com.vunam.nvu7.readrss.core.LoadImg.ImgPicasso;
import com.vunam.nvu7.readrss.model.Rss;
import com.vunam.nvu7.readrss.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nvu7 on 7/5/2018.
 */

public class RecyclerViewAdapterRss extends RecyclerViewAdapterBasic implements Filterable {

    private Boolean changeColor;
    private Boolean changeFontSize;

    public void setChangeColor(Boolean changeColor) {
        this.changeColor = changeColor;
    }

    public Boolean getChangeColor() {
        return changeColor;
    }

    public void setChangeFontSize(Boolean changeFontSize) {
        this.changeFontSize = changeFontSize;
    }

    public Boolean getChangeFontSize() {
        return changeFontSize;
    }

    public RecyclerViewAdapterRss(List<?> data, Context context) {
        super(data, context);
        changeColor=false;
        changeFontSize=false;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
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

        if (holder instanceof RecyclerViewHolder) {
            String urlImg = StringUtils.getUrlimgFromRssDes24h(((Rss) copyData.get(position)).getDescription());
            ((RecyclerViewHolder) holder).txtTitle.setText(((Rss) copyData.get(position)).getTitle());
            ((RecyclerViewHolder) holder).txtDescription.setText(StringUtils.getStringDesFromTagDes24h(((Rss) copyData.get(position)).getDescription()));
            //((RecyclerViewHolder) holder).txtTitle.setText("title");
            //((RecyclerViewHolder) holder).txtDescription.setText("description");

            new ImgPicasso(context).load(urlImg, ((RecyclerViewHolder) holder).imgRss);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.URL, ((Rss) copyData.get(position)).getLink());
                    Goto.startActivity(context, DetailActivity.class, bundle);
                }
            });

            if(changeColor)
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.black));
            else
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.white));

            if(changeFontSize)
            {
                ((RecyclerViewHolder) holder).txtTitle.setTextSize(context.getResources().getDimension(R.dimen.large_font_size));
                ((RecyclerViewHolder) holder).txtDescription.setTextSize(context.getResources().getDimension(R.dimen.large_font_size));
            }
            else
            {
                ((RecyclerViewHolder) holder).txtTitle.setTextSize(context.getResources().getDimension(R.dimen.default_font_size));
                ((RecyclerViewHolder) holder).txtDescription.setTextSize(context.getResources().getDimension(R.dimen.default_font_size));
            }

        }
    }


    public class RecyclerViewHolderFooter extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public RecyclerViewHolderFooter(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
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
            imgRss = (ImageView) itemView.findViewById(R.id.imgRss);
        }
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    copyData = data;
                } else {

                    ArrayList<Rss> filteredList = new ArrayList<>();
                    ArrayList<Rss> data1=(ArrayList<Rss>)data;

                    for (Rss  rss :  data1) {

                        if (rss.getTitle().toLowerCase().contains(charString) || rss.getDescription().toLowerCase().contains(charString)) {

                            filteredList.add(rss);
                        }
                    }

                    copyData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = copyData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                copyData = (ArrayList<Rss>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}