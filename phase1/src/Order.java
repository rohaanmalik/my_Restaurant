import java.util.*;

/**
 * An Order class.
 * An Order has a Cook, the MenuItem it is associated with, the Table number of the Table it ordered as well
 * as a Server.
 * The class also has a static variable to assign an Order number to each Order which is incremented.
 * Finally, it has an ArrayList of extras, which are additions or subtractions made by the customer.
 */
public class Order extends Observable{

    private Cook cook;
    private static int totalOrders = 1;
    private int order_num;
    private MenuItem menuItem;
    private int table_num;
    private String status;
    private Server server;
    public ArrayList<String> extras = new ArrayList<>();

    /**
     * Create an instance of an Order.
     * This will assign an Order number, and then increment the static variable to assign a new number
     * for the next Order.
     * It also checks whether there is enough InventoryItems to create this.
     *
     * @param tableNumber the Table number of the customer who ordered this
     * @param menuItem_ordered the MenuItem which this Order is
     * @param s the Server who took down the Order, and will deliver
     */
    public Order(int tableNumber, MenuItem menuItem_ordered, Server s){
        this.menuItem = menuItem_ordered;
        // order num starts with 0 then
        order_num = totalOrders;
        this.server = s;

        totalOrders += 1;
        this.table_num = tableNumber;

        Set ingredients = menuItem.getInventoryItem().keySet();

        boolean enoughItems = true;
        for (Object i: ingredients){
            if(((InventoryItem) i).getTotalQuantity() < menuItem.getInventoryItem().get(i)) {
                enoughItems = false;
                break;
            }
        }
        if(enoughItems) {
            this.changeStatus("ordered");
        }
        else {
            this.changeStatus("rejected");
            System.out.println("Order #" + this.order_num + " rejected, not enough inventory to prepare.");
        }
    }

    /**
     * Return the current status of this Order
     * @return String with the status
     */
    public String getStatus() { return status; }

    /**
     * Return the Server who is responsible for delivering this Order
     * @return Server object.
     */
    public Server getServer(){return server;}

    /**
     * Return the number of this specific Order.
     * @return int representing this Order's number.
     */
    public int getOrder_num() {
        return order_num;
    }

    /**
     * Return the MenuItem which this Order is based on.
     * @return MenuItem object.
     */
    public MenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Return the Table number of the Table which ordered this
     * @return int representing the Table number.
     */
    public int getTable_num() {
        return table_num;
    }

    /**
     * Set an active Cook object tasked with preparing this Order.
     * @param cook Cook object that will prepare this.
     */
    public void setCook(Cook cook) {
        this.cook = cook;
    }

    /**
     * Get the Cook tasked with preparing this
     * @return Cook who is preparing this.
     */
    public Cook getCook() {
        return cook;
    }

    /**
     * Change the current status of this Order only after a specific event has occurred.
     * If the status has changed, then notify the Observer.
     * @param newStatus String representation of the new status to be set.
     */
    public void changeStatus(String newStatus){
        boolean runCheck = false;
        if (newStatus.equals("ordered")){
            this.status = "ordered";
            // server should change the status
            runCheck = true;
        }

        if (newStatus.equals("seen") && this.status.equals("ordered")){
            this.status = "received by chef (id: " + this.cook.getCookId()
                    + ").";
            // cook should change the status
            runCheck = true;
        }

        if (newStatus.equals("prepared") && this.status.equals("received by chef (id: " + this.cook.getCookId() + ").")){
            this.status = "prepared";
            //cook should change the status
            // Now the server should be able to read the message that the food is ready to deliver
            runCheck = true;
        }

        if (newStatus.equals("delivered") && this.status.equals("prepared")){
            this.status = "delivered";
            // Server should change the status
            runCheck = true;
        }

        if (newStatus.equals("returned") && this.status.equals("delivered")){
            this.status = "returned";
            // a new dish should be made
            // the reason for the problem should be recorded somewhere
            runCheck = true;
        }

        if (newStatus.equals("accepted") && this.status.equals("delivered")){
            this.status = "accepted";
            // a new dish should be made
            // the reason for the problem should be recorded somewhere
            runCheck = true;
        }
        if (runCheck) {
            setChanged();
            notifyObservers(generateMessage());
        }
    }

    /**
     * Create the string representation that will be logged for this specific Order.
     * This includes the Order number, Table number and current status of the Order.
     * @return String to be passed to Observer.
     */
    public Object generateMessage(){
        String rstring = "Order #" + this.order_num + ", ";
        rstring += this.toString() + " for Table #" + this.table_num + " has been " + this.status + ".";
        return rstring;
    }

    /**
     * If the customer requests any InventoryItem additions to the MenuItem, then add them here.
     * @param item InventoryItem to be added to the Order.
     */
    public void additions(InventoryItem item){

        boolean added = false;

        // first search in the map, if teh inventoryItem exists, if so add to it
        for (InventoryItem key: menuItem.getInventoryItem().keySet()) {
            if (item.equals(key)){

                //Searches for item in inventoryItem.keyset
                for (InventoryItem i: menuItem.getInventoryItem().keySet()){
                    if (i.equals(item)){

                        //Increases the value of item by 1
                        menuItem.getInventoryItem().put(key, menuItem.getInventoryItem().get(i) + 1);
                        extras.add(item.getName() + " +1");
                        added = true;
                        break;
                    }
                }
            }
        }

        if (!added) {
            // could find the item in its recipe, therefore, adding it to map.
            menuItem.getInventoryItem().put(item, 1);
            extras.add(item.getName() + " +1");

        }
    }

    /**
     * If the customer requests any InventoryItem subtractions to the MenuItem, then subtract them here.
     * @param item InventoryItem to be subtracted from the Order.
     */
    public void subtract(InventoryItem item){
        boolean added = false;

        // first search in the map, if teh inventoryItem exists, if so add to it
        for (InventoryItem key: menuItem.getInventoryItem().keySet()) {
            if (item.equals(key)){

                //Searches for item in inventoryItem.keyset
                for (InventoryItem i: menuItem.getInventoryItem().keySet()){
                    if (i.equals(item)){

                        //Increases the value of item by 1
                        menuItem.getInventoryItem().put(key, menuItem.getInventoryItem().get(i) - 1);
                        extras.add(item.getName() + " -1");
                        added = true;
                        break;
                    }
                }
            }
        }

        if (!added){
            // could find the item in its recipe, therefore, adding it to map.
            menuItem.getInventoryItem().put(item, 1);
            extras.add(item.getName() + " -1");
        }
    }

    /**
     * Create a String representation fo this Order, including all of the additions and subtractions
     * made by the customer.
     * @return String representation of this specific Order.
     */
    @Override
    public String toString() {

        StringBuilder returnThis = new StringBuilder(menuItem.getName() + " changes(");
        if(extras.size() == 1) {
            returnThis.append(extras.get(0));
        }
        else if (extras.size() > 1){
            returnThis.append(extras.get(0));
            for (int i=1; i < extras.size(); i++) {
                returnThis.append(", ");
                returnThis.append(extras.get(i));
            }
        }

        returnThis.append(")");
        return returnThis.toString();

    }
}
