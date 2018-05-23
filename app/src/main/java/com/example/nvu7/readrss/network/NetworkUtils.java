package com.example.nvu7.readrss.network;

import java.io.BufferedReader;
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
    public String getResponse(String requestURL, Map<String,Object> header)
  {
      URL url;
      String response = "";
      try {
          url = new URL(requestURL);
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();

          conn.setReadTimeout(15000);
          conn.setConnectTimeout(15000);
          conn.setRequestMethod("GET");
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


