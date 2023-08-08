package Modules;

import java.io.*;
import java.net.Socket;

public class ReadRequest {
    public static String readRequest(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String message = reader.readLine();
            return message;
        } catch (Exception e) {
            System.out.println("No connection to client");
        }
        return "client_out";
    }
}
