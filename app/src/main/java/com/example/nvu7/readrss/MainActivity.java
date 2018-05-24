package com.example.nvu7.readrss;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.module.RssService;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    String title=null,description=null,link = null;
                    Boolean isItem=false;
                    XmlPullParser xmlPullParser = RssService.getInstance().getRss24h();
                    try {
                        xmlPullParser.nextTag();
                        while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                            int eventType = xmlPullParser.getEventType();

                            String name = xmlPullParser.getName();
                            if(name == null)
                                continue;

                            if(eventType == XmlPullParser.END_TAG) {
                                continue;
                            }

                            if (eventType == XmlPullParser.START_TAG) {
                                if(name.equalsIgnoreCase("item")) {
                                    isItem=true;
                                    continue;
                                }
                            }
                            String result = "";
                            if (xmlPullParser.next() == XmlPullParser.TEXT) {
                                result = xmlPullParser.getText();
                                xmlPullParser.nextTag();
                            }
                            if (name.equalsIgnoreCase("title")) {
                                title = result;
                            } else if (name.equalsIgnoreCase("link")) {
                                link = result;
                            } else if (name.equalsIgnoreCase("description")) {
                                description = result;
                            }
                            if (title != null && link != null && description != null && isItem) {
                                Rss item=new Rss(title,description,link);
                                items.add(item);
                                title = null;
                                link = null;
                                description = null;
                                isItem = false;
                            }
                        }
                    }

                    catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        List<Rss> b=items;
          //new ProcessThread().start();
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
