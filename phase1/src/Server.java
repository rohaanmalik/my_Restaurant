import java.util.ArrayList;

/**
 * This class represents the server('s) of the restaurant. The methods contained in this class are those
 * that the servers would do while interacting with the application. The server inherits much of their attributes
 * and their methods from the Employee super/ abstract class.
 */
public class Server extends Employee {

    /**
     * Initializes the server with a unique Int id.
     *
     * @param id the unique id that each Employee has.
     */
    public Server(int id) {
        super(id);
    }

    /**
     * Returns the bill for the table with table number tableNumber.
     * The method only works for tables that are being served by this server.
     *
     * @param tableNumber the unique table number that the bill is being requested for.
     * @return the total amount that is on the bill.
     */
    public double getBill(int tableNumber){
        return 0.0;
    }


    /**
     * Returns a String representation of the Server. This consists of the Server's Employee id.
     */
    @Override
    public String toString() {
        return "Servere (Id: " + super.id + ")";
    }

}
