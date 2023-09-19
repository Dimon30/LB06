package Server;

import Auxiliary.Message;
import Auxiliary.Protocol;
import Commands.*;
import Modules.*;
import Organization.Organization;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Class for work with server app
 */
public class Server {
    private static final int PORT = 3030;
    public static void main(String[] args) {
        // Message about start work server
        System.out.println("Server is working...");

        try {
            Selector selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            SocketAddress address = new InetSocketAddress(PORT);
            server.bind(address);
            //server.configureBlocking(false);
            //server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {

                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (var iter = keys.iterator(); iter.hasNext(); ) {
                    SelectionKey key = iter.next(); iter.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            var ssc = (ServerSocketChannel) key.channel();
                            var sc = ssc.accept();
                            sc.configureBlocking(false);
                            sc.register(key.selector(), SelectionKey.OP_READ);
                        }
                        if (key.isReadable()) {
                            var sc = (SocketChannel) key.channel();

                            sc.register(key.selector(), SelectionKey.OP_WRITE);
                        }
                        if (key.isWritable()) {
                            var sc = (SocketChannel) key.channel();
                            
                            sc.register(key.selector(), SelectionKey.OP_WRITE);
                        }
                    }
                }

                SocketChannel socket = server.accept();
                /*if (socket == null)
                    continue;*/
                System.out.println("Client connected.");

                // 1) + 2)
                 Vector<Organization> Organization = ProcessInputFile.processInputFile(socket);
                while (true){

                    //3
                    Message messageGet = ReadRequest.getMessage(socket);
                    //System.out.println(messageGet.print());
                    //System.out.println("Get");
                    if (messageGet.getStatus().equals("update")){
                        Message messageSend = new Message(socket);
                        //messageGet.setOrg(Organization);
                        Organization t = Organization.stream().filter(o -> o.getId() == parseInt(messageGet.getMessage())).collect(Vector<Organization>::new, Vector<Organization>::add, Vector<Organization>::addAll).get(0);
                        messageSend.setOrganizationMessage(t);
                        SendResponse.sendMessage(messageSend);
                        //System.out.println("Send");
                        continue;
                    }

                    System.out.println(messageGet.getCommand());
                    if (messageGet.getCommand() == null){
                        System.out.println("Client is out");
                        Save save = new Save();
                        save.setOrg(Organization);
                        save.save();
                        break;
                    }
                    //System.out.println(messageGet.print());
                    messageGet.setOrg(Organization);

                    //4
                    Message messageSend = new Message(socket, messageGet.getCommand().execute());
                    SendResponse.sendMessage(messageSend);
                    System.out.println("Send");
                }
            }
        } catch (BindException ex){
            System.out.println("Try another port");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error...");
        }
        System.out.println("Server is closed");
    }

    /*private static void doAccept() {
        var ssc = (ServerSocketChannel) key.channel();
        var sc = ssc.accept();
        key.attach(clientData);
        sc.configureBlocking(false);
        sc.register(key.selector(), OP_READ);
    }
    private static void doRead() {
        var sc = (SocketChannel) key.channel();
        var data = (ClientData) key.attachment();
        sc.read(data.buffer);
        sc.register(key.selector(), OP_WRITE);
    }
    private static void doWrite() {
        var sc = (SocketChannel) key.channel();
        var data = (ClientData) key.attachment();
        sc.write(data.buffer);
    }*/

}
