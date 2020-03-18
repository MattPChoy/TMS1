package tms.intersection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestIntersectionAddConnection {
    /**
     * Test if a positive default speed creates a connection
     */
    @Test
    public void addConnection1(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 3);
    }

    /**
     * Test if a default speed of 0 creates creates a connection
     */
    @Test
    public void addConnection2(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 0);
    }
    /**
     * Test if a positive default speed creates a connection
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test (expected = IllegalArgumentException.class)
    public void addConnection3(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, -3);

        thrown.expect(IllegalArgumentException.class);
        // thrown.expectMessage(); // Optional but not required for testing exception message
    }

    /**
     * Test if an IllegalStateException is thrown if the connection already exists.
     */
    @Test (expected = IllegalStateException.class)
    public void addConnection4(){
        Intersection A = new Intersection("A");
        Intersection B = new Intersection("B");

        A.addConnection(B, 3);
        A.addConnection(B, 3);
    }

    /**
     * Test if a circular route is able to be created
     */
    @Test
    public void addConnection5(){
        Intersection A = new Intersection("A");

        A.addConnection(A, 10);
    }
}
