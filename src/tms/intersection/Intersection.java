package tms.intersection;

import tms.route.Route;
import tms.util.RouteNotFoundException;

import java.util.*;

/**
 * From JavaDoc
 * https://csse2002.uqcloud.net/assignment/1/tms/intersection/Intersection.html
 * public class Intersection extends Object

 * Represents a point at which routes can originate and terminate
 * All intersections have a unique identifier (ID), a list of incoming
   connections and, optionally, a set of traffic
   lights.

 * Inherited methods from class Object: clone, finalize, getClass, notify,
   notifyAll, wait, wait, wait
 */

public class Intersection {
    private String id; // the unique string identifier for each intersection
    private List<Route> incomingRoutes = new ArrayList<>();
    List<Intersection> connections = new ArrayList<>();

    /**
     * Creates an intersection with the given identifier
     *
     * Note: (Piazza @124) Do not need to check if the id is unique.
     *                     Do not need to check if the id is an empty
     *                     string/null
     *
     * @param id the unique string identifier for each intersection
     */
    public Intersection(String id) {
        this.id = id; // Update the instance variable.
    }

    /**
     * Returns the ID of this intersection
     * @return id unique string identifier for each intersection
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a new list containing all the incoming connections to this
       intersection.
     * Adding/removing routes from this list should not affect the
       intersection's internal list of connecting
     routes.

     * @return a list of routes;
     */
    public List<Route> getConnections() {
        // Return a copy of the instance variable so that it cannot be
        // edited by code outside of this class.
        return new ArrayList<>(this.incomingRoutes);
    }

    /**
     * Gets a list containing all intersections that have incoming routes to
       this intersection.
     * If no such intersection exists, return an empty list.

     * @return a list of all intersections that feed a route that ends at this
       intersection.
     */
    public List<Intersection> getConnectedIntersections() {
        // Note that connections could be generated when this method is called,
        // by iterating through the incomingRoutes
        // list and appending route.from() to a list and returning that list.

        // Return a copy of the instance variable so that it cannot be edited
        // by code outside of this class.
        return new ArrayList<>(this.connections);
    }

    /**
     * Creates a new Route originating from the given intersection and adds it
       to our list of incoming routes.
     * The ID of the new route should be of the form "from:to" where 'from' is
       the ID of the origin intersection and 'to' is the ID of this
       intersection. The new route should have a default speed given by
       'defaultSpeed', however if this value is negative, then an exception
       should be thrown and no new connection should be added.

     * If this intersection has an IntersectionLights a new traffic light signal
       should be added to the new route.

     * If there is already a connection from 'from', then instead of creating a
       new connection, an IllegalStateException should be thrown and the
       method should do nothing.

     * Note: On Piazza (@124): Do not need to check if a route has been created
       circularly (back to itself)

     * @param from the intersection the connection is from
     * @param defaultSpeed the connecting route's default speed.

     * @throws IllegalStateException if a route already exists connecting this
       intersection and the given intersection
     * @throws IllegalArgumentException if the given default speed is negative.
     */
    public void addConnection(Intersection from, int defaultSpeed) throws
            IllegalStateException {
        if (defaultSpeed < 0) {
            // If the default speed is negative
            throw new IllegalArgumentException("The given default speed, '" +
                    defaultSpeed + "' is negative");
        }

        for (Route r : incomingRoutes) {
            // Search all routes to see whether it already exists.

            String existingRouteID = r.getFrom().getId();
            String newRouteID = from.getId();

            if (existingRouteID.equals(newRouteID)) {
                throw new IllegalStateException();
            }
        }

        // NOTE: A connection route goes from the intersection named
        // 'from' to 'this' intersection.
        String RouteID = from.getId() + ":" + this.getId();
        Route newRoute = new Route(RouteID, from, defaultSpeed);
        incomingRoutes.add(newRoute); // List for getConnections()
        connections.add(from);        // List for getConnectedIntersections()
                                      // method.
    }

    /**
     * Reduces the speed limit on incoming routes to this intersection.
     * All incoming routes with an electronic speed sign should have their speed
     limit changed to be the greater of 50
       and the current displayed speed minus 10.
     *
     * Routes without an electronic speed sign should not be affected.
     *
     * No speed limits should be increased as a result of calling this
     * method, ie. routes with a speed limit of 50 or
       below should not be affected.
     */
    public void reduceIncomingSpeedSigns() {
        for (Route r : incomingRoutes) {
            if (r.hasSpeedSign()) {
                int speedLimit = r.getSpeed();
                if (speedLimit > 50) {
                    r.setSpeedLimit(Math.max(speedLimit - 10, 50));
                }
            }
        }
    }

    /**
     * Given an origin intersection, returns the route that connects it to this
       destination intersection.

     * @param from an intersection that is connected to this intersection
     * @return the route that goes from 'from' to this intersection
     * @throws tms.util.RouteNotFoundException if no route exists from the given
       intersection to this intersection
     */
    public Route getConnection(Intersection from) throws RouteNotFoundException {
        // the getConnection method creates a connection between the
        // intersection "from" and this route.
        // Intersection A (from) --ROUTE A:B--> Intersection B (this)
        for (Route r : incomingRoutes) {
            String originRouteId = r.getFrom().getId();
            String expectedRouteId = from.getId();

            if (expectedRouteId.equals(originRouteId)) {
                return r;
            }
        }

        // The exception is only thrown if we have iterated through the whole of
        // incomingRoutes and no matching routes have been found.
        throw new RouteNotFoundException();
    }

    /**
     * Returns the string representation of this intersection.
     * The format of the string to return is simply "id" where 'id' is this
       intersection's identifier string.

     * For example, an intersection with the an ID of "ABC" and traffic lights
       with a string representation of "3:X,Y,Z"  would have a toString()
       value of "ABC:3:X,Y,Z".

     * @return string representation of this intersection
     */
    @Override
    public String toString() {
        return (id);
    }
}
