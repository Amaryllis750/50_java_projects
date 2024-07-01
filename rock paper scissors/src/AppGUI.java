import javax.swing.*;
import javax.swing.border.Border;
import static player.GameOptions.*;
import player.Player;

import java.awt.*;
import java.awt.event.*;
import gui.PlayerPanel;
import player.GameOptions;


public class AppGUI {
    private Player computer = new Player("Computer");
    private Player user = new Player("You");
    PlayerPanel computerPanel;
    PlayerPanel userPanel;
    JLabel result;

    // style the button icons
    private Icon rockIcon = resizeImageIcon(new ImageIcon("rock paper scissors\\src\\images\\rock-option.png"),
                                         40, 40);
    private Icon paperIcon = resizeImageIcon(new ImageIcon("rock paper scissors\\src\\images\\paper-option.png"),
                        40, 40);    
    private Icon scissorsIcon = resizeImageIcon(new ImageIcon("rock paper scissors\\src\\images\\scissors-option.png"),
                40, 40);

    public void setupGUI(){
        JFrame frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        result = new JLabel("");
        result.setFont(new Font("Dialog", Font.BOLD, 30));
        result.setHorizontalAlignment(JLabel.CENTER);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(0, 2));
        computerPanel = new PlayerPanel(computer);
        userPanel = new PlayerPanel(user);

        JPanel buttonPanel = setupUserButtonPanel();
        userPanel.add(buttonPanel);
        
        
        playerPanel.add(computerPanel);
        playerPanel.add(userPanel);


        background.add(playerPanel, BorderLayout.CENTER);
        background.add(result, BorderLayout.PAGE_END);
        frame.getContentPane().add(BorderLayout.CENTER, background);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public JPanel setupUserButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        

        JButton rockButton = new JButton(rockIcon);
        rockButton.addActionListener(event -> startGame(ROCK));

        JButton paperButton = new JButton(paperIcon);
        paperButton.addActionListener(event -> startGame(PAPER));

        JButton scissorsButton = new JButton(scissorsIcon);
        scissorsButton.addActionListener(event -> startGame(SCISSORS));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(rockButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(paperButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(scissorsButton);

        return buttonPanel;
    }

    public void startGame(GameOptions userChoice){
        // set the user's choice to the choice given above
        user.setChoice(userChoice);
        // set the computer's random choice
        computer.setChoice();

        userPanel.setLabelIcon(user.getPlayerIcon());
        computerPanel.setLabelIcon(computer.getPlayerIcon());

        Player winner = GameEngine.gamePlay(user, computer);
        if(winner != null){
            if(winner == user){
                result.setText(user.getPlayerName() + " are the winner");
            }
            else{
                result.setText(computer.getPlayerName() + " is the winner");
            }
        }
        else{
            result.setText("It's a TIE");
        }
    }

    private static Icon resizeImageIcon(ImageIcon icon, int resized_width, int resized_height){
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resized_width, resized_height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);

    }
}
