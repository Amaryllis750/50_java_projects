import javax.swing.*;

import player.Player;

import java.awt.*;
import java.awt.event.*;

import gui.MyButton;
import gui.PlayerPanel;

public class GameGUI {
    Player computer;
    Player user = new Player("You");
    private PlayerPanel userPanel = new PlayerPanel(user);
    private ImageIcon rockIcon = new ImageIcon("Rock Paper Scissors\\src\\images\\rock-option.png");
    private ImageIcon paperIcon = new ImageIcon("Rock Paper Scissors\\src\\images\\paper-option.png");
    private ImageIcon scissorsIcon = new ImageIcon("Rock Paper Scissors\\src\\images\\scissors-option.png");
    JLabel result;

    
    public void createGUI(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // setup the computer user
        setupComputerPlayer("Computer1");
        PlayerPanel computerPanel = new PlayerPanel(computer);
        
        // the computer and user will be stationed in grids in a grid box
        GridLayout gridBox = new GridLayout(1, 2, 3, 3);
        JPanel playersGrid = new JPanel(gridBox);  
        
        // add the computer and user panels to the grid box
        playersGrid.add(computerPanel);
        playersGrid.add(userPanel);

        // set up the userpanel
        setupUserPanel();


        JPanel resultPanel = new JPanel();
        result = new JLabel("Result");
        resultPanel.add(result);

        background.add(playersGrid);
        background.add(resultPanel);

        frame.getContentPane().add(BorderLayout.CENTER, background);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setupComputerPlayer(String name){
        computer = new Player(name);
        String choice = GameEngine.getAutomatedChoice();
        computer.setChoice(choice);
    }

    public void setupUserPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        MyButton buttonRock = new MyButton(new ImageIcon());
        buttonRock.setIcon(resizeIcon(rockIcon,30, 30));
        buttonRock.addActionListener(event -> {
            user.setChoice("rock");
            getWinner(user, computer);
        });

        MyButton buttonScissors = new MyButton(new ImageIcon());
        buttonScissors.setIcon(resizeIcon(scissorsIcon, 30, 30));
        buttonScissors.addActionListener(event -> {
            user.setChoice("scissors");
            getWinner(user, computer);
        });


        MyButton buttonPaper = new MyButton(new ImageIcon());
        buttonPaper.setIcon(resizeIcon(paperIcon, 30,30));
        buttonPaper.addActionListener(event ->{
            user.setChoice("paper");
            getWinner(user, computer);
        });

        buttonPanel.add(buttonRock);
        buttonPanel.add(buttonScissors);
        buttonPanel.add(buttonPaper);

        userPanel.add(buttonPanel);
    }

    public void getWinner(Player player1, Player player2){
        Player winner = GameEngine.gamePlay(player1, player2); 
        result.setText(winner.getName() + " won");
    }

    private static Icon resizeIcon(ImageIcon icon, int resized_width, int resized_height){
        Image img = icon.getImage();
        Image resized_image = img.getScaledInstance(resized_width, resized_height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resized_image);
    }
}
