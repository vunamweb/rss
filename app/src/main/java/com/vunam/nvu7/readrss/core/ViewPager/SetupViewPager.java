package com.vunam.nvu7.readrss.core.ViewPager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.vunam.nvu7.readrss.Fragment.OneFragment;
import com.vunam.nvu7.readrss.Fragment.ThreeFragment;
import com.vunam.nvu7.readrss.Fragment.TwoFragment;
//import com.vunam.nvu7.readrss.adapter.ViewPagerAdapter;
import com.vunam.nvu7.readrss.core.Adapter.ViewPagerAdapterBaisc;

/**
 * Created by nvu7 on 6/14/2018.
 */

public class SetupViewPager {
   public static void setupAdapter(ViewPager viewPager,ViewPagerAdapterBaisc adapter)
   {
       adapter.addFragment(new OneFragment(), "ONE");
       adapter.addFragment(new TwoFragment(), "TWO");
       adapter.addFragment(new ThreeFragment(), "THREE");
       viewPager.setAdapter(adapter);
   }
    public static void setupTablayout(ViewPager viewPager , TabLayout tabLayout)
    {
        tabLayout.setupWithViewPager(viewPager);
    }
}
