package com.vunam.nvu7.readrss.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.vunam.nvu7.readrss.R;
import com.vunam.nvu7.readrss.common.Constants;
import com.vunam.nvu7.readrss.core.Toolbar.Display;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        WebView webView=(WebView)findViewById(R.id.webview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //enable back button
        Display.displayBackButton(this);
        //lấy intent gọi Activity này
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller= callerIntent.getBundleExtra(Constants.INTENT_DATA);
        String url=packageFromCaller.getString(Constants.URL);
        //call webview
        webView.loadUrl(url);

    }

}
