package Commands;

import Organization.Organization;

import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterByTypeAsc extends Command{
    private static final String name = "filter_greater_than_type";
    private final static String description = ": show organizations with type > than given value;";
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
     * Function print organizations which have type greater than given
     */
    public static String filter_greater_than_type() {
        try {
            if (arg.length == 0) {
                return "Please input type in format: filter_greater_than_type 'type'\n\n";
            }
            String type = arg[0];
            Vector<Organization> result = org.stream().filter(o -> o.equals(type)).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            Show show = new Show();
            show.setOrg(result);
            return Show.show();
        } catch (Exception e){
            return "You input incorrect datas";
        }
    }
    //public String execute(){return filter_greater_than_type();}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
}

