package Modules;


import Auxiliary.Message;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SendRequest {

    /**
     * @param message
     * @return
     */
    public static Message sendMessage(Message message) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.close();
            message.getSocket().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
            //System.out.println(message.print());
        } catch (IOException e) {
            return new Message("SendRequest::sendMessage::message: 'Server temporary unavailable'");
        } catch (Exception e){
            return new Message("SendRequest::sendMessage::message: 'Error send Message'");
        }
        return new Message();
    }
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
