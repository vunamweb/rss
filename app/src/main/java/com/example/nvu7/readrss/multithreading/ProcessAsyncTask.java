package com.example.nvu7.readrss.multithreading;

import android.os.AsyncTask;

import com.example.nvu7.readrss.model.Rss;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/30/2018.
 */

public class ProcessAsyncTask extends AsyncTask<Void, Integer, ArrayList<Rss>> {
    @Override
    protected ArrayList<Rss> doInBackground(Void... arg0)
    {
        return null;
    }
    @Override
    protected void onPostExecute(ArrayList<Rss> result)
    {


    }
}
