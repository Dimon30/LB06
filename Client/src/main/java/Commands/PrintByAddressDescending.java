package Commands;

import Organization.Organization;
import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * Class for working with command: print_field_descending_postal_address
 */
public class PrintByAddressDescending extends Command{
    private static final String name = "print_field_descending_postal_address";
    private final static String description = ": Print field descending postal address;";
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
     * Function to print organizations by descending postal address
     */
    public static String print_field_descending_postal_address(){
        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0] + "\n";
        }
        Vector<Organization> result = org.stream()
                .sorted(Comparator.comparing(Organization::getAddress).reversed())
                .collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
        Show show = new Show();
        show.setOrg(result);
        return Show.show();
        //Command.showOtherList(result);
    }
    public String execute(){return print_field_descending_postal_address();}
    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
}
