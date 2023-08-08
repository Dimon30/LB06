package Modules;


import java.io.*;
import java.net.Socket;

public class SendRequest {
    public static void sendRequest(Socket socket, String request){
        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.println(request);
            printWriter.flush();
        } catch (Exception e){
            System.out.println("Request hadn't sent");
        }
    }
    public static void sendRequest(Socket socket, Object request){
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(request);
            //printWriter.flush();
        } catch (Exception e){
        }
    }

}
