package gui;
import javax.swing.JPanel;
import player.Player;
import javax.swing.*;
import java.awt.*;


public class PlayerPanel extends JPanel{
    private JLabel choice;
    private Icon unknown = new ImageIcon("C:\\Users\\Admin\\Documents\\50 java projects\\Rock Paper Scissors\\src\\images\\question.png");


    public PlayerPanel(Player player){
        super();

        JLabel name = new JLabel(player.getName());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        choice = new JLabel();
        choice.setSize(new Dimension(50, 50));
        choice.setIcon(unknown);
        add(name);
        add(choice);

    }

    public void changeIcon(Icon icon){
        this.unknown  = icon;
    }
}
