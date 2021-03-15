package com.minga.android_notes.model;

import java.io.Serializable;

public class SimpleNote implements Serializable {
    private String id;
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }

}
