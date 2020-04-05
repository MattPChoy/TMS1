package route;

import org.junit.Test;
import tms.route.SpeedSign;
import static org.junit.Assert.*;

public class SpeedSignTest {
    @Test
    public void speedSignConstructor_correctSpeed(){
        SpeedSign s = new SpeedSign(10);
        assertEquals(10, s.getCurrentSpeed());
    }

    @Test
    public void setCurrentSpeed_getCurrentSpeed(){
        SpeedSign s = new SpeedSign(10);
        s.setCurrentSpeed(20);

        assertEquals(20, s.getCurrentSpeed());
    }
}
