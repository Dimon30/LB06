package Modules;

import java.net.Socket;

public class SendFile {
    public static void sendFile(String[] args, Socket socket){
        String filename = "src/Organizations.xml";
        if (args.length != 0)
            filename = args[0];
        // 1)
        SendRequest.sendRequest(socket, filename);
        // 2)
        System.out.println(GetResponse.getResponse(socket));
    }
}
