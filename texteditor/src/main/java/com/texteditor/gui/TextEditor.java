package com.texteditor.gui;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class TextEditor extends JFrame{
    private JTextPane typingArea;
    private Font defaultFont;
    private UndoManager um;
    private File currentFile = null;
    
    public TextEditor(){
        super("New File");
        defaultFont = new Font("Calibri", Font.PLAIN, 15);
        um = new UndoManager(){
            @Override
            public void undoableEditHappened(UndoableEditEvent e){
                this.addEdit(e.getEdit());
            }
        };
        setupGUI();
    }

    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));


        typingArea = new JTextPane();
        typingArea.getDocument().addUndoableEditListener(um);
        // setup the key bindings for undo and redo
        typingArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                undoAndRedoBindings(e);
            }
        });
        typingArea.setFont(defaultFont);

        JScrollPane scrollPane = new JScrollPane(typingArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        background.add(scrollPane);

        // add the menu to the application
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu formatMenu = new JMenu("Format");

        // these are the menu items in the file menu
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(event -> saveChanges());

        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        saveAsMenuItem.addActionListener(event -> saveFile());

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(event -> openFile());

        JMenuItem fontMenuItem = new JMenuItem("Font");
        fontMenuItem.addActionListener(event -> new FontDialog(this, defaultFont));

        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(openMenuItem);

        formatMenu.add(fontMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(formatMenu);

        setJMenuBar(menuBar);

        getContentPane().add(BorderLayout.CENTER, background);
        setSize(600, 600);
        setVisible(true);
    }

    public void setTypingAreaFont(Font font){
        typingArea.setFont(font);
    }

    private void saveChanges(){
        if(this.currentFile == null){
            saveFile();
        }
        else{
            String currentContent = typingArea.getText();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.currentFile))){
                writer.write(currentContent);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void undoAndRedoBindings(KeyEvent e){
        if(e.isControlDown()){
            if(e.getKeyCode() == KeyEvent.VK_Z){
                um.undo();
            }
            else if(e.getKeyCode() == KeyEvent.VK_Y){
                um.redo();
            }
        }
    }

    private void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        String line;

        if(option == JFileChooser.APPROVE_OPTION){
            File filename = fileChooser.getSelectedFile();
            this.currentFile = filename;

            typingArea.setText("");
            setTitle(currentFile.getName());
            try(BufferedReader br = new BufferedReader(new FileReader(filename))){
                while((line = br.readLine()) != null){
                    typingArea.setText(typingArea.getText() + "\n" + line);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void saveFile(){
        JFileChooser fileChooser = new JFileChooser();
        String textToSave = typingArea.getText();
        fileChooser.showSaveDialog(this);
        File filename = fileChooser.getSelectedFile();
        this.currentFile = filename;
        setTitle(currentFile.getName());
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename))) {    // it is on this line that the file is created
            fileWriter.write(textToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}
