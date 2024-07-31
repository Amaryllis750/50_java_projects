package com.livecha.server_objects;

import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.concurrent.*;
import java.util.*;
import static java.nio.charset.StandardCharsets.*;

public class Server {
    private HashMap<Integer, ClientHandler> clients;

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        clients = new HashMap<>();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8080));

            while (serverChannel.isOpen()) {
                SocketChannel clientChannel = serverChannel.accept();
                System.out.println("The client " + clientChannel.getLocalAddress() + " has connected");
                ClientHandler clientHandler = new ClientHandler(clientChannel);
                threadPool.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        private SocketChannel clientChannel;
        private BufferedReader idReader;

        public ClientHandler(SocketChannel clientChannel) {
            this.clientChannel = clientChannel;
            idReader = new BufferedReader(Channels.newReader(this.clientChannel, UTF_8));
        }

        public void run() {
            getUserId();
        }

        private void getUserId() {
            String userIdString;
            try {
                while((userIdString = idReader.readLine())!= null){
                    int userId = Integer.parseInt(userIdString);
                    clients.put(userId, this);
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
