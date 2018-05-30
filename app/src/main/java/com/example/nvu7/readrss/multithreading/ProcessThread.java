package com.example.nvu7.readrss.multithreading;

import android.os.Message;

import com.example.nvu7.readrss.common.Constants;
import com.example.nvu7.readrss.model.Rss;
import com.example.nvu7.readrss.module.RssService;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by nvu7 on 5/24/2018.
 */

public class ProcessThread extends Thread {
    private List<Rss> items;
    private android.os.Handler handler;
    public ProcessThread(List<Rss> items, android.os.Handler handler)
    {
        this.items=items;
        this.handler=handler;
    }
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
                if(Constants.USE_HANDLER)
                {
                    Message msg = handler.obtainMessage(1, (ArrayList<Rss>) items);
                    handler.sendMessage(msg);
                }
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
