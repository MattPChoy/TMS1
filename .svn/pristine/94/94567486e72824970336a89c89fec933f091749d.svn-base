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

        // Intersections B,C -> Intersection A
        System.out.println(A.getConnections());

        int routeBAInitialSpeed = 100;
        A.addConnection(B, routeBAInitialSpeed);

        int routeCAInitialSpeed = 20;
        A.addConnection(C, routeCAInitialSpeed);

        Route BA = A.getConnection(B);
        Route CA = A.getConnection(C);

        BA.addSpeedSign(routeBAInitialSpeed);
        CA.addSpeedSign(routeCAInitialSpeed);

        A.reduceIncomingSpeedSigns();

        int newBASpeed = BA.getSpeed();
        int newCASpeed = CA.getSpeed();

        Assert.assertEquals(routeBAInitialSpeed, newBASpeed+10);
        Assert.assertEquals(50, newCASpeed);
    }


}
