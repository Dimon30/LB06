package Commands;

import Organization.Organization;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for actions with command: clear
 */
public class Clear extends Command{
    private static final String name = "clear";
    private final static String description = ": Clear collection;";
    private static String[] arg;
    private static Vector<Organization> org;

    /**
     * Function to get name of command
     * @return name of command
     */
    public static String getName(){return name;}
    /**
     * Function to get description of command
     * @return description of command
     */
    public static String getDescription(){return description;}

    /**
     * Function clear collection
     */
    public static String clear(){
        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        try {
            org.clear();
            return "Now the collection is empty.\n";
        } catch (Exception e) {
            return "Error cleaning..\n";
        }

    }
    public String execute(){return clear();}
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
}
