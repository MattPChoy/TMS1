package tms.intersection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestIntersectionGetConnectedIntersections {
     // Test if a positive defaultSpeed successfully creates a new route with appropriate connections as retrieved from
     // the getConnectedIntersections method.
    @Test
    public void getConnectedIntersections1(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 40);
        List<Intersection> ConnectionsFromA = A.getConnectedIntersections();
        List<Intersection> ExpectedConnections = Collections.singletonList(B);

        Assert.assertEquals(ExpectedConnections, ConnectionsFromA);
    }

     // Test if a network with no connections has the correct output for the getConnectedIntersections method
    @Test
    public void getConnectedIntersections2(){
        Intersection A = new Intersection("A");
        List<Intersection> connectionsFromA = A.getConnectedIntersections();
        List<Intersection> emptyList = new ArrayList<>();

        // connectionsFromA should be empty if there are no connections to it.
        Assert.assertEquals(connectionsFromA, emptyList);
    }
}
