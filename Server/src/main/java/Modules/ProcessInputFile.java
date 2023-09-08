package Modules;

import Auxiliary.Read_XML;
import Organization.Organization;

import java.net.Socket;
import java.util.Vector;

public class ProcessInputFile {
    public static Vector<Organization> processInputFile(Socket socket){
        // 1)
        // Create collection of organizations
        String filename = ReadRequest.readRequest(socket);
        //System.out.println(filename);
        // 2)
        return Read_XML.CreateVector(socket, filename);
    }

}
