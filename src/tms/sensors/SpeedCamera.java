package tms.sensors;

/**
 * From JavaDoc
   https://csse2002.uqcloud.net/assignment/1/tms/sensors/SpeedCamera.html
 * public interface SpeedCamera extends Sensor
 *
 * A type of sensor that measures the average speed of vehicles travelling along
   a route. A type of sensor that measures the average speed of vehicles
   travelling along a route.
 *
 * Inherited methods from interface Sensor: getCongestion, getThreshold
 * */

public interface SpeedCamera extends Sensor {

    /**
     * Returns the observed average speed of vehicles travelling past this
       sensor in km/h.
     * If there are no vehicles whose speed to measure, returns 0.
     *
     * @return the current average speed in km/h reported by the speed camera
     */
    int averageSpeed();
}
