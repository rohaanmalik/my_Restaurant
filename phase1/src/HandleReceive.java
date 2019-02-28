import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A HandleReceive class.
 * This class will deal with input from events.txt that involve receiving inventory deliveries.
 */
public class HandleReceive implements EventHandler{

    /**
     * Processes a food delivery action.
     * If the quantity of the delivery is left blank, the quantity is assumed to be 20.
     * @param received An array of strings as shown: [receive, employee #, food delivery(item + quantity)].
     * @param restaurant1 The Restaurant object.
     */
    @Override
    public void process(String[] received, Restaurant restaurant1) {

        ArrayList<String> items_recieved = new ArrayList<>();

        Map<InventoryItem,Integer> item_qty = new HashMap<>();

        int employee_num = Integer.valueOf(received[1]);

        // [cheese(), cucumber(20)]
        for (int i = 2; i < received.length; i++) {
            received[i].trim();
            items_recieved.add(received[i]);
        }

        for (String itemReceieved: items_recieved) {
            int index_open = itemReceieved.indexOf("(");
            int index_close = itemReceieved.indexOf(")");


            // "" or "20"
            String quantity_in_string = itemReceieved.substring(index_open+1,index_close);

            int quantity = 0;

            if (quantity_in_string.equals("")){
                quantity = 20;
            }

            else{
                quantity = Integer.valueOf(quantity_in_string);
            }

            // cheese
            String item = itemReceieved.substring(0,index_open);


            // updating the item_qty map
            for (InventoryItem inventoryItem: restaurant1.getInventoryItems()) {
                if (inventoryItem.getName().equals(item)){
                    item_qty.put(inventoryItem,quantity);
                }
            }
        }

        for (InventoryItem item :item_qty.keySet()) {
            restaurant1.getInventoryItem(item.getName()).increaseTotalQuantity(item_qty.get(item));
            //print out who received the order
            System.out.println("Employee " + employee_num + " received an order: "+ item_qty.get(item)
                    + " " +item.getName() + ".");
        }
    }
}
