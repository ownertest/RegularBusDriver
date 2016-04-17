package com.tel.china.regularbusdiver.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule implements Parcelable {

    private String time;
    private String order;
    private String free;
    private String lineId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(time);
        parcel.writeString(order);
        parcel.writeString(free);
        parcel.writeString(lineId);
    }

    public Schedule(Parcel in) {
        time = in.readString();
        order = in.readString();
        free = in.readString();
        lineId = in.readString();
    }

    public Schedule(String t, String o, String f, String lineId) {
        time = t;
        order = o;
        free = f;
        this.lineId = lineId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }
}
