import java.util.ArrayList;
import java.util.Observable;

/**
 * A HandleOrder class.
 * This class will deal with input from events.txt that involve ordering food.
 */
public class HandleOrder implements EventHandler {

    /**
     * Processes an order by creating instances of Order with the respective changes to the order.
     * It also adds the corresponding MenuItem object to each Order.
     * Sets the status of the order to "ordered" or "rejected" if there is not enough inventory.
     * @param orders An array of strings as shown: [order, table #, server #, order].
     * @param restaurant The Restaurant object.
     */
    @Override
    public void process(String[] orders, Restaurant restaurant) {

        int tableNum = Integer.valueOf(orders[1]);
        int serverNum = Integer.valueOf(orders[2]);

        boolean found = false;
        boolean online = false;

        for (Employee employee: restaurant.getEmployeeList()) {

            if (employee.getId() == serverNum){
                found = true;
                if (employee.isOnline()){
                    online = true;
                    Table table = restaurant.getTableList().get(tableNum);

                    table.setServer((Server) restaurant.getEmployeeList().get(serverNum));


                    // list of menuItems to be ordered looks like [burger(+cheese), pizza(+cheese)]
                    String[] menuItems = orders[3].split(", ");

                    //list of menuItem
                    ArrayList<Object[]> menuItemArrayList = add_item(menuItems, table, restaurant);

                    //list of orders
                    ArrayList<Order> orderArrayList = table.getOrderList();

                    //getting an active cook
                    Cook my_cook = restaurant.getActiveCook();

                    // add all the items to the table
                    for (Object[] item : menuItemArrayList) {
                        Order order = table.addOrder((MenuItem) item[0]);
                        if(order.getStatus() == null || order.getStatus().equals("rejected")){
                            table.subtractOrder(order);
                        }
                        if (item[1] != null) {
                            changes_to_menuItem(order, (String[]) item[1], restaurant);
                        }
                        order.setCook(my_cook);
                        order.addObserver(restaurant.getVirtualManager());
                    }

                    try { //Do we want to check if there are no online employees?
                        my_cook.confirm(orderArrayList, tableNum);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        if(!found){
            System.out.println("Error: Server # " +serverNum + " does not exist.");
        }
        else if(!online){
            System.out.println("Error: Server # " +serverNum + " is not Online");
        }
    }

    /**
     * Accesses instances of MenuItem according to the menuItems parameter and returns a list of the MenuItem objects.
     * @param menuItems An array of strings representing the orders [burger(+cheese), pizza(+cheese)].
     * @param table The Table object associated with the order.
     * @param restaurant1 The Restaurant object.
     * @return list_of_menuItems An ArrayList of MenuItem objects.
     */
    private ArrayList<Object[]> add_item(String [] menuItems, Table table, Restaurant restaurant1){

        ArrayList<Object[]> list_of_menuItems = new ArrayList<>();

        // create menuItems and add it to the list_of_menuItems
        for (String itemName : menuItems) {

            // [MenuItem, changes]
            Object[] changes = new Object[2];

            int index_open = itemName.indexOf("(");
            int index_closed = itemName.indexOf(")");

            // getting the menuItem
            MenuItem newMenuItem = restaurant1.getMenuItem(itemName.substring(0,index_open));


            // check it the menuItem is with changes or no changes i.e if burger(+cheese,...) or burger()
            if (index_open + 1 != index_closed){

                // list of changes_to_menuItem to be made to the menuItem. looks like [+cheese, -veggies]
                String[] changes_to_menuItem = itemName.substring(index_open+1,index_closed).split(",");
                changes[1] = changes_to_menuItem;
            }

            // adding the menuItem with all the changes in it to the final arrayList
            changes[0] = newMenuItem;
            list_of_menuItems.add(changes);
        }

        return list_of_menuItems;

    }

    /**
     * Adds the order changes to their corresponding Order object.
     * @param order The order object.
     * @param list_of_changes An array of Strings representing the list of changes to each order.
     * @param restaurant1 The Restaurant object.
     */
    private void changes_to_menuItem(Order order, String [] list_of_changes, Restaurant restaurant1){

        for (String change: list_of_changes) {

            String inventory_item_change = change.substring(0,change.length());
            String item_no_symbol = inventory_item_change.substring(1, inventory_item_change.length());

            if (inventory_item_change.startsWith("+")) {

                // search in the inventory, if the inventoryItem is there
                InventoryItem inventoryItem = restaurant1.getInventoryItem(item_no_symbol);

                // and then add an extra unit of it in the MenuItem
                order.additions(inventoryItem);

            }

            else if (inventory_item_change.startsWith("-")) {

                // search in the inventory, if the inventoryItem is there
                InventoryItem inventoryItem = restaurant1.getInventoryItem(item_no_symbol);

                // and then subtract one unit of it in the MenuItem
                order.subtract(inventoryItem);

            }


        }
    }
}