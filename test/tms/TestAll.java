import intersection.IntersectionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import route.*;
import util.TimedItemManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        IntersectionTest.class,
        RouteTest.class,
        SpeedSignTest.class,
        TrafficLightTest.class,
        TimedItemManagerTest.class,
        // Util unit tests
})

public class TestAll {
    // Entry point
}
