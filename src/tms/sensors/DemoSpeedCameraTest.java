package tms.sensors;

import org.junit.Assert;
import org.junit.Test;

public class DemoSpeedCameraTest {
    @Test
    public void DemoSpeedCamera_TestDataInput(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[]{1,2,3,4,5}, 3);

        Assert.assertEquals("SC:3:1,2,3,4,5", d.toString());
    }
}
