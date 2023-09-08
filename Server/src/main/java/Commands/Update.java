package Commands;

import Organization.*;
import java.util.*;

/**
 * Class for working with command: update
 */
public class Update extends Command{
    private static final String name = "update";
    private final static String description = ": Update value of field for organization from collection;";
    private static String[] arg;
    private static Vector<Organization> org;
    private static Organization o;

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
     * Function to update values of organization in collection by id
     */
    public static String update(){
        try{
            Integer id = o.getId();
            org = org.stream().filter(o  -> o.getId() != id).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            org.add(o);
        } catch (Exception e){
            return "Error for updating";
        }
        return "Organization's been successfully updated";
    }

    //public String execute(){return update();}
    public Vector<Organization> getOrg(){return org;}
    public void setArg(String[] arg){this.arg = arg;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
    public void setOrganization(Organization or){o = or;}
}
