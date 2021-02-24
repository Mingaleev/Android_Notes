package com.minga.android_notes;

import android.os.Parcel;
import android.os.Parcelable;

class SimpleNote implements Parcelable {
    private String title;
    private String date;
    private String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SimpleNote(String title, String desc, String date) {
        this.title = title;
        this.date = date;
        this.desc = desc;
    }

    protected SimpleNote(Parcel in) {
        title = in.readString();
        desc = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTitle());
        dest.writeString(getDesc());
        dest.writeString(getDate());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleNote> CREATOR = new Creator<SimpleNote>() {
        @Override
        public SimpleNote createFromParcel(Parcel in) {
            return new SimpleNote(in);
        }

        @Override
        public SimpleNote[] newArray(int size) {
            return new SimpleNote[size];
        }
    };
}
