package com.example.nvu7.readrss.core.Adapter;

import android.content.pm.ProviderInfo;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 7/4/2018.
 */

public class AdapterBasic {
   private RecyclerView.Adapter adapter;
   private List<?> datas;

    public AdapterBasic(List<?> datas) {
        this.datas = datas;
    }
    public void init()
    {
        adapter=new RecyclerView.Adapter<RecyclerView.ViewHolder>();
    }
}
