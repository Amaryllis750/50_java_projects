package com.texteditor.gui;

import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.*;


public class FontDialog extends JDialog{
    private String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private Integer[] fontSizes  = new Integer[]{8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48};  
    private String[] fontStyles = new String[]{"PLAIN", "BOLD", "ITALIC"};
    private TextEditor owner;
    JComboBox<String> fontComboBox, fontStyleComboBox;
    JComboBox<Integer> fontSizesComboBox;
    private Font currentFont;

    public FontDialog(Frame owner, Font currentFont){
        setTitle("Font Settings");
        this.owner = (TextEditor) owner;
        this.currentFont = currentFont;
        setupGUI();
    }

    private void setupGUI(){
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        JPanel comboBoxPanel = new JPanel();
        fontComboBox = new JComboBox<>(fontNames);
        fontComboBox.setMaximumRowCount(5);
        fontComboBox.setSelectedItem(currentFont.getName());

        fontSizesComboBox = new JComboBox<>(fontSizes);
        fontSizesComboBox.setMaximumRowCount(5);
        fontSizesComboBox.setSelectedItem(currentFont.getSize());

        fontStyleComboBox = new JComboBox<>(fontStyles);
        fontStyleComboBox.setMaximumRowCount(5);
        fontStyleComboBox.setSelectedItem(getFontStyle(currentFont.getStyle()));

        comboBoxPanel.add(fontComboBox);
        comboBoxPanel.add(fontStyleComboBox);
        comboBoxPanel.add(fontSizesComboBox);

        background.add(comboBoxPanel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> returnFont());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(BorderLayout.EAST, okButton);

        getContentPane().add(BorderLayout.CENTER, background);
        getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        setSize(400, 200);
        setVisible(true);
    }

    public void returnFont(){
        String fontName = (String) fontComboBox.getSelectedItem();
        Integer fontSize = (Integer) fontSizesComboBox.getSelectedItem();
        Integer fontStyle = getFontStyle(fontStyleComboBox.getSelectedItem());

        Font font = new Font(fontName, fontStyle, fontSize);

        owner.setTypingAreaFont(font);
        this.dispose();
    }

    private Integer getFontStyle(Object item){
        String style = (String) item;
        if(style.equals("PLAIN")){
            return Font.PLAIN;
        }
        else if (style.equals("BOLD")){
            return Font.BOLD;
        }
        else{
            return Font.ITALIC;
        }
    }

    private String getFontStyle(int fontStyle){
        if(fontStyle == Font.PLAIN){
            return "PLAIN";
        }
        else if (fontStyle == Font.ITALIC){
            return "ITALIC";
        }
        else {
            return "BOLD";
        }
    }

}
