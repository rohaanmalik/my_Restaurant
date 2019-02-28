import java.util.*;

/**
 * MenuItem has a price, name and Inventory Item.
 */
public class MenuItem {

    private String name;
    private static double price;
    private Map<InventoryItem,Integer> inventoryItem;


    public MenuItem(String name_of_item){
        this.name = name_of_item;
        inventoryItem = new HashMap<>();
    }

    /**
     * @param item the inventory item used to prepare the quantity
     * @param quantity the amount of inventory item used to prepare the menu item
     *
     */
    public void setQuantities(InventoryItem item, int quantity){
        inventoryItem.put(item, quantity);
    }

    /**
     * @return the name of MenuItem
     */
    public String getName() {
        return name.trim();
    }

    /**
     * @return the map of the inventory item used and the amount of the
     * quantity required to prepare the MenuItem
     */
    public Map<InventoryItem, Integer> getInventoryItem(){
        return inventoryItem;
    }

    /**
     * @return the price of the menuItem
     */
    public static double getPrice() {
        return price;
    }

    /**
     * @param price this price used to the price of this menuItem
     */
    public static void setPrice(double price) {
        MenuItem.price = price;
    }

    /**
     * @return the name of teh MenutItem
     */
    @Override
    public String toString() {return getName();}

}
