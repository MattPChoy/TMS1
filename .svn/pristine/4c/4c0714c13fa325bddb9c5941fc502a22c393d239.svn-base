package tms.route;

import org.junit.Assert;
import org.junit.Test;

public class TrafficLightTest {
    // Test that the traffic light signal is instantiated as RED
    @Test
    public void trafficLightInstantiation_testSignalIsRED(){
        TrafficLight t = new TrafficLight();
        Assert.assertEquals(TrafficSignal.RED, t.getSignal());
    }

    // Test that the signal can be set (and by extension retrieved by the getter method)
    @Test
    public void setTrafficLight_getTrafficLight_testCorrectValues(){
        TrafficLight t = new TrafficLight();
        Assert.assertEquals(TrafficSignal.RED, t.getSignal());

        t.setSignal(TrafficSignal.GREEN);
        Assert.assertEquals(TrafficSignal.GREEN, t.getSignal());
    }
}
