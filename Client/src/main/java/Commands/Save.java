package Commands;

import Organization.Organization;

import java.util.Vector;

/**
 * Class for working with command: save
 */
public class Save extends Command{
    private static final String name = "save";
    private final static String description = ": Save collection in file;";
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
     * Function to save collection in file xml
     */
    public static String save(){
        if (arg.length == 0) {
            return "Please input filename in format: 'save filename'\n" + "\n";
        }
        try {
            String[] a = arg[0].split("\\.");
            String filename = arg[0].contains(".") ? a[0] : arg[0];
            //Write_XML.Write(org.stream(), filename + ".xml");
            return "File was successfully written\n";
        } catch (Exception e){
            return "File's not written\n";
        }
    }
    public String execute(){return save();}
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
}
