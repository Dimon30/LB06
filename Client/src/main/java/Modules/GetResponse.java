package Modules;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.stream.Collectors;

public class GetResponse {
    public static String getResponse(Socket socket){
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            //System.out.println(reader.readLine());
            //System.out.println(reader.readLine());
            //System.out.println(reader.lines());
            System.out.println(reader.lines().collect(Collectors.joining()));
            return "";

        } catch (RuntimeException e){
            return "Server temporarily unavailable";
        } catch (Exception e) {
            return "Server temporarily unavailable";
        }
    }
}
