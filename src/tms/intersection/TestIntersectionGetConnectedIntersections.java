package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.route.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestIntersectionGetConnectedIntersections {
     // Test if a positive defaultSpeed successfully creates a new route with appropriate connections as retrieved from
     // the getConnectedIntersections method.
    @Test
    public void getConnectedIntersections_NoConnections(){
        Intersection A = new Intersection("A");
        List<Route> actual = A.getConnections();
        List<Route> expected = new ArrayList<>();

        Assert.assertEquals(expected, actual);
    }

     // Test if a network with no connections has the correct output for the getConnectedIntersections method
    @Test
    public void getConnectedIntersections_SingleConnection(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 40);

        String actual = A.getConnections().toString();
        String expected = "[B:A:40:0]";
        Assert.assertEquals(expected, actual);
    }
}
