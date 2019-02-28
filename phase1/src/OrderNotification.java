/**
 * An OrderNotification class.
 * An OrderNotification is created when a Server receives an Order from a customer.
 * It is sent to the appropriate Cook.
 */
public class OrderNotification implements Notification{

    private String content;
    private boolean is_urgent;
    private Order order;

    /**
     * Create an instance of an OrderNotification which will set the content, and then send the OrderNotification
     * to the appropriate Cook for them to prepare it.
     * @param is_urgent boolean whether this is urgent.
     * @param order the Order object itself, to be prepared by the Cook.
     */
    public OrderNotification(boolean is_urgent, Order order) {
        this.is_urgent = is_urgent;
        this.order = order;
        setContent();
        NotifyRecipients();
    }

    /**
     * Notify the appropriate Cook that an Order has been made, prompting them to start cooking.
     */
    public void NotifyRecipients(){
        Cook cook = order.getCook();
        cook.addToNotifications(this);
    }

    /**
     * Create the content of the OrderNotification that will appear for the Cook.
     * The content includes a string representation of the Order, its number as well as the Table number.
     */
    public void setContent(){
        this.content = "Order #" + order.getOrder_num() + ", ";
        this.content += order.toString() + " for Table #" + order.getTable_num() + " has been " + order.getStatus();
    }

    /**
     * Set this DeliveryNotification as urgent (which will be implemented in Phase 2).
     * @param urgent boolean that will set the is_urgent attribute of this notification.
     */
    public void setUrgent(boolean urgent){this.is_urgent = urgent;}

    /**
     * Print out the OrderNotification.
     * @return String representation of this OrderNotification.
     */
    @Override
    public String toString() {

        return this.content;
    }

}