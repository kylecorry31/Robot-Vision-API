package com.kylecorry.frc.vision;

public class Range {

    private double low;
    private double high;

    public Range(double low, double high) {
        this.low = low;
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public double getHigh() {
        return high;
    }

    public boolean inRangeInclusive(double value){
        return getLow() <= value && getHigh() >= value;
    }

    public boolean inRangeExclusive(double value){
        return getLow() < value && getHigh() > value;
    }

}