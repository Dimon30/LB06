package Server;

import Auxiliary.Read_XML;
//import Commands.*;
import Organization.Organization;

import javax.xml.stream.events.Comment;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for work with server app
 */
public class Server {
    public static void main(String[] args) {
        // Message about start work server
        System.out.println("Server is working...");

        try (ServerSocket server = new ServerSocket(30)) {
            while (true) {
                Socket socket = server.accept();
                if (socket == null)
                    continue;

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error...");
        }
        System.out.println("Server is closed");
    }

}
