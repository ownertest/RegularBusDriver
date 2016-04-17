package com.tel.china.regularbusdiver.bean;

/**
 * Created by zhaohaiyang on 2016/4/17.
 */
public class ClassLines {
    String lineTime;


    String belongLine;
    int lineId;
    int id;
    int timeSeat;
    int freeSeat;
    int lineDirection;
    public ClassLines(){}
    public ClassLines(String lineTime, String belongLine) {
        this.belongLine = belongLine;
        this.lineTime = lineTime;
    }
    public String getBelongLine() {
        return belongLine;
    }

    public void setBelongLine(String belongLine) {
        this.belongLine = belongLine;
    }

    public String getLineTime() {
        return lineTime;
    }

    public void setLineTime(String lineTime) {
        this.lineTime = lineTime;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeSeat() {
        return timeSeat;
    }

    public void setTimeSeat(int timeSeat) {
        this.timeSeat = timeSeat;
    }

    public int getFreeSeat() {
        return freeSeat;
    }

    public void setFreeSeat(int freeSeat) {
        this.freeSeat = freeSeat;
    }

    public int getLineDirection() {
        return lineDirection;
    }

    public void setLineDirection(int lineDirection) {
        this.lineDirection = lineDirection;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return lineTime;
    }

}
