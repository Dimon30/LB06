package Commands;

import Organization.Organization;
import java.util.Comparator;
import java.util.Vector;

/**
 * Class for working with command: reorder
 */
public class Reorder extends Command{
    private static final String name = "reorder";
    private final static String description = ": Reverse sort collection;";
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
     * Function to reorder collection
     */
    public static String  reorder(){
        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        org = org
                .stream()
                .sorted(Comparator.comparing(Organization::getName).reversed())
                .collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
        return "Collection reversed.";
    }
    public String execute(){return reorder();}
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
}
