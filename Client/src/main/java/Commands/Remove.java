package Commands;

import Organization.Organization;

import java.util.Vector;

import static java.lang.Integer.parseInt;

/**
 * Class to working with command: remove_by_id
 */
public class Remove extends Command{
    private static final String name = "remove_by_id";
    private final static String description = ": Remove organization by id;";
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
     * Function to delete organization by id
     */
    public static String remove_by_id(){
        if (arg.length == 0) {
            return "Please input command in format: remove_by_id 'id'" + "\n";
        }
        try {
            Integer id = arg.length > 0 ? parseInt(arg[0]) : -1;
            org = org.stream().filter(o  -> o.getId() != id).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            return "Organization by id '" + arg[0] + "' was successfully deleted" + "\n";
        } catch (Exception e) {
            return "Sorry...\nDon't find organization by this id(" + "\n";
        }
    }
    public String execute(){return remove_by_id();}
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
}
