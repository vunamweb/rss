package com.vunam.nvu7.readrss.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vunam.nvu7.readrss.R;
import com.vunam.nvu7.readrss.adapter.MyAdapter;
import com.vunam.nvu7.readrss.common.Constants;
import com.vunam.nvu7.readrss.core.RecycleView.Display;
import com.vunam.nvu7.readrss.model.Rss;
import com.vunam.nvu7.readrss.multithreading.ProcessAsyncTask;
import com.vunam.nvu7.readrss.multithreading.ProcessThreadTest;
import com.vunam.nvu7.readrss.network.NetworkConstants;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
     Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerRss=(RecyclerView) findViewById(R.id.list_item);
        //recyclerRss.setOnClickListener();
        if(Constants.USE_HANDLER)
        {
            //hander
             handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    List<Rss> items=(ArrayList<Rss>) msg.obj;
                    recyclerRss.setLayoutManager(new Display(getApplicationContext()).getLinear());
                    recyclerRss.setAdapter(new MyAdapter(items,MainActivity.this));
                }
            };
            //
        }

        final List<Rss> items=new ArrayList<Rss>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //if not use asynctask
        if(!Constants.USE_ASYNcCTASK)
           new ProcessThreadTest(items,handler, NetworkConstants.RSS_24H).start();
        //else use asynctask
        else
           new ProcessAsyncTask(getApplicationContext(),recyclerRss).execute();
        //if not use handler and asynctask
        if(!Constants.USE_HANDLER && !Constants.USE_ASYNcCTASK)
        {
            try {
                recyclerRss.setLayoutManager(new Display(getApplicationContext()).getLinear());
                recyclerRss.setAdapter(new MyAdapter(items,MainActivity.this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
