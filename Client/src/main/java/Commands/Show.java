package Commands;

import Organization.Organization;

import java.util.Vector;

public class Show extends Command{
    private static final String name = "show";
    private final static String description = ": Show all organizations in collection;";
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

    public static String show() {
        if (arg.length > 0) {
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        if (org.stream().count() == 0) {
            return "Collection is empty\n";
        }
        final String[] showReturn = {"\n"};
        final Integer[] i = {1};
        try{
        org.forEach(o -> {
            showReturn[0] += "Organization #" + i[0] + ":\n";
            i[0] += 1;
            showReturn[0] += o.print();
            showReturn[0] += "-----------------------\n";
        });
        } catch(Exception e) {
            e.printStackTrace();
        }
        return showReturn[0];

    }
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
}
