package tms.util;
/**
 * From JavaDoc
 http://csse2002.uqcloud.net/assignment/1/tms/util/DuplicateSensorException.html
 * public class DuplicateSensorException extends Exception
 *
 * Exception thrown when a sensor is added to a route that already contains a
   sensor of the same type

 * Inherited methods from class Throwable: addSuppressed, fillInStackTrace,
   getCause, getLocalizedMessage, getMessage, getStackTrace, getSuppressed,
   initCause, printStackTrace, printStackTrace, printStackTrace, setStackTrace,
   toString

 * Inherited methods from class Object: clone, equals, finalize, getClass,
   hashCode, notify, notifyAll, wait, wait, wait
 */
public class DuplicateSensorException extends Exception {

    /**
     * Default constructor for the DuplicateSensorException exception
     * Constructs a normal DuplicateSensorException with no error message or
       cause.
     */
    public DuplicateSensorException() {
        super();
    }

    /**
     * Constructs a DuplicateSensorException that contains a helpful message
       detailing why the exception occurred.
     * Note: implementing this constructor is optional. It has only been
       included to ensure your code will compile if you give your exception a
       message when throwing it. This practice can be useful for debugging
       purposes.

     * Important: do not write JUnit tests that expect a valid implementation of
       the assignment to have a certain error message, as the official
       solution will use different messages to those you are expecting, if any
       at all.

     * @param message detail message of the exception
     */
    public DuplicateSensorException(String message) {
        super(message);
    }

    /**
     * Constructs a DuplicateSensorException that contains a helpful message
       detailing why the exception occurred and a cause of the exception.

     * Note: implementing this constructor is optional. It has only been
       included to ensure your code will compile if you give your exception a
       message when throwing it. This practice can be useful for debugging
       purposes.

     * Important: do not write JUnit tests that expect a valid implementation
       of the assignment to have a certain error message, as the official
       solution will use different messages to those you are expecting, if any
       at all.

     * @param message detail message of the exception
     * @param err cause of the exception
     */
    public DuplicateSensorException(String message, Throwable err) {
        super(message, err);
    }
}
