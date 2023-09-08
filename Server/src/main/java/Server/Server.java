package Server;

import Auxiliary.Read_XML;
//import Commands.*;
import Commands.Command;
import Modules.*;
import Organization.Organization;

import javax.xml.stream.events.Comment;
import java.io.ObjectInputStream;
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
                System.out.println("Client connected.");

                // 1) + 2)
                Vector<Organization> Organization = ProcessInputFile.processInputFile(socket);

                while (true){
                    // 3
                    String message_from_client = ReadRequest.readRequest(socket);
                    if (message_from_client.equals("client_out"))
                        break;
                    if (!message_from_client.equals("command_is_ready"))
                        continue;
                    // 4) get command(request)
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Command inputCommand = (Command)objectInputStream.readObject();
                    //System.out.println(inputCommand);
                    String message_to_client = inputCommand.execute();
                    // 5) send response
                    SendResponse.sendResponseCommand(socket, message_to_client);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error...");
        }
        System.out.println("Server is closed");
    }

}
