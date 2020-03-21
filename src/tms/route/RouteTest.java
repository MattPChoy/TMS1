package tms.route;


import org.junit.Test;
import tms.intersection.Intersection;
import tms.util.RouteNotFoundException;

import java.util.List;

public class RouteTest {
    @Test
    public void RouteInstantiation1() throws RouteNotFoundException {
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 30);

        // Get the route object
        Route r = A.getConnection(B);
    }
}
