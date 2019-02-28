import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A VirtualManager class.
 * The VirtualManager implements Observer, and is tasked with observing both Observable objects,
 * the Order class as well as the InventoryItem class.
 * The VirtualManager will both print the events to the log, as well as send the necessary notifications.
 */
public class VirtualManager implements Observer {
    private NotificationFactory nFactory;
    private Restaurant restaurant;

    /**
     * Create an instance of a VirtualManager, which observe and update when necessary.
     * It will also create an instance of the NotificationFactory which will notify the
     * Employees of an occurrence.
     * @param restaurant the Restaurant object this VirtualManager is tasked with virtually
     *                   managing.
     */
    public VirtualManager(Restaurant restaurant){
        this.restaurant = restaurant;
        this.nFactory = new NotificationFactory();

    }

    /**
     * Overriding the Observer's update method. This method will first send a notification through
     * the NotificationFactory, and then print out the event (which is currently the logging system
     * implemented).
     * @param o the Observable object, which is either an Order object or an InventoryItem object
     * @param arg the String to be printed into the console, as logging.
     */
    @Override
    public void update(Observable o, Object arg) {
        //check what Observable object this is, and create a Notification accordingly
        if(o instanceof Order) {
            Order order = ((Order) o);
            nFactory.getNotification(order.getStatus(), order);
        } else if (o instanceof InventoryItem){
            InventoryItem item = ((InventoryItem) o);
            boolean verity = false;
            //this will check if the InventoryItem is below threshold, and change verity accordingly
            if(item.getTotalQuantity() < item.getThreshold()){
                verity = true;
                restaurant.getManagerList().get(0).sendrequest(((InventoryItem)o));
            }
            //verity will allow NotificationFactory to know which type of notification to create
            nFactory.getNotification(verity, item, restaurant.getEmployeeList());
        }
        System.out.println(arg);
    }

}
