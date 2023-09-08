package Modules;

import Commands.Command;
import Organization.Organization;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Vector;

public class SendResponse {
    public static void sendResponse(Socket socket, String response){
        try{
            PrintWriter printWriter = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()
                    )
            );
            printWriter.println(response);
            printWriter.flush();
            //System.out.println("Message successful send");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public static void sendResponseCommand(Socket socket, String response){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Command temp = new Command();
            temp.setMessage(response);
            objectOutputStream.writeObject(temp);
        }catch (Exception e){
            System.out.println("Command message not sent");
        }
    }

    public static void sendOrganization(Socket socket, Vector<Organization> org, int id) {
        Organization t = null;
        try{
            t = org.stream().filter(o -> o.getId() == id).findFirst().orElseThrow();
        } catch (NoSuchElementException e){
            sendResponse(socket, "Sorry...\nDon't find organization by this id(\n");
        } catch (Exception e){
            sendResponse(socket, "Something is going wrong.\n");
        }
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}