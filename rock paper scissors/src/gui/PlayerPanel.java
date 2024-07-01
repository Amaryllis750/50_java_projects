package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import player.Player;

public class PlayerPanel extends JPanel{
    ImageIcon questionMark = new ImageIcon("rock paper scissors\\src\\images\\question.png");
    JLabel unknownOption;
    public PlayerPanel(Player player){
        super();
        
        // setup the player name font style
        JLabel playerName = new JLabel(player.getPlayerName());
        playerName.setAlignmentX(CENTER_ALIGNMENT);
        playerName.setFont(new Font("Dialog", Font.PLAIN, 24));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        unknownOption = new JLabel(questionMark);
        unknownOption.setAlignmentX(CENTER_ALIGNMENT);
        add(playerName);
        add(unknownOption);
    }

    public void setLabelIcon(Icon icon){
        unknownOption.setIcon(icon);
    }
}
