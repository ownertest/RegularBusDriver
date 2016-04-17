package com.tel.china.regularbusdiver.bean;

import java.util.List;

public class carInfoDetail {
    private String result;
    private Line line;
    private int carCount;
    private List<Station> station;
    private List<ClassLines> classLines;

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }

    public List<ClassLines> getClassLines() {
        return classLines;
    }

    public void setClassLines(List<ClassLines> classLines) {
        this.classLines = classLines;
    }


}
