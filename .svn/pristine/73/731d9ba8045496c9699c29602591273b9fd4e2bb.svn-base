package tms.sensors;

import org.junit.Assert;
import org.junit.Test;

public class DemoPressurePadTest {
    // Test that the instance variables data, threshold are set on instantiation
    @Test
    public void DemoPressurePadInstantiation_InstanceVariablesSet(){
        DemoPressurePad d = new DemoPressurePad(new int[] {1,2,3,4}, 40);

        Assert.assertEquals(1, d.getCurrentValue());
    }

    // Test that the oneSecond method wraps the data to the start
    @Test
    public void oneSecond_epochWrapping(){
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4}, 40);

        d.oneSecond(); // 2

        Assert.assertEquals(2, d.getCurrentValue());

        d.oneSecond(); // 3

        Assert.assertEquals(3, d.getCurrentValue());

        d.oneSecond(); // 4

        Assert.assertEquals(4, d.getCurrentValue());

        d.oneSecond(); // 1 (Wrap to start)

        Assert.assertEquals(1, d.getCurrentValue());
    }

    // Test that the value of threshold is set correctly.
    @Test
    public void getThreshold_CorrectValue(){
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4}, 40);
        Assert.assertEquals(40, d.getThreshold());
    }

    // Test that the toString method returns the correct value
    @Test
    public void toString_CorrectValue(){
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4}, 40);
        Assert.assertEquals("PP:40:1,2,3,4", d.toString());
    }

    // Test that the countTraffic method returns the correct value (is actually the same as getCurrentValue)
    @Test
    public void countTraffic_correctValue(){
        DemoPressurePad d = new DemoPressurePad(new int[]{1,2,3,4}, 40);

        Assert.assertEquals(1, d.countTraffic());
    }

    // Test that the calculation of congestion returns the correct value
    @Test
    public void getCongestion_correctValue(){
        DemoPressurePad d = new DemoPressurePad(new int[]{300,2,3,4}, 40);

        int expected = (int) (
                ((float) 300 / (float) 40) * (float) 100
        );

        Assert.assertEquals(expected, d.getCongestion());
    }
}
