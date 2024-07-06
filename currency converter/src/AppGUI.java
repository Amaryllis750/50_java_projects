import javax.imageio.ImageIO;
import javax.swing.*;

import currencyapi.CurrencyAPI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.*;

import gui.StretchIcon;


public class AppGUI {
    private JPanel inputContainer;
    private JFrame frame;
    private JTextField inputAmountField;
    private JComboBox<String> originalCurrField;
    private JComboBox<String> newCurrField;
    private JTextField resultField;
    private java.util.List<String> currencies;
    private Color bg = new Color(192, 134, 103);


    public void setupGUI(){
        // setup the call to the API
        currencies = CurrencyAPI.getAvailbleCurrencies();

        // set up the GUI frame and background panel
        frame = new JFrame("Currency Converter");
        frame.setBackground(bg);
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBackground(bg);
        

        // create the title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Dialog", Font.BOLD, 20));
        title.setForeground(Color.white);
        ImageIcon refresh = new ImageIcon("currency converter\\src\\images\\refresh.png");
        refresh = resizeImage(refresh, 30, 30);
        JLabel refreshIcon = new JLabel(refresh);
        titlePanel.add(BorderLayout.WEST, title);
        titlePanel.add(BorderLayout.EAST, refreshIcon);
        titlePanel.setBounds(0, 0, 500, 45);
        titlePanel.setBackground(bg);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        titlePanel.setPreferredSize(new Dimension(500, 50));
        
        // BODY PANEL
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        // create the image label
        // read the image
        ImageIcon img = setupImage(new File("currency converter\\src\\images\\elena.jpg"));
        JLabel imgLabel = new JLabel(img);
        imgLabel.setBackground(bg);
        imgLabel.setPreferredSize(new Dimension(480, 150));
        
        bodyPanel.add(BorderLayout.NORTH, imgLabel);

        // set up the container where the inputs will be stored
        inputContainer = new JPanel();
        inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.Y_AXIS));
        setupInputContainer();
        bodyPanel.add(BorderLayout.CENTER, inputContainer);



        background.add(titlePanel);
        background.add(bodyPanel);
        frame.getContentPane().add(BorderLayout.CENTER, background);
        // frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void setupInputContainer(){
        inputContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // this is where the input amount will be placed
        inputAmountField = new JTextField(20);
        inputAmountField.setFont(new Font("Dialog", Font.PLAIN, 28));

        JPanel currencySelectionPanel = new JPanel();
        currencySelectionPanel.setBackground(bg);
        currencySelectionPanel.setLayout(new GridLayout(1, 2, 3, 3));

        // get the available currencies from the API
        String[] currenciesArray = currencies.stream().toArray(String[]::new);

        // this is the combo box for original currencies
        originalCurrField = new JComboBox<>(currenciesArray);
        // this is the combo box for new currencies
        newCurrField = new JComboBox<>(currenciesArray);        


        // this is where the final resultField of the conversion will be shown
        resultField = new JTextField(20);
        resultField.setEditable(false);
        resultField.setFont(new Font("Consolas", Font.PLAIN, 20));

        // action listener for the input amount field
        inputAmountField.addActionListener(event -> convert());

        // add GUI elements to inputContainer panel....
        inputContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        inputContainer.add(inputAmountField);
        inputContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        currencySelectionPanel.add(originalCurrField);
        currencySelectionPanel.add(newCurrField);

        inputContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        inputContainer.add(currencySelectionPanel);
        inputContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        inputContainer.add(resultField);
        inputContainer.setBackground(bg);
    }

    public ImageIcon setupImage(File file){
        ImageIcon imgIcon = new ImageIcon("currency converter\\src\\images\\elena.jpg");
        imgIcon = resizeImage(imgIcon, 150, (imgIcon.getIconWidth()/8));
        return imgIcon;
    }

    private ImageIcon resizeImage(ImageIcon image, int nh, int nw){
        BufferedImage bi = new BufferedImage(nw, nh, BufferedImage.TRANSLUCENT);
        Graphics2D gd = bi.createGraphics();
        gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        gd.drawImage(image.getImage(), 0, 0, nw, nh, null);
        gd.dispose();
        return new ImageIcon(bi);
    }

    private double parseInput(String str){
        double d = 0.00;
        try{
            d = Double.parseDouble(str);
            return d;
        }
        catch(NumberFormatException e){
            System.out.println("The input was not a valid number");
            return d;
        }
    }
    
    private void convert(){
        double inputCurrencyAmount = parseInput(inputAmountField.getText());
        String inputCurrency = (String) originalCurrField.getSelectedItem();
        String outputCurrency =(String) newCurrField.getSelectedItem();

        try{
            double outputCurrencyAmount = CurrencyAPI.convert(inputCurrency, outputCurrency,inputCurrencyAmount);
            resultField.setText(Double.toString(outputCurrencyAmount));
        }
        catch(URISyntaxException e){
            e.printStackTrace();
        }
    }
}
