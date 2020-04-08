package intersection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tms.intersection.Intersection;
import tms.route.Route;
import tms.route.SpeedSign;
import tms.sensors.Sensor;
import tms.util.RouteNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DIntersectionTest {
    // test intersection
    private Intersection defaultIntersection;
    private Intersection speedSignIntersection1;

    // intersection to be connected to defaultIntersection
    private Intersection connectedIntersection1;
    private Intersection connectedIntersectionTest;

    //intersection to be connected to speedSignIntersection1
    private Intersection speedSignConnectedIntersection1;
    private Intersection speedSignConnectedIntersection2;
    private Intersection speedSignConnectedIntersection3;

    // intersection not connected to defaultIntersection
    private Intersection randomIntersection;


    @Before
    public void setUp()  {
        defaultIntersection = new Intersection("1");
        speedSignIntersection1 = new Intersection("4");
        randomIntersection = new Intersection("2");

        // Intersections to be connected to defaultIntersection
        connectedIntersection1 = new Intersection("3");
        connectedIntersectionTest = new Intersection("test");

        // Connect connectedIntersection1 to defaultIntersection
        defaultIntersection.addConnection(connectedIntersection1, 80);

        // intersection with a speed sign on connection
        speedSignConnectedIntersection1 = new Intersection("SC1");
        speedSignConnectedIntersection2 = new Intersection("SC2");
        speedSignConnectedIntersection3 = new Intersection("SC3");

        // connect speedSignConnected intersection to speedSignIntersection
        speedSignIntersection1.addConnection(speedSignConnectedIntersection1, 75);
        speedSignIntersection1.addConnection(speedSignConnectedIntersection2, 55);
        speedSignIntersection1.addConnection(speedSignConnectedIntersection3, 40);

        //Add Speed Sign to the connections of speedSignIntersection1
        try {
            speedSignIntersection1.getConnection(speedSignConnectedIntersection1).addSpeedSign(75);
            speedSignIntersection1.getConnection(speedSignConnectedIntersection2).addSpeedSign(55);
            speedSignIntersection1.getConnection(speedSignConnectedIntersection3).addSpeedSign(40);
        } catch (RouteNotFoundException e) {
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getId() {
        Assert.assertEquals("1", defaultIntersection.getId());
    }

    @Test
    public void getConnections() {
        try {
            Route expectedRoute = defaultIntersection.getConnection(connectedIntersection1);
            List<Route> expectedOutput = new ArrayList<>(Arrays.asList(expectedRoute));
            Assert.assertEquals(expectedOutput, defaultIntersection.getConnections());

        } catch (RouteNotFoundException e) {
        }
    }

    @Test
    public void getConnectedIntersections() {
        List<Intersection> expectedOutput = Arrays.asList(connectedIntersection1);
        Assert.assertEquals(expectedOutput, defaultIntersection.getConnectedIntersections());
    }

    /**
     *  test addConnection with an intersection that is not already connected
     *  */
    @Test
    public void addConnection() {
        defaultIntersection.addConnection(connectedIntersectionTest, 120);
        assertEquals(2, defaultIntersection.getConnections().size());

    }

    /**
     * test addConnection with an intersection that is connected. Expected IllegalStateException
     * */
    @Test(expected = IllegalStateException.class)
    public void addConnection2() throws IllegalStateException {
        defaultIntersection.addConnection(connectedIntersection1, 80);
    }

    /**
     * test reduceIncomingSpeedSign with routes with no intersection
     */
    @Test
    public void reduceIncomingSpeedSigns() {
        try {
            defaultIntersection.reduceIncomingSpeedSigns();
            Assert.assertEquals(80, defaultIntersection.getConnection(connectedIntersection1).getSpeed());
        } catch (RouteNotFoundException e){
        }
    }

    /**
     * test reduceIncomingSpeedSign with a route that has a speed sign of 75 km/h
     */
    @Test
    public void reduceIncomingSpeedSigns2(){
        try {
            speedSignIntersection1.reduceIncomingSpeedSigns();
            Assert.assertEquals(65, speedSignIntersection1.getConnection(speedSignConnectedIntersection1).getSpeed());
        } catch (RouteNotFoundException e) {
        }
    }

    /**
     * test reduceIncomingSpeedSign with a route that has a speed sign of 55 km/h
     */
    @Test
    public void reduceIncomingSpeedSigns3(){
        try {
            speedSignIntersection1.reduceIncomingSpeedSigns();
            Assert.assertEquals(50, speedSignIntersection1.getConnection(speedSignConnectedIntersection2).getSpeed());
        } catch (RouteNotFoundException e) {
        }
    }
    /**
     * test reduceIncomingSpeedSign with a route that has a speed of 40 km/h
     */
    @Test
    public void reduceIncomingSpeedSigns4(){
        try {
            speedSignIntersection1.reduceIncomingSpeedSigns();
            Assert.assertEquals(40, speedSignIntersection1.getConnection(speedSignConnectedIntersection3).getSpeed());
        } catch (RouteNotFoundException e) {
        }
    }


    /**
     *  test getConnection with an intersection that is not connected to defaultIntersection
     *  */
    @Test(expected = RouteNotFoundException.class)
    public void getConnection() throws RouteNotFoundException {
        defaultIntersection.getConnection(randomIntersection);
    }

    /**
     *  test getConnection with an intersection  that is connected to defaultIntersection
     *  */
    @Test
    public void getConnection2(){
        try {
            Route expectedOutput = defaultIntersection.getConnections().get(0);
            Assert.assertEquals(expectedOutput,defaultIntersection.getConnection(connectedIntersection1));
        } catch (RouteNotFoundException e) {
        }
    }

    @Test
    public void testToString(){
        Assert.assertEquals("1", defaultIntersection.toString());
    }
}
