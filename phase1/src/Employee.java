import java.util.ArrayList;

/**
 * Abstract class to for all Employees to follow (eg. Manager, Cook, Server).
 * Contains the attributes id, online and salary.
 */
public abstract class Employee {

    /**
     *  The attributes of the class are the int id which is the unique id each employee
     *  will have. The boolean online which is whether the employee is online or not.
     *  The double which is the salary of the employee.
     */

    public int id;
    public boolean online = false;
    public double salary;
    public ArrayList<Object> notificationList;

    /**
     * Intializes the Employee with a unique int id and a notificationList.
     *
     * @param id the unique id that each Employee is represented numerically by.
     */
    public Employee(int id){
        this.id = id;
        this.notificationList = new ArrayList<Object>();
    }

    /**
     * Returns a boolean of whether the Employee is online or not.
     *
     * @return a boolean value that when True means that the Employee is online.
     */
    public boolean isOnline() { return online; }

    /**
     * Adds a notification to the notification list of this Employee.
     *
     * @param e the notification being added.
     */
    public void addToNotifications(Notification e){ notificationList.add(e); }

    /**
     * Updates the status of whether the cook is online or not using a boolean parameter
     * status.
     *
     * @param status the boolean that dictates whether the cooks is online or not.
     */
    public void updateStatus(boolean status){
        this.online = status;
    }

    /**
     * Returns the Employees id which is a int.
     *
     * @return A unique int that each Employee has.
     */
    public int getId() { return id; }

}
