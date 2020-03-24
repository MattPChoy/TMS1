package intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.intersection.Intersection;
import tms.route.Route;
import tms.util.RouteNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class IntersectionTest{
    // Test that editing the returned list does not edit the instance variable
    @Test
    public void getConnectedIntersections_testRemoveNode(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 40);

        List<Route> a = A.getConnections();
        List<Route> _a = new ArrayList<>(a);

        a.remove(0);

        Assert.assertEquals(_a, A.getConnections());
    }

    // Test that the method throws a RouteNotFoundException when a route is not found
    @Test (expected = RouteNotFoundException.class)
    public void getConnection_testNoRouteThrowsRouteNotFoundException() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        Route actual = A.getConnection(B);
    }

    
}