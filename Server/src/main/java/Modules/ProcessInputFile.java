package Modules;

import Auxiliary.Protocol;
import Auxiliary.Read_XML;
import Organization.Organization;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Vector;

public class ProcessInputFile {
    public static Vector<Organization> processInputFile(SocketChannel socket){
        // 1)
        // Create collection of organizations
        String filename = ReadRequest.getMessage(socket).execute();
        //System.out.println(filename);
        // 2)
        return Read_XML.CreateVector(socket, filename);
    }

}
