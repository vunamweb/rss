package com.example.nvu7.readrss.model;

/**
 * Created by nvu7 on 5/24/2018.
 */

public class Rss {
    private String title;
    private String link;
    private String description;
    public Rss(String title, String description, String link) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
