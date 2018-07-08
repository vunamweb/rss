package com.example.nvu7.readrss.core.ViewPager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

//import com.example.nvu7.readrss.adapter.ViewPagerAdapter;
import com.example.nvu7.readrss.core.Adapter.ViewPagerAdapterBaisc;

/**
 * Created by Administrator on 7/6/2018.
 */

public class ViewPagerBasic {

  protected TabLayout tabLayout;
  protected ViewPager viewPager;
  protected ViewPagerAdapterBaisc viewPagerAdapter;

  public ViewPagerBasic setTabLayout(TabLayout tabLayout)
  {
    this.tabLayout=tabLayout;
    return this;
  }

  public ViewPagerBasic setViewPagerAdapter(ViewPagerAdapterBaisc viewPagerAdapter)
  {
    this.viewPagerAdapter=viewPagerAdapter;
    return this;
  }

  public ViewPagerBasic into(ViewPager viewPager)
  {
    this.viewPager=viewPager;
    viewPager.setAdapter(viewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    return this;
  }
}
