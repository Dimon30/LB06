package Commands;

import Auxiliary.Message;
import Modules.GetResponse;
import Modules.SendRequest;
import Organization.*;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Class for working with command: update
 */
public class Update extends Command{
    private static final String name = "update";
    private final static String description = ": Update value of field for organization from collection;";

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
    public String execute(){
        try{
            Integer id = o.getId();
            org = org.stream().filter(o  -> o.getId() != id).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            org.add(o);
        } catch (Exception e){
            return "Error for updating";
        }
        return "Organization's been successfully updated";
    }

    public Organization processingUpdate(SocketChannel socket, Scanner scan) {
        try {
            int id = parseInt(arg[0]);
            SendRequest.sendMessage(new Message(socket, String.valueOf(id), "update"));
            Organization t = GetResponse.getMessage(socket).getOranization();
            if (t == null) {
                System.out.println("Sorry...\nDon't find organization by this id(");
                return null;
            }
            System.out.println(t.print());

            System.out.println("What do u wanna change: ");
            System.out.println(
                            "0 - nothing\n" +
                            "1 - name\n" +
                            "2 - coordinates\n" +
                            "3 - creation date\n" +
                            "4 - annual turnover\n" +
                            "5 - type\n" +
                            "6 - postal address");
            System.out.print("Input value: ");
            switch (scan.next()) {
                case "0":
                case "nothing":
                case "-":
                    break;
                case "1":
                case "name":
                case "Name": {
                    System.out.print("Input <string> name: ");
                    String data = scan.next();
                    t.setName(data);
                    break;
                }
                case "2":
                case "coordinates":
                case "Coordinates":
                case "coordinate":
                case "Coordinate": {
                    scan.nextLine();
                    System.out.print("Input coordinates 'x <long>, y <double>': ");
                    String data = scan.nextLine();
                    t.setCoordinates(data);
                    break;
                }
                case "3":
                case "date":
                case "Date":
                case "creation date":
                case "creationDate":
                case "creation_date": {
                    scan.nextLine();
                    System.out.print("Input date 'yyyy-mm-dd': ");
                    t.setCreationDate(LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay()
                    );
                    break;
                }
                case "4":
                case "annual turnover":
                case "annualTurnover":
                case "annualTurnOver":
                case "annual_turnover":
                case "annual_Turnover":
                case "annual_TurnOver": {
                    System.out.print("Input <float> annual turnover: ");
                    scan.nextLine();
                    t.setAnnualTurnover(scan.nextLine());
                    break;
                }
                case "5":
                case "type":
                case "Type": {
                    scan.nextLine();
                    System.out.print("Input type of organizations (commercial, public, government, trust, private_limited_company) '<string> type': ");
                    String type = scan.nextLine();
                    for (; ; ) {
                        if (OrganizationType.findTypebyName(type) == null) {
                            System.out.print("Input type of organizations (commercial, public, government, trust, private_limited_company) '<string> type': ");
                            type = scan.next();
                            continue;
                        }
                        break;
                    }
                    t.setType(type);
                    break;
                }
                case "6":
                case "postalAddress":
                case "postal address":
                case "postal_address":
                case "postal addres":
                case "postal_addres":
                case "postal adress":
                case "postal_adress":
                case "postal adres":
                case "postal_adres": {
                    System.out.print("Input postal address '<string> zipcode, <string> street, <int> x, <long> y, <string> town': ");
                    scan.nextLine();
                    t.setPostalAddress(scan.nextLine());
                    break;
                }

                default:
                    System.out.println("Unexpected value");
                    break;
            }
            return t;
        } catch (NumberFormatException e){
            System.out.println(arg[0] + " isn't number");
            return (Organization) null;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Collection wasn't update");
            return null;
        }
    }

    public boolean validate(){
        if (arg.length == 0){
            System.out.println("Please input command in format: update 'id'");
            return false;
        }
        if (arg.length > 1){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        //setOrganization(processingUpdate(new Scanner(System.in)));
        return true;
    }
}
