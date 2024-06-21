import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameGUI {
    public static void createGameGui(RandomNumberEngine randomObject){
        JFrame frame  = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JPanel background = new JPanel();
        // set the panel to a box layout
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // button to reset the random number
        JButton resetButton = new JButton("Reset");

        // Label to display result of game
        JLabel resultLabel = new JLabel("sdfs");

        // label where random number will be shown
        JLabel randomNumberLabel = new JLabel("?");
        randomNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        randomNumberLabel.setFont(new Font("sans-serif", Font.BOLD, 100));
        randomNumberLabel.setForeground(Color.black);
        randomNumberLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);


        // get the texbox where the user will place his inputs
        JTextField textField = new JTextField();
        textField.setColumns(1);
        textField.addActionListener(event-> {
            resultLabel.setText(randomObject.validateRandomNumber(textField.getText()));
            if(resultLabel.getText() == "You are correct"){
                randomNumberLabel.setText(Integer.toString(randomObject.getRandomNumber()));
            }
        });

        // add action listener to the button
        resetButton.addActionListener(event -> {
            randomObject.setRandomNumber();
            textField.setText("");
            randomNumberLabel.setText("?");
            resultLabel.setText("The random number has been reset");
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            resultLabel.setText("");

        });


        background.add(resultLabel);
        background.add(randomNumberLabel);
        background.add(textField);
        background.add(resetButton);
        frame.getContentPane().add(BorderLayout.CENTER, background);
        // frame.setBounds(50, 50, 300, 300);
        // frame.pack();
        frame.setVisible(true);
    }
}
