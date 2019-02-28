/**
 * A HandleBill class.
 * This class will deal with input from events.txt that involve the table's Bill.
 */
public class HandleBill implements EventHandler{

    /**
     * Print out the bill for a specific table and afterwards proceed to clean the table.
     * This will remove the bill from the table as well as the associated server.
     * @param bills An array of strings as shown: [bill, table number].
     * @param restaurant1 The Restaurant object.
     */
    @Override
    public void process(String[] bills, Restaurant restaurant1) {
        int tableNum = Integer.valueOf(bills[1]);

        Table table = restaurant1.getTableList().get(tableNum - 1);

        System.out.println(table.getBill());

        table.cleanTable();

    }
}

