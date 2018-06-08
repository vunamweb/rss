package com.example.nvu7.readrss.multithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.widget.RecyclerView;

import com.example.nvu7.readrss.R;
import com.example.nvu7.readrss.adapter.MyAdapter;
import com.example.nvu7.readrss.common.Constants;
import com.example.nvu7.readrss.core.DisplayRecyclerView;
import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.module.RssService;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/30/2018.
 */

public class ProcessAsyncTask extends AsyncTask<Void, Integer, ArrayList<Rss>> {
    private Context context;
    private RecyclerView recyclerRss;

    public ProcessAsyncTask(Context context, RecyclerView recyclerRss) {
        this.context = context;
        this.recyclerRss = recyclerRss;
    }

    @Override
    protected ArrayList<Rss> doInBackground(Void... arg0)
    {
        ArrayList<Rss> items=new ArrayList<Rss>() ;
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
        return items;
    }
    @Override
    protected void onPostExecute(ArrayList<Rss> result)
    {
        this.recyclerRss.setLayoutManager(new DisplayRecyclerView(this.context).getLinear());
        recyclerRss.setAdapter(new MyAdapter(result));
    }
}
