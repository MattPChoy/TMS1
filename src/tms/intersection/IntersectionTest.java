package tms.intersection;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestIntersectionConstructor.class,
    TestIntersectionGetConnections.class,
    TestIntersectionGetConnectedIntersections.class,
    TestIntersectionAddConnection.class,
})

public class IntersectionTest {

}
