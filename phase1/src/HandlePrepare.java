import java.util.ArrayList;

/**
 * A HandlePrepare class.
 * This class will deal with input from events.txt that involve preparing food by the cooks.
 */
public class HandlePrepare implements EventHandler{

    /**
     * Processes the preparation of the food by the cook.
     * This will call the prepared() method of Cook to change the foods status to "prepared" and
     * update the inventory.
     * @param prepared An array of strings as shown: [prepared, table #, server #, order].
     * @param restaurant1 The Restaurant object.
     */
    @Override
    public void process(String[] prepared, Restaurant restaurant1){

        //Get the server and table numbers
        int table_num = Integer.valueOf(prepared[1]);

        Table table = restaurant1.getTableList().get(table_num);

        String[] temp = prepared[3].split(", ");
        ArrayList<String> orders = new ArrayList<>();

        for (String change: temp) {

            int index_open = change.indexOf("(");
            int index_closed = change.indexOf(")");

            if(index_closed - index_open > 1) {
                String inventory_item_change = change.substring(index_open + 1, index_closed);
                String item_no_symbol = inventory_item_change.substring(1, inventory_item_change.length());

                if (inventory_item_change.startsWith("+")) {
                    orders.add(change.substring(0, index_open) + "(" + item_no_symbol + " +1)");
                } else if (inventory_item_change.startsWith("-")) {
                    orders.add(item_no_symbol + " -1");
                }
            }
            else {
                orders.add(change.substring(0, index_open) + "()");
            }
        }
        
        for (Order order: table.getOrderList()){
            order.getCook().prepared(order);
        }
    }
}
