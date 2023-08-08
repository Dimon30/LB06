package Client;

import Modules.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        try(Socket socket = new Socket(InetAddress.getLocalHost(), 30)) {

            // 1) + 2)
            SendFile.sendFile(args, socket);

            Scanner scanner = new Scanner(System.in);

                // 3)
                //SendRequest.sendRequest(socket, "command_is_ready");
                //Command.sendCommand(socket, Help.getName(), null);
                // 4)
                //System.out.println(GetResponse.getResponse(socket));
                //socket.close();


            /*
            while (true) {
                //System.out.println(getResponse(socket));

                //Reading command from console
                System.out.print("Input command: ");

            }
             */


        } catch (Exception e) {
            System.out.println("Server temporarily unavailable");
        }
    }


}
