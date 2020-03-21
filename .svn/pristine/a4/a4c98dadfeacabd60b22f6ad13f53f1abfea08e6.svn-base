package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.util.RouteNotFoundException;

public class TestIntersectionConstructor {
    // Check that an instance of Intersection is created when the intersection id is a sensible value
    @Test
    public void intersectionInstantiation_SensibleValue(){
        String intersectionID = "A";
        Intersection A = new Intersection(intersectionID);

        Assert.assertEquals(intersectionID, A.getId());
    }

    // Check that an instance of Intersection is created when the intersection id is an empty string literal
    @Test
    public void intersectionInstantiation_EmptyStringLiteral(){
        String intersectionID = "";
        Intersection B = new Intersection(intersectionID);
        Assert.assertEquals(intersectionID, B.getId());
    }


    // Check that an instance of Intersection is created when the intersection id is a null string
    @Test
    public void intersectionInstantiation_NullString(){
        Intersection C = new Intersection(null);
    }
}
