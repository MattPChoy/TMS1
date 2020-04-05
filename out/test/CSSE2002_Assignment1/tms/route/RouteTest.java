package route;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import tms.intersection.Intersection;
import tms.route.Route;
import tms.route.SpeedSign;
import tms.route.TrafficLight;
import tms.route.TrafficSignal;
import tms.sensors.DemoPressurePad;
import tms.sensors.DemoSpeedCamera;
import tms.sensors.Sensor;
import tms.util.DuplicateSensorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RouteTest {
    Intersection from;
    Route r;

    @Before
    public void setup(){
        from = new Intersection("A");
        r = new Route("routeId", from, 50);
    }

    @Test
    public void routeConstructor_correctParameters(){
        assertEquals("routeId:50:0", r.toString());
    }

    @Test
    public void getFrom_correctIntersection(){
        assertEquals(from, r.getFrom());
    }

    @Test
    public void getTrafficLight_returnsNull(){
        assertNull(r.getTrafficLight());
    }

    @Test
    public void getTrafficLight_returnsTrafficLight(){
        r.addTrafficLight();

        assertNotNull(r.getTrafficLight());
    }

    @Test
    public void getSensors_noSensors(){
        assertEquals(r.getSensors(), new ArrayList<>());
    }

    @Test
    public void getSensors_oneSpeedCamera() throws DuplicateSensorException {
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);
        r.addSensor(sc);

        List<Sensor> expected = Collections.singletonList(sc);
        assertEquals(expected, r.getSensors());
    }

    @Test
    public void getSensors_onePressurePad() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        r.addSensor(pp);

        List<Sensor> expected = Collections.singletonList(pp);
        assertEquals(expected, r.getSensors());
    }

    @Test
    public void getSensors_twoSensors() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);

        r.addSensor(sc);
        r.addSensor(pp);

        assertTrue(r.getSensors().contains(sc));
        assertTrue(r.getSensors().contains(pp));
    }

    @Test
    public void getSensors_editList() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);

        r.addSensor(sc);
        r.addSensor(pp);

        List<Sensor> originalList = r.getSensors();
        List<Sensor> listToEdit = r.getSensors();

        listToEdit.remove(0);

        assertEquals(originalList, r.getSensors());
    }

    @Test
    public void addSpeedSign_throwsNoErrors(){
        r.addSpeedSign(50);
    }

    @Test
    public void addSpeedSign_negativeValue(){
        boolean success = false;

        try{
            r.addSpeedSign(-10);
        }
        catch (IllegalArgumentException e){
            success = true;
        }
        finally {
            if (!success && r.hasSpeedSign()){
                fail();
            }
        }
    }

    @Test
    public void getSpeedSign_false(){
        assertFalse(r.hasSpeedSign());
    }

    @Test
    public void getSpeedSign_true(){
        r.addSpeedSign(50);
        assertTrue(r.hasSpeedSign());
    }

    @Test
    public void getSpeed_noSpeedSign(){
        assertEquals(50, r.getSpeed());
    }

    @Test
    public void getSpeed_speedSign1(){
        r.addSpeedSign(60);
        assertEquals(60, r.getSpeed());
    }

    @Test
    public void getSpeed_speedSign2(){
        r.addSpeedSign(60);
        r.setSpeedLimit(70);
        assertEquals(70, r.getSpeed());
    }

    @Test
    public void addTrafficLight_throwsNoErrors(){
        r.addTrafficLight();
    }

    @Test
    public void addTrafficLight_signalInitiallyRed(){
        r.addTrafficLight();
        assertEquals(TrafficSignal.RED, r.getTrafficLight().getSignal());
    }

    @Test
    public void addTrafficLight_overwriteOldTrafficLight(){
        r.addTrafficLight();
        TrafficLight oldTrafficLight = r.getTrafficLight();
        r.addTrafficLight();
        TrafficLight newTrafficLight = r.getTrafficLight();

        assertNotSame(oldTrafficLight, newTrafficLight);
    }

    @Test
    public void setSignal_trafficSignalGREEN(){
        r.addTrafficLight();
        r.setSignal(TrafficSignal.GREEN);
        assertEquals(TrafficSignal.GREEN, r.getTrafficLight().getSignal());
    }

    @Test
    public void setSignal_trafficSignalRED(){
        r.addTrafficLight();
        r.setSignal(TrafficSignal.RED);
        assertEquals(TrafficSignal.RED, r.getTrafficLight().getSignal());
    }

    @Test
    public void setSignal_trafficSignalYELLOW(){
        r.addTrafficLight();
        r.setSignal(TrafficSignal.YELLOW);
        assertEquals(TrafficSignal.YELLOW, r.getTrafficLight().getSignal());
    }

    @Test
    public void setSignal_trafficSignalERROR(){
        r.addTrafficLight();
        r.setSignal(TrafficSignal.ERROR);
        assertEquals(TrafficSignal.ERROR, r.getTrafficLight().getSignal());
    }

    @Test
    public void setSpeedLimit_throwsIllegalStateException(){
        boolean success = false;

        try{
            r.setSpeedLimit(10);
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
    public void setSpeedLimit_IllegalArgumentException(){
        boolean success = false;
        r.addSpeedSign(60);

        try{
            r.setSpeedLimit(-20);
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
    public void setSpeedLimit_setCorrectValue(){
        r.addSpeedSign(60);
        assertEquals(60, r.getSpeed());
    }

    @Test
    public void addSensor_throwsNoErrors() throws DuplicateSensorException {
        r.addSensor(new DemoSpeedCamera(new int[]{1,2,3,4}, 40));
    }

    @Test
    public void addSensor_throwsDuplicateSensorException1() throws
    DuplicateSensorException{
        r.addSensor(new DemoSpeedCamera(new int[]{1,2,3,4}, 40));
        boolean success = false;

        try {
            r.addSensor(new DemoSpeedCamera(new int[]{1,2,3,4}, 40));
        }
        catch (DuplicateSensorException d){
            success = true;
        }
        finally{
            if (!success){
                fail();
            }
        }
    }

    @Test
    public void addSensor_throwsDuplicateSensorException2() throws
            DuplicateSensorException{
        r.addSensor(new DemoPressurePad(new int[]{1,2,3,4}, 40));
        boolean success = false;

        try {
            r.addSensor(new DemoPressurePad(new int[]{1,2,3,4}, 40));
        }
        catch (DuplicateSensorException d){
            success = true;
        }
        finally{
            if (!success){
                fail();
            }
        }
    }

    @Test
    public void toString_noSensorsOrSpeedSign(){
        assertEquals("routeId:50:0", r.toString());
    }

    @Test
    public void toString_noSensor1SpeedSign(){
        r.addSpeedSign(60);

        String expected = "routeId:50:0:60";

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_1SpeedCameraNoSpeedSign() throws DuplicateSensorException {
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);
        r.addSensor(sc);

        String expected =
                "routeId:50:1" + System.lineSeparator() + sc.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_1PressurePadNoSpeedSign() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        r.addSensor(pp);

        String expected = "routeId:50:1" + System.lineSeparator() + pp.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_1SpeedCamera1SpeedSign() throws DuplicateSensorException {
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);
        r.addSensor(sc);
        r.addSpeedSign(60);

        String expected =
                "routeId:50:1:60" + System.lineSeparator() + sc.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_1PressurePad1SpeedSign() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        r.addSensor(pp);
        r.addSpeedSign(60);

        String expected =
                "routeId:50:1:60" + System.lineSeparator() + pp.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_2Sensors1SpeedSign() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);
        r.addSensor(pp);
        r.addSensor(sc);
        r.addSpeedSign(60);

        String expected =
                "routeId:50:2:60" + System.lineSeparator() + pp.toString()
                + System.lineSeparator() + sc.toString();

        assertEquals(expected, r.toString());
    }

    @Test
    public void toString_2SensorsNoSpeedSign() throws DuplicateSensorException {
        DemoPressurePad pp = new DemoPressurePad(new int[] {1,2,3}, 40);
        DemoSpeedCamera sc = new DemoSpeedCamera(new int[] {1,2,3}, 40);

        r.addSensor(pp);
        r.addSensor(sc);

        String expected =
                "routeId:50:2" + System.lineSeparator() + pp.toString()
                        + System.lineSeparator() + sc.toString();

        assertEquals(expected, r.toString());
    }

}
