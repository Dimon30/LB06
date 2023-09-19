package Client;

import Auxiliary.Message;
import Commands.*;
import Modules.*;
import Organization.Organization;

import java.io.*;
import java.net.*;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;


public class Client {
    private static final int PORT = 3030;

    public static void main(String[] args){
        try {
            SocketAddress address = new InetSocketAddress(/*InetAddress.getLocalHost()*/"helios.cs.ifmo.ru", PORT);
            SocketChannel socket = SocketChannel.open();
            //socket.configureBlocking(false);
            /*Selector selector = Selector.open();
            socket.register(selector, SelectionKey.OP_CONNECT);*/
            socket.connect(address);
            Message message = new Message(socket);

            // 1) Send filename + 2) get response
           /* SelectionKey key = socket.keyFor(selector);
            key.interestOps(SelectionKey.OP_WRITE);
            selector.wakeup();*/
            SendFile.sendFile(args, socket);
            System.out.println(new Help().execute());


            Scanner scan = new Scanner(System.in);


            while (true) {
                //Reading command from console
                System.out.print("Input command: ");
                if (!scan.hasNext()) {break;}
                String command_arg = scan.nextLine();
                message.setCommandArg(command_arg);
                //TODO Validation command
                if (!message.validate()){
                    System.out.println("Incorrect command: " + command_arg);
                    continue;
                }
                if (message.getCommand().getType().equals("update")){
                    Update u = (Update) message.getCommand();
                    Organization te = u.processingUpdate(socket, new Scanner(System.in));
                    message.setOrganization(te);
                    continue;
                }

                if (message.getCommand().getType().equals("exit")) {new Exit().execute();return;}

                SendRequest.sendMessage(message);

                // 5) get response
                String response_from_server = GetResponse.getMessage(socket).getMessage();
                System.out.println(response_from_server);
            }


        } catch (IOException e) {
            System.out.println("Server temporary unavailable");
        } catch (Exception e) {
            System.out.println("Server temporarily unavailable");
        }
    }


}
