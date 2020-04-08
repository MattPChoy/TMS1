package tms.route;

import tms.intersection.Intersection;
import tms.sensors.DemoPressurePad;
import tms.sensors.Sensor;
import tms.util.DuplicateSensorException;

import java.util.*;

/**
 * From JavaDoc
   https://csse2002.uqcloud.net/assignment/1/tms/route/Route.html
 * public class Route
 *
 * Represents a one-way connection between intersections
 * All routes have a string identifer (ID), an origin intersection, a default
   speed, a congestion calculator, up to one of each type of sensor, and up
   to one of each type of signal (TrafficLight and SpeedSign).
 * Inherited methods from class Object: clone, finalize, getClass, notify,
   notifyAll, wait, wait, wait
 */
public class Route {
    // Unique string identifier for this Route. Formatted as Route.FROM:Route.To
    private String id;
    private int speedLimit;
    private Intersection from; // Originating intersection for this route.
    // if speedSign == null or trafficLight == null then one DNE for this route.
    private SpeedSign speedSign = null;
    private TrafficLight trafficLight = null;
    private List<Sensor> sensors = new ArrayList<>();

    /**
     * Creates a new route with the given ID, origin intersection and default
       speed.
     * Note: id not necessarily unique (no uniqueness checks required).
     *
     * @param defaultSpeed the default speed for the one-way route
     * @param from the intersection from which this route originates from
     * @param id the unique string identifier for this route
     */
    public Route(String id, Intersection from, int defaultSpeed) {
        this.id = id;
        this.from = from;
        this.speedLimit = defaultSpeed;
    }

    /**
     * Creates and adds a new electronic speed sign to this route.
     * If an electronic speed sign already exists on this route, it should be
       overwritten.
     *
     * @param initialSpeed initial speed limit to be displayed on speed sign
     * @throws IllegalArgumentException if the given speed is negative

     * @requires initialSpeed <= 0
     * @ensures this.speedSign is set to the respective new speedSign object.
     */
    public void addSpeedSign(int initialSpeed) {
        if (initialSpeed <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.speedSign = new SpeedSign(initialSpeed);
        }

    }

    /**
     * Adds a TrafficLight signal to the route. It should default to RED.
     */
    public void addTrafficLight() {
        // The TrafficSignal's value is set in the TrafficLight constructor.
        this.trafficLight = new TrafficLight();
    }

    /**
     * Returns the intersection at which this route begins.
     *
     * @return the intersection from which this route originates
     */
    public Intersection getFrom() {
        return from;
    }

    /**
     * Returns a new list containing all the sensors on this route.
     * Adding/removing sensors from this list should not affect the route's
       internal list of sensors.
     *
     * @return list of all sensors on this route
     */
    public List<Sensor> getSensors() {
        // Clone the list so that the instance variable cannot be edited.
        return new ArrayList<>(sensors);
    }

    /**
     * Returns the currently active speed limit for vehicles on this route.
     * If an electronic speed sign is present, return its displayed speed.
       Otherwise, return the default speed limit of the route. (int
     defaultSpeed)
     *
     * @return the current speed limit of this route
     */
    public int getSpeed() {
        if (hasSpeedSign()) {
            return speedSign.getCurrentSpeed();
        }
        return speedLimit;
    }

    /**
     * Returns the traffic light signal on the route, or null if none exists
     *
     * @return the TrafficLight instance deployed on the route
     */
    public TrafficLight getTrafficLight() {
        return this.trafficLight;
    }

    /**
     * Returns true if this route has an electronic speed sign; false otherwise.
     *
     * @return whether an electronic speed sign is present on this route
     */
    public boolean hasSpeedSign() {
        // If speedSign == null then this route does not contain a speed sign
        // Marginally more economical than creating a boolean variable.
        return this.speedSign != null;
    }

    /**
     * Sets the traffic signal if there is a traffic light controlling traffic
       flow on this route.
     * If there is no traffic light for this route, no action should be taken.
     *
     * @param signal the traffic light signal to set
     */
    public void setSignal(TrafficSignal signal) {
        if (trafficLight != null) {
            this.trafficLight.setSignal(signal);
        }
        // else do nothing
        // need to check if trafficLight is null, as running null.setSignal
        // will throw an exception.
    }

    /**
     * Sets the speed limit of this route to the given value.
     * This method will only change the speed displayed on electronic speed
       signs. If this route doesn't have a SpeedSign, throw an exception and
       take no action.
     * @param newSpeed the new speed to be displayed on the speed sign
     * @throws IllegalStateException if the route has no electronic speed sign
     * @throws IllegalArgumentException if the speed given is negative
     */
    public void setSpeedLimit(int newSpeed) {
        speedLimit = newSpeed;

        if (hasSpeedSign()) {
            if (speedLimit < 0) {
                // Speed limit is negative
                throw new IllegalArgumentException();
            }

            // else is not required as the if statement terminates with an
            // IllegalArgumentException
            this.speedSign.setCurrentSpeed(newSpeed);
        }
        else throw new IllegalStateException();
    }

    /**
     * Adds a sensor to the route if a sensor of the same type is not already on
       the route.
     * @param sensor the sensor to be added to the route
     * @throws  DuplicateSensorException if the sensor to add is of the same
       type as the sensor deployed on this route
     */
    public void addSensor(Sensor sensor) throws DuplicateSensorException {
        for (Sensor existingSensor: sensors) {
            if (existingSensor.getClass() == sensor.getClass()) {
                throw new DuplicateSensorException();
            }
        }

        sensors.add(sensor);
    }

    /**
     * Returns the string representation of this route.
     * The format of the string to return is "id:defaultSpeed:numberOfSensors",
       where 'id' is our identifier string,
       'defaultSpeed' is the default speed of this route, and
       'numberOfSensors' is the number of sensors of all types currently on
       this route.

     * If this route has a SpeedSign, then the format to be returned should
       instead be "id:defaultSpeed:numberOfSensors:speedSignSpeed" where
       'speedSignSpeed' is the current speed limit indicated on the speed sign.
     *
     * If this route has any sensors, the format to be returned should be the
       same as above, with an additional line for information pertaining to
       each sensor on the route. The order in which these lines appear should
       be alphabetical, meaning a line for a pressure plate (PP) should come
       before a line for a speed camera (SC).
     *
     * Each sensor line should contain that sensor's string representation as
       returned by its specific toString method,
       e.g. DemoPressurePad.toString().
     *
     * Note: System.lineSeparator() should be used to separate lines.
     * @return the formatted string representation of the object.
     */
    @Override
    public String toString() {
        // Construct the 'base' of the toString output (which is the same
        // pattern for every route)
        StringBuilder output = new StringBuilder();
        output.append(id).append(":").append(speedLimit).append(":");
        output.append(sensors.size());

        // Check if the route has a speed sign and if so, append the
        // appropriate strings.
        if (hasSpeedSign()) {
            output.append(":").append(getSpeed());
        }

        StringBuilder pp = new StringBuilder();
        StringBuilder sc = new StringBuilder();
        boolean hasPressurePad = false;
        boolean hasSpeedCamera = false;

        // Append the sensor representations if any
        for (Sensor s: sensors) {
            if (s instanceof DemoPressurePad) {
                pp.append(System.lineSeparator()).append(s.toString());
                hasPressurePad = true;
            }
            else {
                sc.append(System.lineSeparator()).append(s.toString());
                hasSpeedCamera = true;
            }
        }

        if (hasPressurePad) output.append(pp);
        if (hasSpeedCamera) output.append(sc);

        return output.toString();
    }
}
