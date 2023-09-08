package Commands;

import Organization.Organization;

import java.util.Comparator;
import java.util.Vector;

public class Sort extends Command{
    private static final String name = "sort";
    private final static String description = ": Sort collection;";
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
    public static void sort(){
        if (arg.length > 0){
            System.out.println("I don't understand u\n What does it mean: " + getName() + " " + arg[0]);
            return;
        }
        org = org.stream()
                .sorted(Comparator.comparing(Organization::getName))
                .collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
    }
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
}
