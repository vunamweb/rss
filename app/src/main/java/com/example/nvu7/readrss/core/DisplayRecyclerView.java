package com.example.nvu7.readrss.core;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by nvu7 on 5/29/2018.
 */

public class DisplayRecyclerView {
    private Context context;
    public DisplayRecyclerView(Context context)
    {
        this.context=context;
    }
    public LinearLayoutManager getLinear()
    {
      LinearLayoutManager layoutManager = new LinearLayoutManager(context);
      layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      return layoutManager;
    }
}
