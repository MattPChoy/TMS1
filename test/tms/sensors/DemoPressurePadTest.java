package sensors;

import org.junit.Assert;
import org.junit.Test;
import tms.sensors.DemoPressurePad;

public class DemoPressurePadTest {
    // Test that the instance variables data, threshold are set on instantiation
    @Test
    public void demoPressurePadInstantiation_InstanceVariablesSet() {
        DemoPressurePad d = new DemoPressurePad(new int[] {1,2,3,4},40);

        int expectedCongestion = (int) (((float) 1 / (float) 40) * (float) 100);
        Assert.assertEquals(expectedCongestion, d.getCongestion());
    }

    // Test that the oneSecond method wraps the data to the start
    @Test
    public void oneSecond_testEpochWrapping() {
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4},
                40);

        d.oneSecond(); // 2

        int expectedCongestion = (int) (((float) 1 / (float) 40) * (float) 100);
        Assert.assertEquals(expectedCongestion, d.getCongestion());

        d.oneSecond(); // 3

        expectedCongestion = (int) (((float) 2 / (float) 40) * (float) 100);
        Assert.assertEquals(expectedCongestion, d.getCongestion());

        d.oneSecond(); // 4

        expectedCongestion = (int) (((float) 3 / (float) 40) * (float) 100);
        Assert.assertEquals(expectedCongestion, d.getCongestion());

        d.oneSecond(); // 1 (Wrap to start)

        expectedCongestion = (int) (((float) 4 / (float) 40) * (float) 100);
        Assert.assertEquals(expectedCongestion, d.getCongestion());
    }

    // Test that the value of threshold is set correctly.
    @Test
    public void getThreshold_testCorrectValue() {
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4},
                40);
        Assert.assertEquals(40, d.getThreshold());
    }

    // Test that the toString method returns the correct value
    @Test
    public void toString_CorrectValue() {
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4}, 40);
        Assert.assertEquals("PP:40:1,2,3,4", d.toString());
    }

    // Test that the countTraffic method returns the correct value
    @Test
    public void countTraffic_testCorrectValue() {
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4},
                40);

        Assert.assertEquals(1, d.countTraffic());
    }

    // Test that the calculation of congestion returns the correct value
    @Test
    public void getCongestion_correctValue() {
        DemoPressurePad d = new DemoPressurePad(new int[]{30,2,3,4}, 40);

        int expected = (int) (
                ((float) 30 / (float) 40) * (float) 100
        );

        Assert.assertEquals(expected, d.getCongestion());
    }

    // Test that negative values return a congestion of 0.
    @Test
    public void getCongestion_testUpperBound() {
        DemoPressurePad d = new DemoPressurePad(new int[]{1000}, 3);

        int expected = 100;

        Assert.assertEquals(expected, d.getCongestion());
    }
}
