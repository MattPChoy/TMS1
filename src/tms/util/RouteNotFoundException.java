package tms.util;

/**
 * From JavaDoc
 *https://csse2002.uqcloud.net/assignment/1/tms/util/RouteNotFoundException.html
 * public class RouteNotFoundException extends Exception
 *
 * Exception thrown when a request is made for a route that does not exist.
 */
public class RouteNotFoundException extends Exception {
    /**
     * Default constructor for the RouteNotFoundException exception
     * Constructs a normal RouteNotFoundException with no error message or cause
     */
    public RouteNotFoundException() {
        super();
    }

    /**
     * Constructs a RouteNotFoundException that contains a helpful message
       detailing why the exception occurred.
     * Note: implementing this constructor is optional. It has only been
       included to ensure your code will compile if
       you give your exception a message when throwing it. This practice can
       be useful for debugging purposes.
     *
     * Important: do not write JUnit tests that expect a valid implementation
       of the assignment to have a certain error message, as the official
       solution will use different messages to those you are expecting, if any
       at all.

     * @param message detail message of the exception
     */
    public RouteNotFoundException(String message){
        super(message);
    }

    /**
     * Constructs a RouteNotFoundException that contains a helpful message
       detailing why the exception occurred.

     * Note: implementing this constructor is optional. It has only been
       included to ensure your code will compile if you give your exception a
       message when throwing it. This practice can be useful for debugging
       purposes.

     * Important: do not write JUnit tests that expect a valid implementation
       of the assignment to have a certain error
       message, as the official solution will use different messages to those
       you are expecting, if any at all.

     * @param message detail message of the exception
     * @param err cause of the exception
     */
    public RouteNotFoundException(String message, Throwable err){
        super(message, err);
    }
}
