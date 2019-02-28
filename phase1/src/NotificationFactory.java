import java.util.ArrayList;

/**
 * A NotificationFactory class.
 * This class is tasked with creating the proper notification in response to a specific event.
 */
public class NotificationFactory {

    /**
     * Create a notification associated with an order or prepared event.
     * These will either notify the Cook of an Order by a customer, or the Server that the
     * Order has been prepared.
     * @param occurence A string of the status of the Order.
     * @param order The Order object itself.
     */
    public void getNotification(String occurence, Order order){
        if (occurence.equals("received by chef")){
            new OrderNotification(false, order);
        }
        else if (occurence.equals("prepared")){
            new PreparedNotification(false, order);
        }
    }

    /**
     * Create a notification associated with the delivery of or the depletion of an InventoryItem.
     * This will notify the managers of the depletion of an InventoryItem, and search for anyone
     * who is online to let them know of a shipment outside.
     * @param verity Whether InventoryItem is low or not, informing the NotificationFactory which type of notification
     *               is needed.
     * @param item The InventoryItem we are creating a notification about.
     * @param employeeList ArrayList of Employees, passed in to be informed of something.
     */
    public void getNotification(boolean verity, InventoryItem item, ArrayList<Employee> employeeList){
        if(verity){
            new InventoryNotification(employeeList, item);
        }
        else { new DeliveryNotification(employeeList); }
    }

}
