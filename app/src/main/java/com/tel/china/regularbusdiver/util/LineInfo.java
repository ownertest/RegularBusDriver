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
    private List<String> times;

    public LineInfo(String lineNum, List<String> schedule, List<String> times) {
        this.lineNum = lineNum;
        this.schedule = schedule;
        this.times = times;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public LineInfo(Parcel in) {
        lineNum = in.readString();
        in.readStringList(schedule);
        in.readStringList(times);
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
        parcel.writeList(times);
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

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }
}
