package tms.route;

/**
 * From JavaDoc (https://csse2002.uqcloud.net/assignment/1/tms/route/SpeedSign.html)
 * public class SpeedSign
 *
 * Represents an electronic speed sign, indicating a dynamic speed limit on a route
 *
 * Inherited methods from class Object: clone, equals, finalize, getClass, hashCode, notify,
       notifyAll, toString, wait, wait, wait
 */
public class SpeedSign {
    private int displaySpeed;

    /**
     * Creates a new electronic speed sign with the initial displayed speed.
     * (No verification required).
     * @param initialSpeed the intial speed to be shown on the sign
     */
    public SpeedSign(int initialSpeed) {
        this.displaySpeed = initialSpeed;
    }

    /**
     * Get the speed displayed by the sign (not the speed of the cars on the route
     * @return the current speed limit displayed by the sign
     */
    public int getCurrentSpeed() {
        return displaySpeed;
    }

    /**
     * Sets the speed limit displayed. (No verification required).
     * @param speed the new speed limit to display.
     * */
    public void setCurrentSpeed(int speed) {
        this.displaySpeed = speed;
    }
}
