package sensors;

import org.junit.Test;
import static org.junit.Assert.*;
import tms.util.TimedItemManager;
import tms.sensors.DemoSpeedCamera;

public class DemoSpeedCameraTest {
    // Test that the instance variables data, threshold are set on instantiation
    @Test
    public void demoPressurePadConstructor_setInstanceVariables() {
        DemoSpeedCamera d = new DemoSpeedCamera(new int[] {1, 2, 3, 4},
                40);

        int expectedCongestion = (int) (((float) 1 / (float) 40) * (float) 100);
        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals("40", d.toString().substring(3,5));
        assertEquals("1,2,3,4", d.toString().substring(6,13));
    }

    @Test
    public void countTraffic_valueIteratesWithOneSecond(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[] {40,2,3,4,},
                60);

        double count = 40;
        double threshold = 60;
        double conversionToPercent = 100;

        int expectedCongestion = (int) ((count / threshold) * conversionToPercent);

        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals(40, d.averageSpeed());

        TimedItemManager.getTimedItemManager().oneSecond();

        count = 2;
        threshold = 60;
        conversionToPercent = 100;

        expectedCongestion = (int) ((count / threshold) * conversionToPercent);

        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals(2, d.averageSpeed());

        TimedItemManager.getTimedItemManager().oneSecond();

        count = 3;
        threshold = 60;
        conversionToPercent = 100;

        expectedCongestion = (int) ((count / threshold) * conversionToPercent);

        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals(3, d.averageSpeed());

        TimedItemManager.getTimedItemManager().oneSecond();

        count = 4;
        threshold = 60;
        conversionToPercent = 100;

        expectedCongestion = (int) ((count / threshold) * conversionToPercent);

        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals(4, d.averageSpeed());

        TimedItemManager.getTimedItemManager().oneSecond();

        count = 40;
        threshold = 60;
        conversionToPercent = 100;

        expectedCongestion = (int) ((count / threshold) * conversionToPercent);

        assertEquals(expectedCongestion, d.getCongestion());
        assertEquals(40, d.averageSpeed());
    }

    @Test
    public void getCongestion_testUpperBound(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[] {50, 60}, 40);

        assertEquals(100, d.getCongestion());
        assertEquals(50, d.averageSpeed());
    }

    @Test
    public void getCongestion_testLowerBound(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[] {-10, 3}, 40);

        assertEquals(0, d.getCongestion());
        assertEquals(-10, d.averageSpeed());
    }
}
