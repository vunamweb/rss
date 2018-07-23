package com.example.nvu7.readrss.view;

import android.content.Context;
import android.support.v4.util.CircularArray;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.nvu7.readrss.core.ViewPager.ViewPagerBasic;

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
          viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
              @Override
              public void onPageSelected(int position) {
                  Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show();
              }
          });
      }
}
