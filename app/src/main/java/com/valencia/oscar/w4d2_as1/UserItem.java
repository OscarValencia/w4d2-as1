package com.valencia.oscar.w4d2_as1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserItem {
    private String id;
    private String thumbnail;
    private String title;
    private String subtitle;
    private List<String> authors;
    private String selfLink;

    public UserItem(String id, String thumbnail, String title, String subtitle, List<String> authors, String selfLink) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.selfLink = selfLink;
    }

    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getSelfLink() {
        return selfLink;
    }
}
