package com.tel.china.regularbusdiver.util;

import android.os.Parcel;
import android.os.Parcelable;


public class LineItem implements Parcelable {
    private String lineId;
    private String lineName;
    private String startStation;
    private String endStation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lineId);
        parcel.writeString(lineName);
        parcel.writeString(startStation);
        parcel.writeString(endStation);
    }

    public LineItem(Parcel in) {
        lineId = in.readString();
        lineName = in.readString();
        startStation = in.readString();
        endStation = in.readString();
    }

    public LineItem(String name, String start, String end) {
        lineName = name;
        startStation = start;
        endStation = end;
    }

    public static final Parcelable.Creator<LineItem> CREATOR = new Parcelable.Creator<LineItem>() {
        public LineItem createFromParcel(Parcel in) {
            return new LineItem(in);
        }

        public LineItem[] newArray(int size) {
            return new LineItem[size];
        }
    };

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }
}
