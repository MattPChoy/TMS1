package tms.sensors;

/**
 * From JavaDoc
 * https://csse2002.uqcloud.net/assignment/1/tms/sensors/DemoSpeedCamera.html
 * public class DemoSpeedCamera extends DemoSensor implements SpeedCamera
 * */
public class DemoSpeedCamera extends DemoSensor implements SpeedCamera {
    // Note: Previously declared in DemoSensor.java but not sure about testing
    // implementation. Thus I have moved it into the class file so that this
    // class can access it even if my DemoSensor class is unavailable the code
    // doesn't break;
    private final int CONGESTION_LOWER_BOUND = 0;
    private final int CONGESTION_UPPER_BOUND = 100;
    /**
     * Creates a new sensor, using the given list of data values and threshold.
     * The initial value returned by getCurrentValue() should be the first
       element of the given data array.
     *
     * The sensor should be registered as a timed item, see
       TimedItemManager.registerTimedItem(TimedItem).
     *
     * @param data      a non-empty array of data values
     * @param threshold a threshold value that indicated what value is high
     *                  congestion
     * @requires data.length > 0
     */
    public DemoSpeedCamera(int[] data, int threshold) {
        super(data, threshold);
    }

    /**
     * Calculates the congestion rate as the complement of the percentage
       given by averageSpeed() divided by getThreshold().
     * For example, a route with an average speed of 60 km/h and a threshold
       value of 100 km/h would be 40 percent congested. (Returns (int) 40);
     *
     * Floating point division should be used when performing the calculation,
       however the resulting floating point number should be rounded to the
       nearest integer before being returned.
     *
     * @return congestion levels present at the sensor, 0 to 100
     * @
     */
    @Override
    public int getCongestion() {
        float average = (float) averageSpeed();
        float threshold = (float) getThreshold();

        int congestion = (int) ((average / threshold) * ((float) 100));

        // Using Math.max() and Math.min() is the same as bounding the value of
        // congestion between 0 and 100 inclusive. NOTE that the values of
        // CONGESTION_LOWER_BOUND and CONGESTION_UPPER_BOUND are set in the
        // DemoSensor class as it is also used in the DemoPressurePad class.
        return Math.max(CONGESTION_LOWER_BOUND,
                Math.min(congestion, CONGESTION_UPPER_BOUND));

    }

    /**
     * Returns the observed average speed of vehicles travelling past this
     * sensor in km/h. If there are no vehicles whose speed to measure,
     * returns 0.
     *
     * @return the current average speed in km/h reported by the speed camera
     */
    @Override
    public int averageSpeed() {
        return super.getCurrentValue();
    }

    /***
     * Returns the string representation of this sensor.
     *
     * @return the string representation of the DemoSpeedCamera class instance.
     */
    @Override
    public String toString() {
        return "SC:" + super.toString();
    }
}
