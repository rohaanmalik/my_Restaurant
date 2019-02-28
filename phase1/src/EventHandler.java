/**
 * A EventHandler interface, when implemented, will be used to handle different commands
 * in events.txt
 */
public interface EventHandler {

    /**
     * Processes the inputs from events.txt.
     * @param list_of_strings An array of strings containing the line from events.txt with an input.
     * @param restaurant The Restaurant Object.
     */
    void process(String[] list_of_strings, Restaurant restaurant);
}
