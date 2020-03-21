package tms.display;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tms.intersection.*;
import tms.route.*;
import tms.sensors.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestIntersectionClasses.class,
        TestRouteClasses.class,
        TestSensorsClasses.class,
        // Util unit tests
})

public class TestAll {
    // Entry point
}
