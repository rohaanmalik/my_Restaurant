import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the cook of the restaurant. The methods contained in this class are those
 * that the cook would use while interacting with the application. The cook inherits much of their attributes
 * and their methods from the Employee super/ abstract class.
 *
 */
public class Cook extends Employee {

    /**
     * Initializes the cook with a unique Int id.
     *
     * @param id the unique id that each Employee has.
     */
    public Cook(int id) {
        super(id);
    }


    /**
     * Updates the status of an order to seen as it is received by the cook. This is done
     * per table using the int table_num and the items are found in the ArrayList<Order>
     * named itemsOrdered.
     *
     * @param itemsOrdered is an ArrayList<Order> containing all items that have been order in the restaurant.
     *
     */
    public void confirm(ArrayList<Order> itemsOrdered, int table_num){
        //updates status to recieved/ confirmed
        for (Order m : itemsOrdered){
            if (m.getStatus().equals("ordered")) {
                m.changeStatus("seen");
            }
        }
    }

    /**
     * Updates the status of an order to prepared as it is finished being prepared by the cook.
     * The items are found in the ArrayList<Order> named itemsOrdered.
     *
     * @param item_ordered is an Order containing all items that have been order in the restaurant.
     */
    public void prepared(Order item_ordered) {
        //updates status to prepared
        item_ordered.changeStatus("prepared");

        Map<InventoryItem, Integer> itemsUsed = item_ordered.getMenuItem().getInventoryItem();
        Set<InventoryItem> itemsUsedList = itemsUsed.keySet();
        for (InventoryItem t : itemsUsedList) {
            if (t.getTotalQuantity() >= itemsUsed.get(t)) {
                t.decreaseTotalQuantity(itemsUsed.get(t));
            }
        }
    }

    /**
     * Returns the cook id which is an id int that is unique to each cook.
     *
     * @return A unique int id the only cooks have.
     */
    public int getCookId(){ return super.id; }


    /**
     * Returns a String representation of the Cook. This consists of the Cook's Employee id.
     */
    @Override
    public String toString() {
        return "Cook (Id: " + super.id + ")";
    }
}
