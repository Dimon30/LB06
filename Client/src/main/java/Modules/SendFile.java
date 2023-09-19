package Modules;

import Auxiliary.Message;
import java.nio.channels.SocketChannel;

public class SendFile {
    public static void sendFile(String[] args, SocketChannel socket){
        String filename = "src/main/resources/Organizations.xml";
        if (args.length != 0)
            filename = args[0];
        // 1)
        SendRequest.sendMessage(new Message(socket, filename));
        // 2)
        System.out.println(GetResponse.getMessage(socket).getMessage());
    }
}
