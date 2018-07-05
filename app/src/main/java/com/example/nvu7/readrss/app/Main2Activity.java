package com.example.nvu7.readrss.app;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nvu7.readrss.Fragment.InterfaceFragment;
import com.example.nvu7.readrss.Fragment.OneFragment;
import com.example.nvu7.readrss.Fragment.ThreeFragment;
import com.example.nvu7.readrss.Fragment.TwoFragment;
import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.adapter.MyAdapter;
import com.example.nvu7.readrss.adapter.ViewPagerAdapter;
import com.example.nvu7.readrss.core.Adapter.AdapterRss;
import com.example.nvu7.readrss.core.RecycleView.RecycleViewRss;
import com.example.nvu7.readrss.core.ViewPager.SetupViewPager;
import com.example.nvu7.readrss.model.HandlerMessage;
import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.multithreading.ProcessThread;
import com.example.nvu7.readrss.network.NetworkConstants;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, InterfaceFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    Handler handler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //hander
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                boolean loading = true;
                HandlerMessage handlerMessage=(HandlerMessage) msg.obj;
                //if is set data for recycleview
                List<Rss> itemsTest =handlerMessage.getItems();
                final ProgressBar progressBar=handlerMessage.getProgressBar();
                if(handlerMessage.getRecyclerView()!=null)
                {
                    RecyclerView recyclerView=handlerMessage.getRecyclerView();
                    final MyAdapter myAdapter=new MyAdapter(itemsTest, Main2Activity.this);
                    AdapterRss a=new AdapterRss(itemsTest,getApplicationContext());
                    a.setLayoutItem(R.layout.list_item)
                            .setLayoutFooter(R.layout.list_footer);



                    new RecycleViewRss(getApplicationContext(),recyclerView,myAdapter,progressBar,handler).init();
                }
                //if is endless, then update data
                else if(progressBar==null)
                {
                    MyAdapter myAdapter=handlerMessage.getMyAdapter();
                    List<Rss> datas=myAdapter.getData();
                    for(int i=0;i<itemsTest.size();i++)
                        datas.add(itemsTest.get(i));
                    myAdapter.setData(datas);
                    myAdapter.notifyDataSetChanged();
                }
                //if is scrool top
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    MyAdapter myAdapter=handlerMessage.getMyAdapter();
                    myAdapter.setData(itemsTest);
                    myAdapter.notifyDataSetChanged();
                }
            }
        };
        //

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setup adapter
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        SetupViewPager.setupAdapter(viewPager, adapter);

        //setup tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        SetupViewPager.setupTablayout(viewPager, tabLayout);

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @Override
    public void getListNewsRss24h(Fragment fragment, String url) {
        switch (url) {
            case NetworkConstants.RSS_24H:
                new ProcessThread(handler, url,((OneFragment) fragment).recyclerView,((OneFragment) fragment).progressBar).start();
                break;
            case NetworkConstants.RSS_24H_WORLDCUP2018 :
                 new ProcessThread(handler, url,((TwoFragment) fragment).recyclerView,((TwoFragment) fragment).progressBar).start();
                break;
             default:
                 new ProcessThread(handler, url,((ThreeFragment) fragment).recyclerView,((ThreeFragment) fragment).progressBar).start();
        }
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
