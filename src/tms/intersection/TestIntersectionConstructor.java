package tms.intersection;

import org.junit.Assert;
import org.junit.Test;
import tms.util.RouteNotFoundException;

public class TestIntersectionConstructor {
    // Check that the intersection stores and returns the correct value for ID.
    @Test
    public void intersectionConstructor_testValidString(){
        String intersectionID = "A";
        Intersection A = new Intersection(intersectionID);
        Assert.assertEquals(intersectionID, A.getId());
    }

    // Check that the intersection is instantiated when we give it an empty id, as it is technically a valid string
    @Test
    public void intersectionConstructor_testEmptyStringLiteral(){
        String intersectionID = "";
        Intersection A = new Intersection(intersectionID);
        Assert.assertEquals(intersectionID, A.getId());
    }


    //Check that the intersection is instantiated when the id is passed as null

    @Test
    public void intersectionConstructor_testNullString(){
        Intersection A = new Intersection(null);
        Assert.assertNull(A.getId());
    }
}
