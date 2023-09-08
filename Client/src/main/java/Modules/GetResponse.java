package Modules;

import Commands.Command;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class GetResponse {
    public static String getResponse(Socket socket){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            reader.readLine();
            return "hui";

        } catch (RuntimeException e){
            return "Server temporarily unavailable";
        } catch (Exception e) {
            return "Server temporarily unavailable";
        }
    }

    public static String getResponseCommand(Socket socket){
        try{
            ObjectInputStream objectOutputStream = new ObjectInputStream(socket.getInputStream());
            Command temp = (Command)objectOutputStream.readObject();
            return temp.getMessage();
        }catch (Exception e){
            return "Command message not got";
        }
    }
}
