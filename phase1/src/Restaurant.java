import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A Restaurant class.
 * A Restaurant object is a single restaurant, which has a name and a cuisine.
 * It also has ArrayLists of its Employees, its Tables, Menu and InventoryItems.
 */
public class Restaurant {
    private String name;
    private String cuisine;
    private ArrayList<Manager> managerList = new ArrayList<>();
    private ArrayList<Cook> cookList = new ArrayList<>();
    private ArrayList<Server> serverList = new ArrayList<>();
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<Table> table_list = new ArrayList<>();
    private ArrayList<InventoryItem> inventory_list = new ArrayList<>();
    private ArrayList<Employee>employee_list = new ArrayList<>();
    private ArrayList<Cook> activeCookList = new ArrayList<>();
    private int num_tables;
    private VirtualManager vmanager;



    /**
     * Create a new instance of a Restaurant. This will then create all the Employees
     * in that Restaurant, as well as create the Tables.
     * @param info String[] that contains the name, cuisine, number of Employees and Tables in the Restaurant.
     */
    public Restaurant(String[] info){
        this.name = info[0];
        this.cuisine = info[1];
        int managers = Integer.valueOf(info[2]);
        int cooks = Integer.valueOf(info[3]);
        int servers = Integer.valueOf(info[4]);
        this.num_tables = Integer.valueOf(info[5]);
        // create all the employees
        setStaff(managers, cooks, servers);
        // create new tables
        setTables(num_tables);

        File new_file = new File("requests.txt");
        try (Writer writer = new FileWriter(new_file,false)) {
            writer.write("");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Create all the Employees (Managers, Cooks, Servers) in this Restaurant, and add them to the
     * appropriate ArrayLists.
     * @param managers number of Managers in this Restaurant.
     * @param chefs number of Cooks in this Restaurant.
     * @param servers number of Servers in this Restaurant.
     */
    public void setStaff(int managers, int chefs, int servers) {
        for(int i = 0; i < managers; i++){
            Manager m = new Manager(this.employee_list.size()+1);
            employee_list.add(m);
            managerList.add(m);
        }
        for(int i = 0; i < chefs; i++){
            Cook c  = new Cook(this.employee_list.size()+1);
            employee_list.add(c); cookList.add(c);
        }
        for(int i = 0; i < servers; i++){
            Server s = new Server(this.employee_list.size()+1);
            employee_list.add(s); serverList.add(s);
        }
    }

    /**
     * Create all the Tables in this Restaurant, and add them to the Table ArrayList.
     * @param num_tables The number of Tables in this restaurant.
     */
    public void setTables(int num_tables){
        for(int i = 0; i < num_tables; i++){
            Table table = new Table(i+1);
            this.table_list.add(table);
        }
    }

    /**
     * Set an ArrayList of all the InventoryItems present in the Restaurant at the beginning of
     * the simulation.
     * @param ilist ArrayList of InventoryItems.
     */
    public void setInventory_list(ArrayList<InventoryItem> ilist){
        this.inventory_list = ilist;
    }

    /**
     * Set an ArrayList of all the MenuItems available at this Restaurant at the beginning of
     * the simulation.
     * @param mlist
     */
    public void setMenu(ArrayList<MenuItem> mlist){
        this.menu = mlist;
    }

    /**
     * Return the ArrayList containing all of the InventoryItems currently at the Restaurant.
     * @return ArrayList of InventoryItems.
     */
    public ArrayList<InventoryItem> getInventoryItems() {
        return this.inventory_list;
    }

    /**
     * Return the ArrayList containing all of the Table objects in this Restaurant.
     * @return ArrayList of Tables.
     */
    public ArrayList<Table> getTableList() {
        return this.table_list;
    }

    /**
     * Return the ArrayList containing all fo the Employees in this Restaurant.
     * @return ArrayList of Employees.
     */
    public ArrayList<Employee> getEmployeeList() {
        return this.employee_list;
    }

    public ArrayList<MenuItem> getMenu(){
        return menu;
    }

    public MenuItem getMenuItem(String name_of_menuItem){
        for (MenuItem menuItem: this.getMenu()) {
            if (menuItem.getName().trim().equals(name_of_menuItem)){
                return menuItem;
            }
        }
        // could not find the menuItem
        System.err.println("could not find the menuItem in the Restaurant");
        return null;
    }

    /**
     * * Return the ArrayList containing all fo the Managers in this Restaurant.
     * @return ArrayList of Managers.
     */
    public ArrayList<Manager> getManagerList(){
        return this.managerList;
    }

    /**
     * * Return the ArrayList containing all fo the Cooks in this Restaurant.
     * @return ArrayList of Cooks.
     */
    public ArrayList<Cook> getCookList(){
        return this.cookList;
    }

    /**
     * * Return the ArrayList containing all fo the Servers in this Restaurant.
     * @return ArrayList of Servers.
     */
    public ArrayList<Server> getServerList() { return this.serverList; }

    /**
     * Return the InventoryItem object that has the same name as the String passed into this method.
     * @param item_name A string which is the name of the InventoryItem
     * @return the InventoryItem object with the name specified.
     */
    public InventoryItem getInventoryItem(String item_name){
        for (InventoryItem item: inventory_list) {
            if (item.getName().equals(item_name)){
                return item;
            }
        }
        //If we reach here, then the InventoryItem does not exist
        System.err.println("could not find the inventory item in the list");
        return null;
    }

    /**
     * Set the VirtualManager (Observer) for this Restaurant, which will notify the correct
     * Employees at the correct time.
     * @param vmanager VirtualManager object which will observe the events at this Restaurant.
     */
    public void setVirtualManager(VirtualManager vmanager) {
        this.vmanager = vmanager;
    }


    /**
     * Return the VirtualManager of this Restaurant.
     * @return VirtualManager object asked with observing this Restaurant.
     */
    public VirtualManager getVirtualManager() {
        return vmanager;
    }

    /**
     * Add a Cook to the ArrayList activeCookList only if the Cook is currently online.
     */
    public void activateCooks(){
        for (Cook c: cookList) {
            if (c.isOnline()){
                activeCookList.add((c));
            }
        }
    }

    /**
     * Return a Cook who is currently online, and then remove them from the list so that they are not
     * directly considered for the next Order.
     * @return
     */
    public Cook getActiveCook(){
        Cook temp = activeCookList.get(0);
        activeCookList.remove(0);
        activeCookList.add(temp);
        return temp;

    }

    /**
     * Remove a Cook from the activeCookList ArrayList if they are no longer online.
     */
    public void removeActiveCook(){
        for (Cook cook : activeCookList) {
            if (!cook.isOnline()){
                activeCookList.remove(cook);
            }
        }
    }
}
