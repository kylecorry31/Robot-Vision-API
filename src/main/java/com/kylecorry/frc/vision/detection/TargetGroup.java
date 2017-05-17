package com.kylecorry.frc.vision.detection;

import com.kylecorry.geometry.Point;

public class TargetGroup extends AbstractTarget {
    private Target first, second;

    /**
     * Create a TargetGroup consisting of the first and second target.
     *
     * @param first  The first target in the group.
     * @param second The second target in the group.
     */
    public TargetGroup(Target first, Target second) {
        this.first = first;
        this.second = second;
        this.imageSize = first.imageSize;
    }

    /**
     * Get the probability that this is the specified target group from 0 to 1
     * inclusive.
     *
     * @return The probability that this is the target group.
     */
    public double getProbability() {
        return confidence;
    }

    /**
     * Get the first target in the group.
     *
     * @return The first target.
     */
    public Target getFirstTarget() {
        return first;
    }

    /**
     * Get the second target in the group.
     *
     * @return The second target.
     */
    public Target getSecondTarget() {
        return second;
    }


    public double getWidth() {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double maxRight = Math.max(first.getPosition().x + first.getWidth(),
                second.getPosition().x + second.getWidth());
        return maxRight - minLeft;
    }


    public double getHeight() {
        double minTop = Math.min(first.getPosition().y, second.getPosition().y);
        double maxBottom = Math.max(first.getPosition().y + first.getHeight(),
                second.getPosition().y + second.getHeight());
        return maxBottom - minTop;
    }


    public Point getPosition() {
        double minLeft = Math.min(first.getPosition().x, second.getPosition().x);
        double minTop = Math.min(first.getPosition().y, second.getPosition().y);
        return new Point(minLeft, minTop, 0);
    }

    public Point getCenterOfMass() {
        return new Point((getFirstTarget().getCenterOfMass().x + getSecondTarget().getCenterOfMass().x) / 2.0,
                (getFirstTarget().getCenterOfMass().y + getSecondTarget().getCenterOfMass().y) / 2.0, 0);
    }
}
