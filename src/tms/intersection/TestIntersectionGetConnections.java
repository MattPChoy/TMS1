package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;

import java.util.ArrayList;
import java.util.List;

public class TestIntersectionGetConnections {
    /*
     * Testing for the getConnections method
     * getConnections1: Check that an empty list is returned when there are no connections/incoming routes
     * getConnections2: Check that the correct list is returned when there is 1 route.
     */

    // Check that empty list is returned by getConnections when there are no incoming routes
    @Test
    public void getConnections1(){
        Intersection A = new Intersection("A");
        List<Route> incomingRoutes = A.getConnections();
        List<Route> empty = new ArrayList<>();

        Assert.assertEquals(incomingRoutes, empty);
    }

    // Check that the appropriate list is returned when there is 1 route.
    @Test
    public void getConnections2(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 50);
        List<Route> incomingRoutesA = A.getConnections();
        List<Route> expectedRoutesA = new ArrayList<>();
        expectedRoutesA.add(new Route(B.getId() + ":" + A.getId(), B, 50));

        Assert.assertEquals(expectedRoutesA.get(0).getFrom(), incomingRoutesA.get(0).getFrom());
    }
}
