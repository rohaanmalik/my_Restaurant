import java.util.ArrayList;

/**
 * A Bill class.
 * A Bill has a total, an ArrayList of Orders, and a Table number associated with it.
 */
public class Bill {
    private double total;
    private ArrayList itemsOrdered;
    private int table_num;

    /**
     * Create an instance of a Bill. Set the total to 0 initially, and create an empty ArrayList
     * to keep track of the orders.
     * @param tablenumber the Table number associated with this bill
     */
    public Bill(int tablenumber){
        this.total = 0;
        this.itemsOrdered = new ArrayList<Order>();
        table_num = tablenumber;
    }

    /**
     * When a customer orders an item, add it to the list of ItemsOrdered in this bill.
     * @param item the Order which has been requested by the customer.
     */
    public void updateItems(Order item){
        itemsOrdered.add(item);
    }


    /**
     * Get the names of all (accepted) Orders in itemsOrdered, as well as their individual prices.
     * Then, add each item's price to give this Bill a total.
     * @return a String with each itemsOrdered name and price
     */
    public String getAllItems() {
        String rBill = "";
        for (Object o : itemsOrdered) {
            // Only charge the customer if they have accepted the food, and update the total accordingly
            if(((Order) o).getStatus() != null) {
                if (((Order) o).getStatus().equals("accepted")) {
                    rBill += ((Order) o).getMenuItem().getName() + ": $" + ((Order) o).getMenuItem().getPrice() + "\n";
                    total += ((Order) o).getMenuItem().getPrice();
                }
            }
        }
        return rBill;
    }

    /**
     * Return a String representation of this Bill to be given to the customers.
     * @return a String of the Bill.
     */
    @Override
    public String toString(){
        String finalBill = "\nTable number " + table_num + "'s bill:\n";
        finalBill += getAllItems();
        finalBill += "Your total is" + " $" + this.total + "\n" + "Have a nice day!\n";
        return finalBill;
    }

}