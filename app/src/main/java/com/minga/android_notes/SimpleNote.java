package com.minga.android_notes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

class SimpleNote implements Serializable {
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
