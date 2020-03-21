package tms.sensors;

import org.junit.Assert;
import org.junit.Test;

public class DemoSpeedCameraTest {
    // Test that the data is correct.
    @Test
    public void DemoSpeedCamera_TestDataInput(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[]{1,2,3,4,5}, 3);

        Assert.assertEquals("SC:3:1,2,3,4,5", d.toString());
    }

    // Test that the average speed method returns the correct value
    @Test
    public void getAverageSpeed_CorrectValue(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[]{1,2,3,4,5}, 3);

        Assert.assertEquals(1, d.averageSpeed());
    }

    // Test that the getCongestion value is correct
    @Test
    public void getCongestion_IsCorrect(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[]{300,2,3,4,5}, 3);

        int expected = (int) (
                ((float) 300 / (float) 3) * (float) 100
        );

        Assert.assertEquals(expected, d.getCongestion());
    }

    // Test that the output of the toString method is correct
    @Test
    public void toString_CorrectOutput(){
        DemoSpeedCamera d = new DemoSpeedCamera(new int[]{300,2,3,4,5}, 3);

        String expected = "SC:3:300,2,3,4,5";

        Assert.assertEquals(expected, d.toString());
    }
}
