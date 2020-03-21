package tms.route;

import org.junit.Assert;
import org.junit.Test;

public class SpeedSignTest {
    // Test that a speed sign object is created with the correct value for speed.
    @Test
    public void SpeedSignInstantiation_PositiveValue() {
        SpeedSign s = new SpeedSign(3);
        Assert.assertEquals(3, s.getCurrentSpeed());
    }

    // Test that a speed sign object can still be instantiated with negative values.
    @Test
    public void SpeedSignInstantiation_NegativeValue() {
        SpeedSign s = new SpeedSign(-3);
        Assert.assertEquals(-3, s.getCurrentSpeed());

    }

    // Test that we can set the speed displayed on a speed sign object
    @Test
    public void setCurrentSpeed_PositiveValue(){
        SpeedSign s = new SpeedSign(-3);
        Assert.assertEquals(-3, s.getCurrentSpeed());

        s.setCurrentSpeed(30);
        Assert.assertEquals(30, s.getCurrentSpeed());
    }
}
