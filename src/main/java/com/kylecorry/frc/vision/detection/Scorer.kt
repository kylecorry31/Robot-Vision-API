package com.kylecorry.frc.vision.detection

internal object Scorer {
    /**
     * Calculates the score of a ratio (observed/ideal) where the further from 1
     * the ratio is, the lower the ratio. A ratio of 1 will yield a score of
     * 100.

     * @param observed
     * *            The observed value
     * *
     * @param ideal
     * *            The ideal value
     * *
     * @return The score of how close the observed was to the ideal in the range
     * *         0 to 100 inclusive.
     */
    fun score(observed: Double, ideal: Double): Double {
        return toRange(100 * (1 - Math.abs(1 - observed / ideal)), 0.0, 100.0)
    }

    private fun toRange(value: Double, min: Double, max: Double): Double {
        var newVal = Math.max(min, value)
        newVal = Math.min(newVal, max)
        return newVal
    }
}
