package com.example.nvu7.readrss.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.util.CircularArray;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nvu7.readrss.Fragment.OneFragment;
import com.example.nvu7.readrss.Fragment.ThreeFragment;
import com.example.nvu7.readrss.Fragment.TwoFragment;
import com.example.nvu7.readrss.adapter.RecyclerViewAdapterRss;
import com.example.nvu7.readrss.core.Adapter.ViewPagerAdapterBaisc;
import com.example.nvu7.readrss.core.ViewPager.ViewPagerBasic;
import com.example.nvu7.readrss.module.RssService;

/**
 * Created by Administrator on 7/23/2018.
 */

public class ViewPagerRss extends ViewPagerBasic {

      public ViewPagerRss(Context context)
      {
          super(context);
      }

      @Override
      public void setOnPageChangeListener()
      {

          final ViewPagerAdapterBaisc  viewPagerAdapterBaisc = this.getViewPagerAdapter();
          viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
              @Override
              public void onPageSelected(int position) {
                  //get adapter
                  Fragment fragment= viewPagerAdapterBaisc.getItem(position);
                  RecyclerView recyclerView;
                  switch (position)
                  {
                      case 1:
                          recyclerView=((TwoFragment)fragment).getRecyclerView();
                          RssService.getInstance().setAdapter((RecyclerViewAdapterRss)recyclerView.getAdapter());
                          break;
                      case 2:
                          recyclerView=((ThreeFragment)fragment).getRecyclerView();
                          RssService.getInstance().setAdapter((RecyclerViewAdapterRss)recyclerView.getAdapter());
                          break;
                      default:
                          recyclerView=((OneFragment)fragment).getRecyclerView();
                          RssService.getInstance().setAdapter((RecyclerViewAdapterRss)recyclerView.getAdapter());
                  }
              }
          });
      }
}
