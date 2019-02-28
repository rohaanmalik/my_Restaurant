import java.util.ArrayList;

/**
 * This class represents a Table at the restaurant. The methods contained in this class are those
 * that deal with the table.
 *
 */

public class Table {

    private int tableNumber;
    private Bill bill;
    private ArrayList<MenuItem> itemsOrdered;
    private Server server;
    private ArrayList<Order> orderList;

    /**
     * Initializes the table with a unique id tableNumber, a bill, an ArrayList of MenuItem's for the itemsOrdered
     * to the table. Also there is one more ArrayList for the order list.
     *
     * @param tableNumber the unique int that each table has to differentiate between them.
     */
    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        bill = new Bill(tableNumber);
        this.itemsOrdered = new ArrayList<MenuItem>();
        this.orderList = new ArrayList<>();
    }

    /*
     * Returns this Tables tableNumber
     */
    public int getTableNumber() { return tableNumber; }

    /*
     * Returns this Tables bill
     */
    public Bill getBill(){return bill;}
    /*
     * Returns this Tables server
     */
    public Server getServer(){return server;}

    /**
     * Sets the server of this table to Server s.
     *
     * @param s the server that is going to be set to this table.
     */
    public void setServer(Server s){ server = s; }

    /*
     * Returns this Tables ArrayList of items ordered.
     */
    public ArrayList<MenuItem> getItemsOrdered() { return itemsOrdered; }

    /*
     * Returns this Tables ArrayList of orders.
     */
    public ArrayList<Order> getOrderList() { return orderList; }

    /**
     * Returns the order iff it has the status prepared. Otherwise returns null.
     *
     * @param orderName the order being checked on
     * @return the order or null.
     */
    public Order getOrder(String orderName) {
        for (Order o: orderList){
            if(o.getMenuItem().getName().equals(orderName) && o.getStatus().equals("prepared")) {
                return o;
            }
        }
        System.out.println("Order cannot be delivered.");
        return null;
    }

    /**
     * Removes the Order order from the orderList
     *
     * @param order the order being removed form orderList
     */
    public void subtractOrder(Order order){ orderList.remove(order); }

    /**
     * Adds the order to the orderList and updates the bill.
     *
     * @param item_ordered the menuItem being ordered
     * @return the items that was added to orderList
     */
    public Order addOrder(MenuItem item_ordered){
        Order new_order = new Order(getTableNumber(), item_ordered, server);
        orderList.add(new_order);
        bill.updateItems(new_order);
        return new_order;
    }

    /**
     * Resets the table with a new bill and ArrayList for itemsOrdered.
     * Also sets server to null as to be set later.
     */
    public void cleanTable(){
        bill = new Bill(tableNumber);
        this.itemsOrdered = new ArrayList<MenuItem>();
        server = null;
    }

}
