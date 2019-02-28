import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A EventReader class.
 * This class deals with reading the events.txt file and sending the commands to their respective handlers.
 */
public class EventReader {

    private String events;
    private String output;
    private String recipes;
    private Restaurant restaurant1;
    private VirtualManager vmanager;
    private HandleBill billHandler;
    private HandleDeliver deliverHandler;
    private HandleStatus handleStatus;
    private HandleOrder orderHandler;
    private HandlePrepare prepareHandler;
    private HandleReceive receiveHandler;


    /**
     * Creates a new instance of EventReader.
     * This will create new instances of every handler and store them in the handler attributes.
     * The file paths to events.txt, output.txt, and recipes.txt are assigned here as well.
     */
    public EventReader() {
        billHandler = new HandleBill();
        deliverHandler = new HandleDeliver();
        handleStatus = new HandleStatus();
        orderHandler = new HandleOrder();
        prepareHandler = new HandlePrepare();
        receiveHandler = new HandleReceive();
        this.events = "events.txt";
        this.output = "output.txt";
        this.recipes = "recipes.txt";
    }

    /**
     * Reads the recipes.txt file which contains the ingredients + quantities needed for each menu item.
     * Creates instances of MenuItem and InventoryItem and sets the required Inventory items for each MenuItem.
     * @return An ArrayList that contains 2 ArrayLists (list of MenuItems, list of InventoryItems).
     */
    public ArrayList<ArrayList> recipeReader() {
        ArrayList<MenuItem> menu_list = new ArrayList<>();
        ArrayList<InventoryItem> inventory_list = new ArrayList<>();
        try {
            //Read the recipe file
            Scanner scan = new Scanner(recipes);
            File file = new File(scan.nextLine());
            scan = new Scanner(file);
            while (scan.hasNextLine()){
                String firstrecipie = scan.nextLine();
                String [] items = firstrecipie.split("[|]");
                //Get the name of the dish
                MenuItem name = new MenuItem(items[0]);
                //Get the price of the dish, and update it in the MenuItem class
                String item_price = items[1].substring(1, items[1].length());
                name.setPrice(Double.valueOf(item_price.substring(1, item_price.length())));
                menu_list.add(name);
                for(int i = 2; i < items.length; i++){
                    // Find out the inventory items needed for creating the dish, and their quantities
                    String current = items[i].trim();
                    int start_index = current.indexOf("(");
                    int end_index = current.indexOf(")");
                    String item_name = current.substring(0, start_index);
                    int item_quantity = Integer.valueOf(current.substring(start_index + 1, end_index));
                    InventoryItem inventory = new InventoryItem(item_name, 30);
                    boolean verity = true;
                    for(int j = 0; j < inventory_list.size(); j++) {
                        //Do not create duplicate inventory items
                        if(inventory.equals(inventory_list.get(j))){
                            verity = false;
                        }
                    }
                    if(verity){
                        inventory_list.add(inventory);
                        inventory.addObserver(vmanager);
                    }
                    name.setQuantities(inventory, item_quantity);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ArrayList<ArrayList> rlist = new ArrayList<>();
        //Return an arraylist containing two array lists: one for the menu items, and one for the inventory items
        rlist.add(menu_list);
        rlist.add(inventory_list);
        return rlist;
    }

    /**
     * Reads the events.txt file and sends each command line to its respective handler class.
     * The first command creates an instance of Restaurant and VirtualManager in order to setup the new restaurant.
     */
    public void reader() {
        try {
            Scanner scan = new Scanner(events);
            File file = new File(scan.nextLine());
            scan = new Scanner(file);

            String restaurant = scan.nextLine();
            String[] firstline = restaurant.split("[|]");
            for (int i = 0; i < firstline.length; i++) {
                firstline[i] = firstline[i].trim();
            }
            restaurant1 = new Restaurant(firstline);
            vmanager = new VirtualManager(restaurant1);
            restaurant1.setVirtualManager(vmanager);
            ArrayList<ArrayList> lists = recipeReader();
            restaurant1.setMenu(lists.get(0));
            restaurant1.setInventory_list(lists.get(1));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] thisline = line.split("[|]");
                for (int j = 0; j < thisline.length; j++) {
                    thisline[j] = thisline[j].trim();
                }
                if (thisline[0].equals("order")) {
                    orderHandler.process(thisline, restaurant1);
                } else if (thisline[0].equals("deliver")) {
                    deliverHandler.process(thisline, restaurant1);
                } else if (thisline[0].equals("prepared")) {
                    prepareHandler.process(thisline, restaurant1);
                } else if (thisline[0].equals("receive")) {
                    receiveHandler.process(thisline, restaurant1);
                } else if (thisline[0].equals("online") || thisline[0].equals("offline")) {
                    handleStatus.process(thisline, restaurant1);
                } else if (thisline[0].equals("bill")) {
                    billHandler.process(thisline, restaurant1);
                } else {
                    System.out.println("Error: undefined event.");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
