package com.livecha.server_objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

public class Server2 {
    public static void main(String[] args) {
        new Server2().start();
    }

    private void start(){
        InetSocketAddress svrAddress = new InetSocketAddress("127.0.0.1", 8080);
        try {
            SocketChannel serverChannel = SocketChannel.open(svrAddress);
            PrintWriter writer = new PrintWriter(Channels.newOutputStream(serverChannel));
            writer.println("dfsfs");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
