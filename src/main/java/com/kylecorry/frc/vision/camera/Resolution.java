package com.kylecorry.frc.vision.camera;

import org.opencv.core.Mat;

import java.util.Objects;

/**
 * A class representation of the resolution of an image.
 */
public class Resolution {

    private int width, height;

    /**
     * Creates an instance of an image's resolution in pixels.
     * @param width The width of the image in pixels.
     * @param height The height of the image in pixels.
     */
    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get the width of the image in pixels.
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the width of the image in pixels.
     * @param width The width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get the height of the image in pixels.
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height of the image in pixels.
     * @param height The height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the area of the image in pixels.
     * @return The area.
     */
    public int getArea(){
        return width * height;
    }

    /**
     * Create a scaled resolution.
     * @param scale The scale to multiply by.
     * @return The scaled resolution.
     */
    public Resolution scaleBy(double scale){
        return new Resolution((int) Math.round(width * scale), (int) Math.round(height * scale));
    }

    /**
     * Create a resolution from an image.
     * @param image The image to base the resolution off of.
     * @return The resolution of the image.
     */
    public static Resolution fromMat(Mat image){
        return new Resolution((int) image.size().width, (int) image.size().height);
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


