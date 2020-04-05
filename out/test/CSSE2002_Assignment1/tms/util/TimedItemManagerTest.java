package util;

import org.junit.Test;
import static org.junit.Assert.*;

import tms.sensors.DemoSpeedCamera;
import tms.util.TimedItemManager;


public class TimedItemManagerTest {
    @Test
    public void TimedItemManager_singletonPattern(){
        TimedItemManager t1 = TimedItemManager.getTimedItemManager();
        TimedItemManager t2 = TimedItemManager.getTimedItemManager();

        DemoSpeedCamera sc1 = new DemoSpeedCamera(new int[] {1,2}, 40);
        DemoSpeedCamera sc2 = new DemoSpeedCamera(new int[] {1,2}, 40);

        assertSame(t1, t2);
    }

    @Test
    public void TimedItemManager_oneSecondIsCalled(){
        TimedItemManager t = TimedItemManager.getTimedItemManager();

        DemoSpeedCamera sc1 = new DemoSpeedCamera(new int[] {1, 2, 3, 4}, 40);
        DemoSpeedCamera sc2 = new DemoSpeedCamera(new int[] {10, 11, 12, 13},
                40);

        assertEquals(1, sc1.averageSpeed());
        assertEquals(10, sc2.averageSpeed());

        t.oneSecond();

        assertEquals(2, sc1.averageSpeed());
        assertEquals(11, sc2.averageSpeed());

        t.oneSecond();

        assertEquals(3, sc1.averageSpeed());
        assertEquals(12, sc2.averageSpeed());

        t.oneSecond();

        assertEquals(4, sc1.averageSpeed());
        assertEquals(13, sc2.averageSpeed());

        t.oneSecond();

        assertEquals(1, sc1.averageSpeed());
        assertEquals(10, sc2.averageSpeed());
    }

}
