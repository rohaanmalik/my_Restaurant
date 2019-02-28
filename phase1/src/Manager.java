import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.Writer;

/**
 * This class represents the Manager of the restaurant. The methods contained in this class are those
 * that the manager would do while interacting with the application. The manager inherits much of their attributes
 * and their methods from the Employee super/ abstract class.
 *
 */
public class Manager extends Employee{

    /**
     * Initializes the manager with a unique Int id.
     *
     * @param id the unique id that each Employee has.
     */
    Manager(int id){
        super(id);
    }

    /**
     * Sends a request to the supplier for 20 units of InventoryItem item.
     *
     * @param item an InventoryItem that the restaurant is running low on.
     */
    public void sendrequest(InventoryItem item){
        sendrequest(item,20);
    }

    /**
     * Sends a request to the supplier for a int user specified number units of InventoryItem item.
     *
     * @param item an InventoryItem that the restaurant is running low on.
     * @param quantity an int that represent the quantity of the InventoryItem the restaurant wants.
     */
    public void sendrequest(InventoryItem item, int quantity){
        File new_file = new File("requests.txt");
        try (Writer writer = new FileWriter(new_file,true)) {
            writer.write("A request has been made for " + quantity + " " + item.getName() + "." + "\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Returns a String representation of the Manager. This consists of the Manager's Employee id.
     */
    @Override
    public String toString() {
        return "Manager (Id: " + super.id + ")";
    }

}
