import javax.swing.*;
import java.awt.*;


public class GameGUI {
    public static void createGameGui(RandomNumberEngine randomObject){
        JFrame frame  = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JPanel background = new JPanel();
        // set the panel to a box layout
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        // Label to display result of game
        JLabel resultLabel = new JLabel("sdfs");

        // sign where random number will be shown
        
        JLabel randomNumberLabel = new JLabel("?");
        randomNumberLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        randomNumberLabel.setFont(new Font("sans-serif", Font.BOLD, 100));
        randomNumberLabel.setForeground(Color.black);
        randomNumberLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);


        // get the texbox where the user will get his inputs
        JTextField textField = new JTextField();
        textField.setColumns(1);
        textField.addActionListener(event-> {
            resultLabel.setText(randomObject.validateRandomNumber(textField.getText()));
            if(resultLabel.getText() == "You are correct"){
                randomNumberLabel.setText(Integer.toString(randomObject.getRandomNumber()));
            }
        });


        background.add(resultLabel);
        background.add(randomNumberLabel);
        background.add(textField);
        frame.getContentPane().add(BorderLayout.CENTER, background);
        // frame.setBounds(50, 50, 300, 300);
        // frame.pack();
        frame.setVisible(true);
    }
}
