package tms.intersection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tms.util.RouteNotFoundException;

public class TestIntersectionAddConnection {
    /**
     * Test if a positive default speed creates a connection or route
     */
    @Test
    public void intersectionAddConnection_ValidValues1(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 3);
    }

    /**
     * Test if a default speed of 0 creates creates a connection
     */
    @Test
    public void intersectionAddConnection_ValidValues2(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 0);
    }
    /**
     * Test if a positive default speed creates a connection
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test (expected = IllegalArgumentException.class)
    public void intersectionAddConnection_InvalidValues1(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, -3);

        thrown.expect(IllegalArgumentException.class);
        // thrown.expectMessage(); // Optional but not required for testing exception message
    }

    /**
     * Test if an IllegalStateException is thrown if the connection already exists.
     */
    @Test (expected = IllegalStateException.class)
    public void intersectionAddConnection_ExistingRoute(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 3);
        A.addConnection(B, 3);
    }

    /**
     * Test if a circular route is able to be created
     */
    @Test
    public void intersectionAddConnection_CircularRoute(){
        Intersection A = new Intersection("A");

        A.addConnection(A, 10);
    }

    /**
     * Test if the route ID (as derived from the toString method) is formatted correctly (from:to)
     */
    @Test
    public void intersectionAddConnection_toString() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 10);

        String expectedRouteId = "B:A:10:0"; // from:to:speedLimit:numberOfSensors

        Assert.assertEquals(expectedRouteId, A.getConnection(B).toString());
    }

    /**
     * Test that the intersection class can call the reduceIncomingSpeedSigns() method.
     */
    @Test
    public void intersectionAddConnection_SpeedSignReduction1() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 15);

        A.getConnection(B).addSpeedSign(100);
        A.reduceIncomingSpeedSigns();

        Assert.assertEquals(90, A.getConnection(B).getSpeed());
    }
}
