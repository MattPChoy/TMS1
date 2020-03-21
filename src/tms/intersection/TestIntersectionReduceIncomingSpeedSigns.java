package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;
import tms.util.RouteNotFoundException;

public class TestIntersectionReduceIncomingSpeedSigns {
    // Test if we can firstly add create a route and add a speed sign to it
    @Test
    public void reduceIncomingSpeedSigns1() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        int routeInitialSpeed = 100;
        A.addConnection(B, routeInitialSpeed);
        Route BA = A.getConnection(B);

        BA.addSpeedSign(routeInitialSpeed);
    }

    // Test if we can reduce the speed of incoming routes.
    @Test
    public void reduceIncomingSpeedSigns2() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");
        Intersection C = new Intersection("C");
        Intersection D = new Intersection("D");

        // Intersections B,C -> Intersection A
        System.out.println(A.getConnections());

        int routeBAInitialSpeed = 60; // Speeds above 60 should be reduced by 10.
        A.addConnection(B, routeBAInitialSpeed);

        int routeCAInitialSpeed = 49; // Speeds under 50 should remain unchanged
        A.addConnection(C, routeCAInitialSpeed);

        int routeDAInitialSpeed = 51; // Speeds from 51 to 59 should be reduced to 50
        A.addConnection(D, routeDAInitialSpeed);

        Route BA = A.getConnection(B);
        Route CA = A.getConnection(C);
        Route DA = A.getConnection(D);

        BA.addSpeedSign(routeBAInitialSpeed);
        CA.addSpeedSign(routeCAInitialSpeed);
        DA.addSpeedSign(routeDAInitialSpeed);

        A.reduceIncomingSpeedSigns();

        int newBASpeed = BA.getSpeed();
        int newCASpeed = CA.getSpeed();
        int newDASpeed = DA.getSpeed();

        Assert.assertEquals(routeBAInitialSpeed, newBASpeed+10);
        Assert.assertEquals(50, newCASpeed);
        Assert.assertEquals(50, newDASpeed);
    }



    /**
     * Test that speed signs can be
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
