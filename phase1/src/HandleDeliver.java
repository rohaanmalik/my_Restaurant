/**
 * A HandleDeliver class.
 * This class will deal with input from events.txt that involve delivery of the food to the tables.
 */
public class HandleDeliver implements EventHandler{

    /**
     * Processes the delivery of the food to a specific table.
     * This will change the status of the order to "delivered" and then to either "accepted" or "rejected".
     * @param delivery An array of strings as shown: [deliver, table #, server #, orders(accepted/returned)].
     * @param restaurant1 The Restaurant object.
     */
    @Override
    public void process(String[] delivery, Restaurant restaurant1) {
        int tableNum = Integer.valueOf(delivery[1]);

        Table table = restaurant1.getTableList().get(tableNum);

        // list of acceptances/rejections looks like [accept, reject]
        String[] responses = delivery[3].split(", ");
        for (String s: responses) {

            int index_open = s.indexOf("{");
            int item_index_open = s.indexOf("(");

            String itemName = s.substring(index_open + 1, item_index_open);

            Order order = table.getOrder(itemName);

            if(order != null) {
                order.changeStatus("delivered");
                if (s.substring(0, index_open).equals("accepted")) {
                    order.changeStatus("accepted");
                } else if (s.substring(0, index_open).equals("returned")) {
                    order.changeStatus("returned");
                }
            }
        }
    }
}
