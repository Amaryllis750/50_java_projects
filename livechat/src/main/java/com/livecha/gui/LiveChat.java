package com.livecha.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import com.livecha.db_objects.*;

public class LiveChat extends JFrame{
    private User user;
    private SocketChannel serverChannel;
    private InetSocketAddress serverAddress;
    private JTextField searchBar;

    public LiveChat(User user){
        super("Live Chat");
        this.user = user;
        connectToServer();
        setupGUI();
    }

    private void connectToServer(){
        serverAddress = new InetSocketAddress("127.0.0.1", 8080);
        try {
            serverChannel = SocketChannel.open(serverAddress);
            System.out.println("Connected");
            System.out.println(user.getUserId());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendUserId(){
        PrintWriter idWriter = new PrintWriter(Channels.newWriter(serverChannel, StandardCharsets.UTF_8));
        idWriter.println(user.getUserId());
        idWriter.flush();
    }

    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        
        searchBar = new JTextField(20);
        searchBar.addActionListener(event -> searchNames());


        //adding SEARCH BAR
        background.add(BorderLayout.NORTH, searchBar);

        getContentPane().add(BorderLayout.CENTER, background);
        setSize(400, 500);
        setVisible(true);
    }

    // this function is going to search the database for the searched name and display it in a panel
    private void searchNames(){
        String searchedName = searchBar.getText();
    }
}
