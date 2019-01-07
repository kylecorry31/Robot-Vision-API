package com.kylecorry.frc.vision;

/**
 * A class which represents a numerical range.
 */
public class Range {

    private double low;
    private double high;

    /**
     * Creates a numerical range.
     * @param low The minimum value in the range.
     * @param high The maximum value in the range.
     */
    public Range(double low, double high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Gets the lowest value of the range.
     * @return The lowest value.
     */
    public double getLow() {
        return low;
    }

    /**
     * Gets the highest value of the range.
     * @return The highest value.
     */
    public double getHigh() {
        return high;
    }

    /**
     * Determines whether a value is in the range (inclusive).
     * @param value The value to check for.
     * @return True if the value is in the inclusive range.
     */
    public boolean inRangeInclusive(double value){
        return getLow() <= value && getHigh() >= value;
    }

    /**
     * Determines whether a value is in the range (exclusive).
     * @param value The value to check for.
     * @return True if the value is in the exclusive range.
     */
    public boolean inRangeExclusive(double value){
        return getLow() < value && getHigh() > value;
    }

}