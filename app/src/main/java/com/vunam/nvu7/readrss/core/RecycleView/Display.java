package com.vunam.nvu7.readrss.core.RecycleView;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.vunam.nvu7.readrss.R;

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
    public DividerItemDecoration getDecoration()
    {
        DividerItemDecoration divider = new DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
        );
        divider.setDrawable(ContextCompat.getDrawable(context, R.drawable.line_bottom_recycleview));
        return divider;
    }
}
