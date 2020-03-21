package tms.sensors;

/**
 * From JavaDoc (https://csse2002.uqcloud.net/assignment/1/tms/sensors/DemoPressurePad.html)
 * public class DemoPressurePad extends DemoSensor implements PressurePad
 *
 * An implementation of a pressure pad sensor
 *
 * Inherited methods from class DemoSensor: getCurrentValue, getThreshold, oneSecond
 * Inherited methods from class Object    : clone, finalize, getClass, notify, notifyAll, wait, wait, wait
 * Inherited methods from interface Sensor: getThreshold
 * */

public class DemoPressurePad extends DemoSensor implements PressurePad {
    // Instance variable for the threshold value

    /**
     * Creates a new sensor, using the given list of data values and threshold.
     * The initial value returned by getCurrentValue() should be the first element of the given data array.
     * <p>
     * The sensor should be registered as a timed item, see TimedItemManager.registerTimedItem(TimedItem).
     *
     * @param data      a non-empty array of data values
     * @param threshold a threshold value that indicates which values represent high congestion
     * @requires data.length > 0
     */
    public DemoPressurePad(int[] data, int threshold) {
        super(data, threshold); // DemoSensor constructor stores values in super.data, super.threshold.
    }

    /**
     * Returns the number of vehicles currently waiting on the pressure pad.
     * Specified by countTraffic in interface PressurePad
     *
     * @return the current traffic count reported by the pressure pad
     */
    @Override
    public int countTraffic() {
        return super.getCurrentValue();
    }

    /**
     * Calculates the congestion rate as the percentage given by countTraffic() divided by getThreshold().
     * For example, a route with an current traffic count of 45 vehicles and a threshold value of 60 vehicles would be
       75 percent congested.
     *
     * Floating point division should be used when performing the calculation, however the resulting floating point
       number should be rounded to the nearest integer before being returned.
     *
     * @return congestion levels present at the sensor, 0 to 100
     */
    @Override
    public int getCongestion() {
        float count = (float) countTraffic();
        float threshold = (float) getThreshold();

        return (int) ((count/threshold)*(float) 100);
    }

    /**
     * Returns the level below/above which observed data indicates congestion is occurring on a route
     * The exact meaning of the threshold differs per sensor implementation
     *
     * @return the threshold value
     */
    @Override
    public int getThreshold() {
        return super.getThreshold(); // DemoPressurePad -> PressurePad -> DemoSensor
    }

    /***
     * Returns the string representation of this sensor.
     * @return the string representation of the DemoPressurePad class instance.
     */
    @Override
    public String toString() {
        return "PP:" + super.toString();
    }
}
