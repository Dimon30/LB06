package Commands;

import Organization.Organization;

import java.util.Vector;

/**
 * Class for work with command: info
 */
public class Info extends Command{
    private static final String name = "info";
    private static final String description = ": About collection";
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
     * Function print information about collection
     */
    public static String info() {

        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        Organization date = (Organization)(org.stream().findFirst().orElseThrow());
        int size = (int)org.stream().count();
        String infoReturn = "";
        infoReturn += "Date of initialization collection: " + date.getLastUpdate() + "\n";
        infoReturn += "Size of collection: " + Integer.valueOf(size) + "\n";
        return infoReturn;
    }
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
    public String execute(){return info();}
}
