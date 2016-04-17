package com.tel.china.regularbusdiver.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by MapleHua on 2016/4/17 0017.
 */
public class LineInfo implements Parcelable {
    private String lineNum;
    private List<String> schedule;
    private String orderNum;
    private String freeNum;

    public LineInfo(String lineNum, List<String> schedule) {
        this.lineNum = lineNum;
        this.schedule = schedule;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public LineInfo(Parcel in) {
        lineNum = in.readString();
        in.readStringList(schedule);
    }
    public static final Parcelable.Creator<LineInfo> CREATOR = new Parcelable.Creator<LineInfo>() {
        public LineInfo createFromParcel(Parcel in) {
            return new LineInfo(in);
        }

        public LineInfo[] newArray(int size) {
            return new LineInfo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lineNum);
        parcel.writeList(schedule);
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(String freeNum) {
        this.freeNum = freeNum;
    }
}
