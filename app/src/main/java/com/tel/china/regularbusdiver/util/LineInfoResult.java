package com.tel.china.regularbusdiver.util;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LineInfoResult implements Parcelable{
    private String result;
    private List<LineInfo> lineInfo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(result);
        parcel.writeList(lineInfo);
    }

    public LineInfoResult(Parcel in) {
        result = in.readString();
        in.readList(lineInfo, LineInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<LineInfoResult> CREATOR = new Parcelable.Creator<LineInfoResult>() {
        public LineInfoResult createFromParcel(Parcel in) {
            return new LineInfoResult(in);
        }

        public LineInfoResult[] newArray(int size) {
            return new LineInfoResult[size];
        }
    };

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<LineInfo> getLineInfo() {
        return lineInfo;
    }

    public void setLineInfo(List<LineInfo> lineInfo) {
        this.lineInfo = lineInfo;
    }
}
