package com.livecha.server_objects;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class DummyClient {
    public void connectToServer(){
        InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 8080);
        try {
            SocketChannel svrChannel = SocketChannel.open(serverAddress);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
