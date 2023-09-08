package Client;

import Commands.Command;
import Commands.Help;
import Commands.Show;
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

            // 1) Send filename + 2) get response
            SendFile.sendFile(args, socket);
            System.out.println("Filename was sent");
            System.out.println(Help.help());

            Scanner scan = new Scanner(System.in);


            while (true) {
                //Reading command from console
                System.out.print("Input command: ");
                if (!scan.hasNext()) {break;}
                String command_arg = scan.nextLine();
                //TODO Validation command

                // 3) send client status
                SendRequest.sendRequest(socket, "command_is_ready");
                // 4) send command
               /* ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                Help help = new Help();
                objectOutputStream.writeObject(help);
                */
                Command.sendCommand(socket, command_arg, null);

                // 5) get response
                String response_from_server = GetResponse.getResponseCommand(socket);
                System.out.println(response_from_server);
            }


        } catch (Exception e) {
            System.out.println("Server temporarily unavailable");
        }
    }


}
