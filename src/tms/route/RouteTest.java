package tms.route;


import org.junit.Assert;
import org.junit.Test;
import tms.intersection.Intersection;
import tms.sensors.DemoPressurePad;
import tms.sensors.DemoSpeedCamera;
import tms.sensors.Sensor;
import tms.util.DuplicateSensorException;
import tms.util.RouteNotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RouteTest {
    // (Trivial Test Case)
    // Test that the values of route id, from and default speed are set correctly.
    @Test
    public void RouteInstantiator_TestInstantiationValues(){
        Intersection i = new Intersection("A");
        Route r = new Route("A:B", i, 40);

        Assert.assertEquals("A:B", r.toString().substring(0, 3));
        Assert.assertEquals(i, r.getFrom()); // Equals works in this context as they are the same object.
        Assert.assertEquals(40, r.getSpeed());
    }

    // Test that the traffic light is instantiated with the correct signal
    @Test
    public void getTrafficLight_TestSignal() throws RouteNotFoundException {
        Intersection i = new Intersection("A");
        Route r = new Route("routeID", i, 40);
        r.addTrafficLight();
        Assert.assertEquals(TrafficSignal.RED, r.getTrafficLight().getSignal());
    }

    // Test that the traffic light is instantiated with the correct signal
    @Test
    public void setSignal_CorrectSignalValue() throws RouteNotFoundException {
        Intersection i = new Intersection("A");
        Route r = new Route("routeID", i, 40);
        r.addTrafficLight();
        Assert.assertEquals( TrafficSignal.RED, r.getTrafficLight().getSignal());
    }

    // Test that the output from the getSensors method is correct if there are no sensors.
    @Test
    public void getSensors_NoSensorsReturnsEmptyList(){
        Route r = new Route("routeID", new Intersection("A"), 4);
        List<Sensor> actual = r.getSensors();

        List<Sensor> expected = new ArrayList<>(){{add(null); add(null);}};

        Assert.assertEquals(expected, actual);
    }

    // Test that the output from the getSensors method is correct if there are two sensors.
    @Test
    public void getSensors_TwoSensorsReturnsPopulatedList() throws DuplicateSensorException {
        Route r = new Route("routeID", new Intersection("A"), 4);
        Sensor sensor1 = new DemoSpeedCamera(new int[] {1,2,3,4}, 44);
        Sensor sensor2 = new DemoPressurePad(new int[] {1,2,3,4}, 44);

        r.addSensor(sensor1);
        r.addSensor(sensor2);

        List<Sensor> actual = r.getSensors();

        List<Sensor> expected = new ArrayList<>(){{
            add(sensor1);
            add(sensor2);
        }};

        Assert.assertEquals(expected, actual);
    }

    // Test that editing of the list output from getSensors does not edit the instance variable.
    @Test
    public void getSensors_InstanceVariableIsNotEdited() throws DuplicateSensorException {
        Route r = new Route("routeID", new Intersection("B"), 4);
        Sensor sensor1 = new DemoSpeedCamera(new int[] {1,2,3}, 44);
        Sensor sensor2 = new DemoPressurePad(new int[] {1,2,3}, 44);

        r.addSensor(sensor1);
        r.addSensor(sensor2);

        List<Sensor> actual = r.getSensors();


        actual.remove(0);

        List<Sensor> expected = new ArrayList<>(){{
            add(sensor1);
            add(sensor2);
        }};

        actual = r.getSensors();

        Assert.assertEquals(expected, actual);
    }

    // Test that the hasSpeedSign method returns the correct boolean value
    @Test
    public void hasSpeedSign_returnsCorrectBooleanValue(){
        Route r = new Route("routeID", new Intersection("A"), 40);

        Assert.assertFalse(r.hasSpeedSign());

        r.addSpeedSign(40);

        Assert.assertTrue(r.hasSpeedSign());
    }

    // Test that the getSpeed value returns the correct value for with and without a speed sign.
    @Test
    public void getSpeed_returnsCorrectValueWithAndWithoutSpeedSign(){
        Route r = new Route("routeId", new Intersection("A"), 40);

        Assert.assertEquals(40, r.getSpeed());

        r.addSpeedSign(100);

        Assert.assertEquals(100, r.getSpeed());
    }

    // Test that the setSignal method is working and is able to set the traffic light object to all 4 colours/enums
    @Test
    public void setSignal_cycleThroughValues(){
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addTrafficLight();

        r.setSignal(TrafficSignal.RED);
        Assert.assertEquals(TrafficSignal.RED, r.getTrafficLight().getSignal());

        r.setSignal(TrafficSignal.YELLOW);
        Assert.assertEquals(TrafficSignal.YELLOW, r.getTrafficLight().getSignal());

        r.setSignal(TrafficSignal.GREEN);
        Assert.assertEquals(TrafficSignal.GREEN, r.getTrafficLight().getSignal());

        r.setSignal(TrafficSignal.ERROR);
        Assert.assertEquals(TrafficSignal.ERROR, r.getTrafficLight().getSignal());
    }

    // Test that old speed sign objects are correctly overwritten by the addSpeedSign method
    @Test
    public void addSpeedSign_overwritingObjects(){
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addSpeedSign(50);
        Assert.assertEquals(50, r.getSpeed());

        r.addSpeedSign(100);
        Assert.assertEquals(100, r.getSpeed());
    }

    // Test that the setSpeedLimit method throws an error if there is no speed sing
    @Test (expected = IllegalStateException.class)
    public void setSpeedLimit_noSpeedSign(){
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.setSpeedLimit(30);

        Assert.assertEquals(30, r.getSpeed());
    }

    // Test that the setSpeedLimit method correctly sets the speed limit of a route with valid parameters.
    @Test
    public void setSpeedLimit_validValues(){
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addSpeedSign(100);

        Assert.assertEquals(100, r.getSpeed());

        r.setSpeedLimit(30);

        Assert.assertEquals(30, r.getSpeed());
    }

    // Test that the setSpeedLimit method correctly throws an IllegalArgumentException if the new speed is negative.
    @Test (expected = IllegalArgumentException.class)
    public void setSpeedLimit_negativeValues(){
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addSpeedSign(100);

        Assert.assertEquals(100, r.getSpeed());

        r.setSpeedLimit(-30);
    }

    // Test that the addSensor method throws a duplicateSensorException if a sensor of the same type (DemoSpeedCamera)
    // exists
    @Test (expected = DuplicateSensorException.class)
    public void addSensor_DuplicateDemoSpeedCameraThrowsDuplicateSensorException() throws DuplicateSensorException {
        Route r = new Route("routeId", new Intersection("A"), 40);

        Sensor s1 = new DemoSpeedCamera(new int[]{1, 2}, 40);
        Sensor s2 = new DemoSpeedCamera(new int[]{4, 7}, 50);

        r.addSensor(s1);
        r.addSensor(s2);
    }

    // Test that the addSensor method throws a duplicateSensorException if a sensor of the same type (DemoPressurePad)
    // exists
    @Test (expected = DuplicateSensorException.class)
    public void addSensor_DuplicateDemoPressurePadThrowsDuplicateSensorException() throws DuplicateSensorException {
        Route r = new Route("routeId", new Intersection("B"), 40);

        Sensor s1 = new DemoPressurePad(new int[]{1, 2}, 40);
        Sensor s2 = new DemoPressurePad(new int[]{4, 7}, 50);

        r.addSensor(s1);
        r.addSensor(s2);
    }

    // Test that the toString method returns id:defaultSpeed:numberOfSensors if there are no speed sign and no sensors
    @Test
    public void toString_noSpeedSignOrSensors(){
        Route r = new Route("routeId", new Intersection("B"), 40);

        String expected = "routeId:40:0";

        Assert.assertEquals(expected, r.toString());
    }

    // Test that the toString method returns id:defaultSpeed:numberOfSensors if there are no speed sign and no sensors
    @Test
    public void toString_speedSignButNoSensors(){
        Route r = new Route("routeId", new Intersection("B"), 40);
        r.addSpeedSign(100);
        String expected = "routeId:40:0:100";

        Assert.assertEquals(expected, r.toString());
    }

    // Test that toString method returns id:defaultSpeed:numberOfSensors if there is a speed sign and a PressurePad
    @Test
    public void toString_speedSignAndPressurePad() throws DuplicateSensorException {
        Route r = new Route("routeId", new Intersection("B"), 40);
        r.addSpeedSign(100);

        Sensor s1 = new DemoPressurePad(new int[]{1,2,3,4,5}, 3);
        r.addSensor(s1);

        String expected = "routeId:40:1:100" + System.lineSeparator() + s1.toString();

        Assert.assertEquals(expected, r.toString());
    }

    // Test that toString method returns id:defaultSpeed:numberOfSensors if there is a speed sign and a Speed Cameera
    @Test
    public void toString_speedSignAndSpeedCamera() throws DuplicateSensorException {
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addSpeedSign(100);

        Sensor s2 = new DemoSpeedCamera(new int[]{1,2,3,4,5}, 3);
        r.addSensor(s2);

        String expected = "routeId:40:1:100" + System.lineSeparator() + s2.toString();

        Assert.assertEquals(expected, r.toString());
    }

    // Test that toString method returns id:defaultSpeed:numberOfSensors if there are no speed sign and no sensors
    @Test
    public void toString_speedSignAndTwoSensors() throws DuplicateSensorException {
        Route r = new Route("routeId", new Intersection("A"), 40);
        r.addSpeedSign(100);

        Assert.assertEquals(new ArrayList<>(){{add(null); add(null);}}, r.getSensors());

        Sensor s1 = new DemoPressurePad(new int[]{1,2,3,4,5}, 3);
        Sensor s2 = new DemoSpeedCamera(new int[]{1,2,3,4,5}, 3);
        r.addSensor(s1);
        r.addSensor(s2);

        String expected = "routeId:40:2:100" + System.lineSeparator() + s1.toString()
                + System.lineSeparator() + s2.toString();

        Assert.assertEquals(expected, r.toString());
    }
}




































