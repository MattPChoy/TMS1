package tms.util;

import java.util.ArrayList;

/**
 * From JavaDoc (https://csse2002.uqcloud.net/assignment/1/tms/util/TimedItemManager.html)
 * public class TimedItemManager extends Object implements TimedItem
 *
 * Manages timed items for the simulation. All timed items in the simulation need to be registered with this manager.
 * All registered timed items will have their oneSecond() method called when TimedItemManager.oneSecond() is called.
 *
 * This is a singleton class. See the assignment specification and the provided links for more information about the purpose of a singleton and how to implement it.
 *
 * In order to ensure correct singleton behaviour, there should not be a public constructor.
 *
 * Inherited methods from Class object: clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait,
   wait, waitclone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait
 */
public class TimedItemManager implements TimedItem {
    private ArrayList<TimedItem> toCall = new ArrayList<>();
    private static TimedItemManager instance = null;

    /**
     * Creates the timed item manager that stores all the TimedItems in this simulation.
     */
    protected TimedItemManager(){

    }

    /**
     * Gets a singleton instance of the TimedItemManager and makes one if required.
     */
    public static TimedItemManager getTimedItemManager(){
        // Check if an instance of TimedItemManager exists,
        // if not create one
        if (instance == null){
            instance = new TimedItemManager();
        }
        return instance;
    }

    /**
     * A static method to register a TimedItem such that it is called on oneSecond().
     * @param timedItem a TimedItem to register with the manager*/
    public void registerTimedItem(TimedItem timedItem){
        toCall.add(timedItem);
    }

    /**
     * This method will be called by TimedItemManager once every second, provided the model is not in a paused state.
     * Specified by oneSecond interface in TimedItem
     */
    @Override
    public void oneSecond() {
        // Iterate through each item in this.toCall and call them :)
    }
}
