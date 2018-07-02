package com.example.nvu7.readrss.app;

/**
 * Created by nvu7 on 7/2/2018.
 */

public class Application {
    private boolean loadingHeader;
    private static Application singleton;

    public static Application getInstance(){
        if(singleton==null)
            singleton=new Application();
        return singleton;
    }

    public void setLoadingHeader(boolean loadingHeader) {
        this.loadingHeader = loadingHeader;
    }

    public boolean isLoadingHeader() {
        return loadingHeader;
    }
}
