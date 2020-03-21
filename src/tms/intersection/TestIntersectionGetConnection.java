package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;
import tms.util.RouteNotFoundException;

public class TestIntersectionGetConnection {
    // Test that the method returns the correct route
    @Test
    public void getConnection_ReturnsCorrectRoute() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 40);

        Route actual = A.getConnection(B);

        Route expected = new Route("B:A", B, 40);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    // Test that the method throws a RouteNotFoundException when a route is not found
    @Test (expected = RouteNotFoundException.class)
    public void getConnection_ThrowsRouteNotFoundException() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        Route actual = A.getConnection(B);
    }
}
