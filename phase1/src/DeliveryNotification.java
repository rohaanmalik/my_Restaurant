import java.util.ArrayList;

/**
 * A DeliveryNotification Object. This Notification informs any online Employees that there is a shipment
 * of an InventoryItem outside to be picked up.
 */
public class DeliveryNotification implements Notification {

    private String content;
    private boolean is_urgent;
    private ArrayList<Employee> employees;


    /**
     * Create a new instance of a DeliveryNotification.
     * This will set the content of notification and then notify the necessary employees of a InventoryItem
     * shipment to be accepted.
     * @param employees An ArrayList of Employees to send the notification.
     */
    public DeliveryNotification(ArrayList<Employee> employees) {
        setContent();
        this.is_urgent = is_urgent;
        this.employees = employees;
        NotifyRecipients();
    }

    /**
     * Notify the first online Employee of the shipment outside so that they can accept it.
     */
    public void NotifyRecipients(){
        for (Employee e : employees){
            if(e.online){
                e.addToNotifications(this);
                break;
            }
        }
    }

    /**
     * Create the content of this DeliveryNotification to appear for chosen Employee.
     */
    public void setContent(){
        this.content = "A delivery has arrived outside. Please accept it, and scan the box.";
    }

    /**
     * Set this DeliveryNotification as urgent (which will be implemented in Phase 2).
     * @param urgent boolean that will set the is_urgent attribute of this notification.
     */
    public void setUrgent(boolean urgent){this.is_urgent = urgent;}

    /**
     * Print out the DeliveryNotification.
     * @return String representation of this DeliveryNotification.
     */
    @Override
    public String toString() {
        return this.content;
    }

}
