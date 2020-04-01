package tms.util;

import java.util.ArrayList;

/**
 * From JavaDoc
   https://csse2002.uqcloud.net/assignment/1/tms/util/TimedItemManager.html
 * public class TimedItemManager extends Object implements TimedItem

 * Manages timed items for the simulation. All timed items in the simulation
   need to be registered with this manager.
 * All registered timed items will have their oneSecond() method called when
   TimedItemManager.oneSecond() is called.
 *
 * This is a singleton class. See the assignment specification and the provided
   links for more information about the purpose of a singleton and how to
   implement it.
 *
 * In order to ensure correct singleton behaviour, there should not be a public
   constructor.
 */
public class TimedItemManager implements TimedItem {
    private ArrayList<TimedItem> toCall = new ArrayList<>();
    private static TimedItemManager instance = null;

    /**
     * Gets a singleton instance of the TimedItemManager and makes one if
       required.
     */
    public static TimedItemManager getTimedItemManager(){
        if (instance == null){ // Check if an instance exists.
            instance = new TimedItemManager(); // Instance DNE, create one.
        }
        return instance; // return the instance.
    }

    /**
     * A static method to register a TimedItem such that it is called on
       oneSecond().
     * @param timedItem a TimedItem to register with the manager
     */
    public void registerTimedItem(TimedItem timedItem){
        toCall.add(timedItem);
    }

    /**
     * This method will be called by TimedItemManager once every second,
       provided the model is not in a paused state.

     * Specified by oneSecond interface in TimedItem
     */
    @Override
    public void oneSecond() {
        for (TimedItem t: toCall){
            // Call the oneSecond method for all registered timedItems.
            t.oneSecond();
        }
    }
}
