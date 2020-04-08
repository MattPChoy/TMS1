package intersection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tms.intersection.Intersection;
import tms.route.Route;
import tms.util.RouteNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntersectionTest{
    private Intersection A, B, C, D, E, F, G, H;

    @Before
    public void setup() {
        A = new Intersection("A");
        B = new Intersection("B");
        C = new Intersection("C");
        D = new Intersection("D");
        E = new Intersection("E");
        F = new Intersection("F");
        G = new Intersection("G");
        H = new Intersection("H");
    }

    @Test
    public void intersectionConstructor_correctID() {
        assertEquals("A", A.getId());
    }

    @Test
    public void intersectionConstructor_emptyStringID() {
        Intersection empty = new Intersection("");
        assertEquals("", empty.getId());
    }

    @Test
    public void intersectionConstructor_nullID() {
        Intersection nullIntersection = new Intersection(null);
        assertNull(nullIntersection.getId());
    }

    @Test
    public void addConnection_throwsNoErrors() throws RouteNotFoundException {
        A.addConnection(B, 10);
        assertEquals(10, A.getConnection(B).getSpeed());
    }

    @Test
    public void addConnection_throwsNoErrors1() throws RouteNotFoundException {
        A.addConnection(B, 0);
        assertEquals(0, A.getConnection(B).getSpeed());
    }

    @Test
    public void addConnection_correctID() throws RouteNotFoundException {
        A.addConnection(B, 10);
        assertEquals("B:A:10:0", A.getConnection(B).toString());
    }

    @Test
    public void addConnection_throwsIllegalArgumentException() {
        boolean success = false;

        try{
            A.addConnection(B, -10);
        }
        catch (IllegalArgumentException e){
            success = true;
        }
        finally {
            if (!success){
                fail();
            }
        }
    }

    @Test
    public void addConnection_throwsIllegalStateException() {
        boolean success = false;

        A.addConnection(B, 10);

        try{
            A.addConnection(B, 50);
        }
        catch (IllegalStateException e){
            success = true;
        }
        finally {
            if (!success){
                fail();
            }
        }
    }

    @Test
    public void getConnection_returnObjectIsCorrect() throws RouteNotFoundException {
        A.addConnection(B, 50);
        assertEquals("B:A:50:0", A.getConnection(B).toString());
    }

    @Test
    public void getConnection_throwsRouteNotFoundException() {
        boolean success = false;
        try {
            A.getConnection(B);
        }
        catch (RouteNotFoundException r){
            success = true;
        }
        finally {
            if (!success) fail();
        }
    }

    @Test
    public void getConnections_emptyListForNoConnections(){
        assertEquals(new ArrayList<Route>(), A.getConnections());
    }

    @Test
    public void getConnections_oneConnection() throws RouteNotFoundException {
        A.addConnection(B, 50);

        Route r = A.getConnection(B);
        List<Route> expected = new ArrayList<>();
        expected.add(r);

        assertTrue(checkRouteElements(expected, A.getConnections()));
    }

    @Test
    public void getConnections_twoConnection() throws RouteNotFoundException {
        A.addConnection(B, 50);
        A.addConnection(C, 60);

        Route r1 = A.getConnection(B);
        Route r2 = A.getConnection(C);
        List<Route> expected = new ArrayList<>();
        expected.add(r1);
        expected.add(r2);

        assertTrue(checkRouteElements(expected, A.getConnections()));
    }

    @Test
    public void getConnections_multipleConnections() throws
            RouteNotFoundException {
        A.addConnection(B, 50);
        A.addConnection(C, 60);
        A.addConnection(D, 70);
        A.addConnection(E, 80);
        A.addConnection(F, 90);
        A.addConnection(G, 100);
        A.addConnection(H, 110);

        List<Route> expected = Arrays.asList(A.getConnection(B),
                A.getConnection(C), A.getConnection(D), A.getConnection(E),
                A.getConnection(F), A.getConnection(G), A.getConnection(H));

        assertTrue(checkRouteElements(expected, A.getConnections()));
    }

    @Test
    public void getConnections_testRemoveNode(){
        A.addConnection(B, 50);
        A.addConnection(C, 60);
        A.addConnection(D, 70);

        List<Route> initialList = A.getConnections();
        List<Route> listToEdit = A.getConnections();

        listToEdit.remove(1);

        Assert.assertEquals(initialList, A.getConnections());
    }

    @Test
    public void getConnectedIntersections_noConnections(){
        List<Intersection> expected = new ArrayList<>(); // Empty
        assertTrue(checkIntersectionElements(expected,
                A.getConnectedIntersections()));
    }

    @Test
    public void getConnectedIntersections_oneConnection(){
        List<Intersection> expected = Collections.singletonList(B);
        A.addConnection(B, 50);
        assertTrue(checkIntersectionElements(expected,
                A.getConnectedIntersections()));
    }

    @Test
    public void getConnectedIntersections_twoConnections(){
        List<Intersection> expected = Arrays.asList(B, C);
        A.addConnection(B, 50);
        A.addConnection(C, 60);

        assertTrue(checkIntersectionElements(expected,
                A.getConnectedIntersections()));
    }

    @Test
    public void getConnectedIntersections_multipleConnections(){
        List<Intersection> expected = Arrays.asList(B, C, D, E, F, G, H);
        A.addConnection(B, 50);
        A.addConnection(C, 60);
        A.addConnection(D, 70);
        A.addConnection(E, 80);
        A.addConnection(F, 90);
        A.addConnection(G, 100);
        A.addConnection(H, 110);

        assertTrue(checkIntersectionElements(expected,
                A.getConnectedIntersections()));
    }

    @Test
    public void getConnectedIntersection_testRemoveNode(){
        A.addConnection(B, 50);
        A.addConnection(C, 60);
        A.addConnection(D, 70);

        List<Intersection> initialList = A.getConnectedIntersections();
        List<Intersection> listToEdit =  A.getConnectedIntersections();

        listToEdit.remove(1);

        assertEquals(initialList, A.getConnectedIntersections());
    }

    @Test
    public void reduceIncomingSpeedSigns_routeWithNoSpeedSign() throws
            RouteNotFoundException {
        A.addConnection(B, 50);

        A.reduceIncomingSpeedSigns();

        assertEquals(50, A.getConnection(B).getSpeed());
    }

    @Test
    public void reduceIncomingSpeedSigns_routeWithSpeedSign1() throws
            RouteNotFoundException{
        A.addConnection(B, 50);
        A.getConnection(B).addSpeedSign(65);
        A.reduceIncomingSpeedSigns();

        assertEquals(55, A.getConnection(B).getSpeed());
    }

    @Test
    public void reduceIncomingSpeedSigns_routeWithSpeedSign2() throws
            RouteNotFoundException{
        A.addConnection(B, 50);
        A.getConnection(B).addSpeedSign(55);
        A.reduceIncomingSpeedSigns();

        assertEquals(50, A.getConnection(B).getSpeed());
    }

    @Test
    public void reduceIncomingSpeedSigns_routeWithSpeedSign3() throws
            RouteNotFoundException{
        A.addConnection(B, 50);
        A.getConnection(B).addSpeedSign(20);
        A.reduceIncomingSpeedSigns();

        assertEquals(20, A.getConnection(B).getSpeed());
    }

    @Test
    public void toString_testCorrectOutput(){
        assertEquals("A", A.toString());
    }


    public boolean checkRouteElements(List<Route> list1, List<Route> list2){
        if (list1.size() != list2.size()){
            return false;
        }

        for (Route r : list1){
            if (!list2.contains(r)){
                return false;
            }
        }
        return true;
    }

    public boolean checkIntersectionElements(List<Intersection> list1,
                                             List<Intersection> list2){
        if (list1.size() != list2.size()){
            return false;
        }

        for (Intersection r : list1){
            if (!list2.contains(r)){
                return false;
            }
        }
        return true;
    }
}