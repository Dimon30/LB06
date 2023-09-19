package Modules;

import Auxiliary.Message;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
public class GetResponse {

    public static Message getMessage(SocketChannel socket) {
        byte[] data = new byte[303030];
        try{
            socket.read(ByteBuffer.wrap(data));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Message message = (Message) objectInputStream.readObject();
            return message;

        } catch (IOException e) {
            e.printStackTrace();
            return new Message("GetResponse::getMessage::message: 'Server temporary unavailable'");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("GetResponse::getMessage::message: 'Error getting message'");
        }
    }
}
