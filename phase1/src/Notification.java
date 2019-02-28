/**
 * A notification interface, when implemented, will be sent to different Employees
 * informing them on an event.
 */
public interface Notification {

    /**
     * Notify the necessary Employees of the occurrence of an event.
     */
    public void NotifyRecipients();

    /**
     * Set this notification as urgent.
     * @param urgent
     */
    public void setUrgent(boolean urgent);

    /**
     * Get the content associated with this notification.
     * @return String representation of this notification.
     */
    @Override
    public String toString();
}
