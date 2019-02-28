
/**
 * A HandleStatus class.
 * This class will deal with input from events.txt that involve .
 */
public class HandleStatus implements EventHandler{

    /**
     * Setting the statuses of the employees listed in staff to "online" or "offline".
     * @param staff An array of strings as shown: [online/offline, (list of employee numbers)].
     * @param restaurant1 The Restaurant object.
     */
    @Override
    public void process(String[] staff, Restaurant restaurant1) {


            //Selects the list of staff IDs that are online
            String onlineStaff = staff[1];

            //Removes the parentheses from the list
            onlineStaff = onlineStaff.substring(1, onlineStaff.length() - 1);

            //Stores each ID as an element in an array
            String[] staffIds = onlineStaff.split(", ");
            for (String s : staffIds) {
                //Accessing the employee with the specified ID
                Employee employee = ((Employee) restaurant1.getEmployeeList().get(Integer.valueOf(s)-1));


                if (staff[0].equals("online")){
                    employee.updateStatus(true);
                    //Printing a notification
                    System.out.println(employee.toString() + " is now online");
                }

                else if (staff[0].equals("offline")){
                    employee.updateStatus(false);
                    System.out.println(employee.toString() + " is now offline");
                }
                //Updating the employee's status
            }

            if (staff[0].equals("online")){
                restaurant1.activateCooks();
            }

            else if (staff[0].equals("offline")){
                restaurant1.removeActiveCook();
            }


    }
}
