import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An InventoryNotification object. This Notification informs a Manager that they are running low
 * on a certain InventoryItem.
 */
public class InventoryNotification implements Notification {

    private String content;
    private boolean is_urgent;
    private ArrayList<Manager> managers;


    /**
     * Create an instance of an InventoryNotification class.
     * This will set the content (with the appropriate InventoryItem), and send it to a manager.
     * @param employees The list of Employees at the Restaurant.
     * @param item The InventoryItem which has been depleted.
     */
    public InventoryNotification(ArrayList<Employee> employees, InventoryItem item) {
        setContent(item);
        this.is_urgent = is_urgent;
        this.managers = createManagerList(employees);
        NotifyRecipients();
    }

    /**
     * Create a list containing only the online managers at this Restaurant.
     * @param employees ArrayList of all Employees (including Managers, Cooks and Servers).
     * @return ArrayList of Managers only.
     */
    public ArrayList<Manager> createManagerList(ArrayList<Employee> employees){
        ArrayList<Manager> managers = new ArrayList<>();
        for (Employee e : employees) {
            if (e instanceof Manager && e.online) {
                managers.add(((Manager) e));
            }
        }
        return managers;
    }

    /**
     * Notify a random online manager that a specific InventoryItem is running low.
     */
    public void NotifyRecipients(){
        int rand_num = ThreadLocalRandom.current().nextInt(0, managers.size());
        Manager my_manager = managers.get(rand_num);
        my_manager.addToNotifications(this);
    }

    /**
     * Set the content of this InventoryNotification, informing the recipient that an InventoryItem has
     * been depleted, with its name, total quantity left and threshold.
     * @param item the InventoryItem which has been depleted.
     */
    public void setContent(InventoryItem item){
        this.content = "Uh-oh! You only have " + item.getTotalQuantity() + " " + item.getName() + " left. " +
                "We usually have at least " + item.getThreshold() + ".";
    }

    /**
     * Set this InventoryNotification as urgent (to be implemented in Phase 2).
     * @param urgent
     */
    public void setUrgent(boolean urgent){this.is_urgent = urgent;}

    /**
     * Print out this InventoryNotification
     * @return String representation of this InventoryNotification
     */
    @Override
    public String toString() {
        return this.content;
    }

}
