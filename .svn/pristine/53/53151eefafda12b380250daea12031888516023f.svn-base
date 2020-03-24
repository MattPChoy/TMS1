package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;

import java.util.ArrayList;
import java.util.List;

public class TestIntersectionGetConnections {
    /**
     * Test that a network with no routes returns an empty list.
     */
    @Test
    public void getConnections_testEmptyNetwork(){
        Intersection A = new Intersection("A");
        List<Route> incomingRoutes = A.getConnections();
        List<Route> emptyList = new ArrayList<>();

        Assert.assertEquals(incomingRoutes, emptyList);
    }

    /**
     * Test that a network with one route returns the correct list
     */
    @Test
    public void getConnections_testSingleRouteNetwork(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 50);
        List<Route> incomingRoutesA = A.getConnections();
        List<Route> expectedRoutesA = new ArrayList<>();
        expectedRoutesA.add(new Route(B.getId() + ":" + A.getId(), B, 50));

        Assert.assertEquals(expectedRoutesA.get(0).getFrom(), incomingRoutesA.get(0).getFrom());
    }

    /**
     * Test that the removal of nodes from the returned list does not change the instance variable.
     */
    // Check that the instance variable is not changed by modifying the output list
    @Test
    public void getConnections_testRemoveNode(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 50);
        List<Route> incomingRoutesA = A.getConnections();
        List<Route> newIncomingRoutesA = new ArrayList<>(incomingRoutesA);
        incomingRoutesA.remove(incomingRoutesA.get(0));

        Assert.assertEquals(newIncomingRoutesA, A.getConnections());
    }
}