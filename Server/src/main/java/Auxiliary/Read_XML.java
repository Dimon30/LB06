package Auxiliary;

import Modules.SendResponse;
import Organization.*;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class for read xml file
 */
public class Read_XML {
    /**
     * Function which read xml file and create collection of organizations
     * @param filename name of file where is description of organizations
     * @return  collection of organizations
     */
    public static Vector<Organization> CreateVector(Socket socket, String filename) {
        File file = new File(filename);
        StringBuilder str = new StringBuilder();
        try (Scanner reader = new Scanner(new FileReader(file.getAbsolutePath()))) {
            while (reader.hasNext()) {
                str.append(reader.next());
            }
        }
        catch (Exception e){
            SendResponse.sendResponse(socket, "Not found input file\nI created new blank collection\n");
        }
        Vector<Organization> org = new Vector<>();
        ArrayList<Map> arr = new ArrayList<Map>();
        int i = 0;
        String[] lines;
        int tId = (int)(Math.random() * 999999);
        String tName = null;
        Coordinates tCoordinates; //Поле не может быть null
        long xC = 0;
        double yC = 0;
        LocalDateTime tCreationDate = LocalDateTime.now();
        Float tAnnualTurnover = null;
        OrganizationType tType = null;
        Address tPostalAddress;
        String zipCode = null;
        String street = null;
        int xL = 0;
        long yL = 0;
        String town = null;
        String lastUpdate = null;
        try {
            lines = str.toString().split("><");
            for (String el : lines) {
                if (el.equalsIgnoreCase("organization")) {
                    arr.add(new HashMap<String, String>());
                    continue;
                }
                if (el.equalsIgnoreCase("/organization")) {
                    i += 1;
                    continue;
                }
                if (arr.size() > i) {
                    if (el.equalsIgnoreCase("coordinates"))
                        continue;
                    if (el.equalsIgnoreCase("/coordinates"))
                        continue;
                    if (el.equalsIgnoreCase("postaladdress"))
                        continue;
                    if (el.equalsIgnoreCase("/postaladdress"))
                        continue;
                    if (el.equalsIgnoreCase("location"))
                        continue;
                    if (el.equalsIgnoreCase("/location"))
                        continue;
                    arr.get(i).put(el.substring(0, el.indexOf('>')), el.substring(el.indexOf('>') + 1, el.indexOf('<')));
                }
            }

            for (Map<String, String> m : arr) {
                for (String key : m.keySet()) {
                    final String value = m.get(key);

                    switch (key) {
                        case "id":
                            tId = Integer.parseInt(value);
                            break;
                        case "name":
                            tName = value;
                            break;
                        case "xc" :
                            xC = Long.parseLong(value);
                            break;
                        case "yc":
                            yC = Double.parseDouble(value);
                            break;
                        case "date":
                            tCreationDate = LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
                            break;
                        case "annualTurnover":
                            tAnnualTurnover = Float.parseFloat(value);
                            break;
                        case "type":
                            tType = OrganizationType.valueOf(value);
                            break;
                        case "zipCode":
                            zipCode = value;
                            break;
                        case "street":
                            street = value;
                            break;
                        case "xl":
                            xL = Integer.parseInt(value);
                            break;
                        case "yl":
                            yL = Long.parseLong(value);
                            break;
                        case "town":
                            town = value;
                            break;
                        case "lastUpdate":
                            lastUpdate = value;
                            break;
                        default:
                            break;
                    }
                }
                Organization t = new Organization(tName, xC, yC, tCreationDate, tAnnualTurnover, tType, new Address(zipCode, street, new Location(xL, yL, town)));
                t.setId(tId);
                t.setLastUpdate(lastUpdate);
                org.addElement(t);
            }
        }
        catch(Exception e){
            SendResponse.sendResponse(socket, "Not download xml file");
        }
        finally {SendResponse.sendResponse(socket, "Xml file successfully downloaded");
            org.stream().sorted(Comparator.comparing(Organization::getName)); return org;}
    }
}
