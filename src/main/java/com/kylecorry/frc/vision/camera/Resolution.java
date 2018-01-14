package com.kylecorry.frc.vision.camera;

import java.util.Objects;

public class Resolution {

    private int width, height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){
        return width * height;
    }

    public Resolution scaleBy(double scale){
        return new Resolution((int) Math.round(width * scale), (int) Math.round(height * scale));
    }

    @Override
    public String toString() {
        return "Resolution{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resolution that = (Resolution) o;
        return width == that.width &&
                height == that.height;
    }

    @Override
    public int hashCode() {

        return Objects.hash(width, height);
    }
}


