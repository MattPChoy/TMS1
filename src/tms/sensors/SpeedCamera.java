package tms.sensors;

/**
 * From JavaDoc (https://csse2002.uqcloud.net/assignment/1/tms/sensors/SpeedCamera.html)
 * public interface SpeedCamera extends Sensor
 *
 * A type of sensor that measures the average speed of vehicles travelling along a route.A type of sensor that measures
 * the average speed of vehicles travelling along a route.
 *
 * Inherited methods from interface Sensor: getCongestion, getThreshold
 * */
public interface SpeedCamera extends Sensor {

    /**
     * Returns the level of congestion as detected by this sensor.
     * A value of 0 indicates no congestion and 100 indicates maximum congestion
     *
     * @return congestion levels present at the sensor, 0 to 100
     */
    @Override
    int getCongestion();

    /**
     * Returns the level below/above which observed data indicates congestion is occurring on a route
     * The exact meaning of the threshold differs per sensor implementation
     *
     * @return the threshold value
     */
    @Override
    int getThreshold();

    /**
     * Returns the observed average speed of vehicles travelling past this sensor in km/h.
     * If there are no vehicles whose speed to measure, returns 0.
     * @return the current average speed in km/h reported by the speed camera*/
    int averageSpeed();
}
