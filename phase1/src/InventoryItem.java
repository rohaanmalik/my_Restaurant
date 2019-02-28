import java.util.Observable;

/**
 * An InventoryItem is used to create MenuItems
 * It has a threshold below which item would would be ordered.
 */
public class InventoryItem extends Observable {

    private int threshold = 20;
    private String name;
    private int totalQuantity;

    public InventoryItem(String itemName, int quantity){
        this.name = itemName;
        this.totalQuantity = quantity;

    }

    /**
     * @return threshold An int representing the threshold quantity
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Set the Threshold Quantity
     * @param threshold An integer representing the threshold for the InventoryItem.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * @return it returns the total quantity of the InventoryItem
     */
    public int getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * This increases the quantity of the InventoryItem
     * @param increase_qty An integer representing how much to increase inventory.
     */
    public void increaseTotalQuantity(int increase_qty) {

        this.totalQuantity += increase_qty;
        setChanged();
        notifyObservers("We now have " + this.totalQuantity + " " + this.name + ".");
    }

    /**
     * Decreases the total quantity of the InventoryItem
     * @param decrease_qty An integer representing how much to decrease inventory.
     */
    public void decreaseTotalQuantity(int decrease_qty) {
        this.totalQuantity -= decrease_qty;
        if(totalQuantity < threshold){
            setChanged();
            notifyObservers("We only have " + this.totalQuantity + " " + this.name + " left.");
        }
    }

    /**
     * @return this returns the name of the Inventory Item
     */
    public String getName() {
        return name;
    }

    /**
     * @return it returns a string representation of the object
     */
    @Override
    public String toString() {
        return name + " " + totalQuantity;
    }

    /**
     * @param other the object that we are comparing it to
     * @return if the two objects have the same instance and if their names are equal
     */
    @Override
    public boolean equals(Object other) {return other instanceof InventoryItem &&
            this.name.equals(((InventoryItem)other).name);}
}
