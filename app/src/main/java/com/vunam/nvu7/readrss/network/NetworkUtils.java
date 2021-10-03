package com.vunam.nvu7.readrss.network;

import com.vunam.nvu7.readrss.common.Constants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by nvu7 on 5/23/2018.
 */

public class NetworkUtils {

    private static NetworkUtils mInstance;

    public static synchronized NetworkUtils getInstance()
    {
        if(mInstance == null){
            mInstance = new NetworkUtils();
        }
        return mInstance;
    }
    public XmlPullParser getRss(String requestUrl)
    {
        URL url = null;
        XmlPullParser myparser=null;
        try {
            url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myparser.setInput(stream, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myparser;
    }
    public String getResponse(String requestURL, Map<String,Object> header)
   {
      URL url;
      String response = "";
      try {
          url = new URL(requestURL);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();

          conn.setReadTimeout(NetworkConstants.TIME_TIMEOUT);
          conn.setConnectTimeout(NetworkConstants.TIME_TIMEOUT);
          conn.setRequestMethod(Constants.METHOD_GET);
          conn.setDoInput(true);
          conn.setDoOutput(true);
          conn.setRequestProperty("Content-Type", "application/json");

          conn.connect();
          //checks server's status code first
          int responseCode = conn.getResponseCode();
          if (responseCode == HttpsURLConnection.HTTP_OK) {
              String line;
              BufferedReader br = new BufferedReader(new InputStreamReader(
                      conn.getInputStream()));
              while ((line = br.readLine()) != null) {
                  response += line;
              }
          } else {
              response = "";
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return response;
  }
}


