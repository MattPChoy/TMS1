package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;
import tms.util.RouteNotFoundException;

public class TestIntersectionReduceIncomingSpeedSigns {
    // Test if we can firstly add create a route and add a speed sign to it
    @Test
    public void reduceIncomingSpeedSigns_testValidInputs() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        int routeInitialSpeed = 100;
        A.addConnection(B, routeInitialSpeed);
        Route BA = A.getConnection(B);

        BA.addSpeedSign(routeInitialSpeed);

        A.reduceIncomingSpeedSigns();

        Assert.assertEquals(90, A.getConnection(B).getSpeed());
    }

    // Test that speeds below 50km/h are not changed by calling the reduceIncomingSpeedSigns method.
    @Test
    public void reduceIncomingSpeedSigns_testValueBelow50() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        // Intersections B,C -> Intersection A

        int routeBAInitialSpeed = 60; // Speeds above 60 should be reduced by 10.
        A.addConnection(B, routeBAInitialSpeed);
        Route BA = A.getConnection(B);
        BA.addSpeedSign(routeBAInitialSpeed);

        A.reduceIncomingSpeedSigns();
        int newBASpeed = BA.getSpeed();
        Assert.assertEquals(routeBAInitialSpeed, newBASpeed+10);
    }

    // Test that speeds from 51km/h to 60km/hr are reduced to 50km/hr
    @Test
    public void reduceIncomingSpeedSigns_testValuesBetween() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");
        Intersection C = new Intersection("C");

        int routeBAInitialSpeed = 51; // Speeds above 60 should be reduced by 10.
        int routeCAInitialSpeed = 59;
        A.addConnection(B, routeBAInitialSpeed);
        A.addConnection(C, routeCAInitialSpeed);
        Route BA = A.getConnection(B);
        Route CA = A.getConnection(C);
        BA.addSpeedSign(routeBAInitialSpeed);
        CA.addSpeedSign(routeCAInitialSpeed);

        A.reduceIncomingSpeedSigns();
        int newBASpeed = BA.getSpeed();
        int newCASpeed = CA.getSpeed();
        Assert.assertEquals(50, newBASpeed);
        Assert.assertEquals(50, newCASpeed);
    }

    // Test that speeds above 61km/hr are reduced by 10km/hr
    @Test
    public void reduceIncomingSpeedSigns_testValuesAbove() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");
        Intersection C = new Intersection("C");

        int routeBAInitialSpeed = 100; // Speeds above 60 should be reduced by 10.
        int routeCAInitialSpeed = 90;
        A.addConnection(B, routeBAInitialSpeed);
        A.addConnection(C, routeCAInitialSpeed);
        Route BA = A.getConnection(B);
        Route CA = A.getConnection(C);
        BA.addSpeedSign(routeBAInitialSpeed);
        CA.addSpeedSign(routeCAInitialSpeed);

        A.reduceIncomingSpeedSigns();
        int newBASpeed = BA.getSpeed();
        int newCASpeed = CA.getSpeed();
        Assert.assertEquals(routeBAInitialSpeed-10, newBASpeed);
        Assert.assertEquals(routeCAInitialSpeed-10, newCASpeed);
    }

    // Test that a route with no speed sign is not affected
    @Test
    public void reduceIncomingSpeedSign_testRouteWithNoSpeedSignRemainsUnchanged() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 100);

        Route BA = A.getConnection(B);

        A.reduceIncomingSpeedSigns();

        Assert.assertEquals(100, BA.getSpeed());
    }
}
