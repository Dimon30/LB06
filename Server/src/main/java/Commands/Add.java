package Commands;

import Organization.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * Class for actions with command: add
 */
public class Add extends Command{
    private final static String name = "add";
    private final static String description = ": Add new organization to collection;";
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
     * Function add new organizations to collection
     */
    public static String add(){
        if (arg.length > 0){
            return "I don't understand u\n What does it mean: " + getName() + " " + arg[0];
        }
        try {
            org = Stream
                    .concat(org.stream(), Stream.of(o))
                    .collect(Vector<Organization>::new, Vector<Organization>::add, Vector<Organization>::addAll);
            org = org.stream()
                    .sorted(Comparator.comparing(Organization::getName))
                    .collect(Vector<Organization>::new, Vector<Organization>::add, Vector<Organization>::addAll);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Oops... Something going wrong. Probably you input incorrect value.");
        }
        return "";
    }

    public static Organization clientAdd(Scanner scan) {
        System.out.print("Input name organization: ");
        String name = scan.next();

        System.out.print("Input coordinate 'x' organization: ");
        Long xC = null;
        for (;;) {
            if (scan.hasNextLong()) {
                xC = scan.nextLong();
                break;
            } else {
                System.out.print("Please input number in 'long' format: ");
                scan.next();
            }
        }

        System.out.print("Input coordinate 'y' organization: ");
        Double yC = null;
        for (;;) {
            if (scan.hasNextDouble()) {
                yC = scan.nextDouble();
                break;
            } else {
                System.out.print("Please input number in 'double' format: ");
                scan.next();
            }
        }

        System.out.print("Input annual turnover organization: ");
        Float annualTurnover = null;
        for (;;) {
            if (scan.hasNextFloat()) {
                annualTurnover = scan.nextFloat();
                break;
            } else {
                System.out.print("Please input number in 'float' format: ");
                scan.next();
            }
        }

        System.out.print("Choose type of organization (commercial, public, government, trust, private_limited_company): ");
        String tp = scan.next();
        OrganizationType type;
        for (;;) {
            type = OrganizationType.findTypebyName(tp);
            if (type == null) {
                System.out.print("Please chose type of organization (commercial, public, government, trust, private_limited_company): ");
                tp = scan.next();
                continue;
            }
            break;
        }

        System.out.print("Input zipcode organization: ");

        String zipCode = null;
        if (scan.hasNextLine())
            zipCode = scan.next();
        if (zipCode.equals(""))
            zipCode = null;

        System.out.print("Input street organization: ");
        String street = scan.next();

        System.out.print("Input name of town organization: ");
        String town = scan.next();

        System.out.print("Input coordinate -x- town: ");
        Integer xL = null;
        for (;;) {
            if (scan.hasNextInt()) {
                xL = scan.nextInt();
                break;
            } else {
                System.out.print("Please input number in 'integer' format: ");
                scan.next();
            }
        }

        System.out.print("Input coordinate -y- town: ");
        Long yL = null;
        for (;;) {
            if (scan.hasNextLong()) {
                yL = scan.nextLong();
                break;
            } else {
                System.out.print("Please input number in 'long' format: ");
                scan.next();
            }
        }

        return new Organization(name, xC, yC, LocalDateTime.now(), annualTurnover, type, new Address(zipCode, street, new Location(xL, yL, town)));
    }

    //public String execute(){return add();}
    public Vector<Organization> getOrg(){return org;}
    public void setArg(String[] arg){Add.arg = arg;}
    public void setOrg(Vector<Organization> organizations) {org = organizations;}
    public void setOrganization(Organization or){o = or;}
}
