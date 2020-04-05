package route;

import org.junit.Before;
import org.junit.Test;
import tms.route.TrafficLight;
import tms.route.TrafficSignal;
import static org.junit.Assert.*;

public class TrafficLightTest {
    private TrafficLight t;

    @Before
    public void setup(){
        t = new TrafficLight();
    }

    @Test
    public void trafficLightConstructor_initialiseAsRed(){
        assertEquals(TrafficSignal.RED, t.getSignal());
    }

    @Test
    public void setSignalGREEN(){
        t.setSignal(TrafficSignal.GREEN);
        assertEquals(TrafficSignal.GREEN, t.getSignal());
    }

    @Test
    public void setSignalRED(){
        t.setSignal(TrafficSignal.RED);
        assertEquals(TrafficSignal.RED, t.getSignal());
    }

    @Test
    public void setSignalYELLOW(){
        t.setSignal(TrafficSignal.YELLOW);
        assertEquals(TrafficSignal.YELLOW, t.getSignal());
    }

    @Test
    public void setSignalERROR(){
        t.setSignal(TrafficSignal.ERROR);
        assertEquals(TrafficSignal.ERROR, t.getSignal());
    }
}
