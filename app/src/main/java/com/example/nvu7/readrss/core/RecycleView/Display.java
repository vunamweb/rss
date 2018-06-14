package com.example.nvu7.readrss.core.RecycleView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by nvu7 on 5/29/2018.
 */

public class Display {
    private Context context;
    public Display(Context context)
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
