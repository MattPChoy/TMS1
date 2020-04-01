package tms.sensors;

/**
 * From JavaDoc
   https://csse2002.uqcloud.net/assignment/1/tms/sensors/Sensor.html
 * public interface Sensor
 *
 * A device used to detect congestion by comparing observed traffic flow
   measures to a predefined threshold value.
 * This interface will need to be extendable.
 */
public interface Sensor {

    /**
     * Returns the level of congestion as detected by this sensor.
     * A value of 0 indicates no congestion and 100 indicates maximum congestion
     * @return congestion levels present at the sensor, 0 to 100
     */
    int getCongestion();

    /**
     * Returns the level below/above which observed data indicates congestion is
       occurring on a route
     * The exact meaning of the threshold differs per sensor implementation
     * @return the threshold value
     */
    int getThreshold();
}
