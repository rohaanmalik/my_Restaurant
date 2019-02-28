/**
 * A PreparedNotification class.
 * A PreparedNotification is created when an Order has been finished by the Cook.
 * It is sent to the appropriate Server.
 */
public class PreparedNotification implements Notification {

    private String content;
    private boolean is_urgent;
    private Order order;
    private Server recipient;


    /**
     * Create an instance of a PreparedNotification which will set the content, get the appropriate
     * Server, and send them the notification.
     * @param is_urgent boolean whether this PreparedNotification is urgent.
     * @param order the Order object associated with this PreparedNotification.
     */
    public PreparedNotification(boolean is_urgent, Order order) {
        this.is_urgent = is_urgent;
        this.order = order;
        this.recipient = order.getServer();
        setContent();
        NotifyRecipients();
    }

    /**
     * Notify the appropriate Server that the Cook has prepared this Order, and that they should
     * come pick it up from the kitchen.
     */
    public void NotifyRecipients(){
        recipient.addToNotifications(this);
    }

    /**
     * Create the content of this PreparedNotification.
     * This is what the Server will receive.
     */
    public void setContent(){
        this.content = "Please come pick up " + order.toString() + " for Table #" + order.getTable_num();
    }

    /**
     * Set this PreparedNotification as urgent.
     * @param urgent
     */
    public void setUrgent(boolean urgent){this.is_urgent = urgent;}

    /**
     * Print out this PreparedNotification.
     * @return String representation of this PreparedNotification.
     */
    @Override
    public String toString() {
        return this.content;
    }

}
