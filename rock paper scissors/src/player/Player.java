package player;
import static player.GameOptions.*;

import java.awt.Image;
import java.util.Random;
import javax.swing.*;

public class Player {
    private GameOptions choice;
    private String playerName;
    private Icon choicePic;

    public Player(String name){
        this.playerName = name;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public GameOptions getChoice(){
        return this.choice;
    }

    public void setChoice(){
        GameOptions[] choices = {PAPER, SCISSORS, ROCK};
        Random random = new Random();
        GameOptions choice = choices[random.nextInt(3)];
        this.choice = choice;
        setChoicePic();
    }

    public void setChoice(GameOptions choice){
        this.choice = choice;
        setChoicePic();
    }

    public void setChoicePic(){
        ImageIcon imgIcon;
        if(choice == ROCK){
            imgIcon = new ImageIcon("rock paper scissors\\src\\images\\rock-option.png");
            this.choicePic = resizeImageIcon(imgIcon, 128, 128);
        }
        else if(choice == PAPER){
            imgIcon = new ImageIcon("rock paper scissors\\src\\images\\paper-option.png");
            this.choicePic = resizeImageIcon(imgIcon, 128, 128);
        }
        else if(choice == SCISSORS){
            imgIcon = new ImageIcon("rock paper scissors\\src\\images\\scissors-option.png");
            this.choicePic = resizeImageIcon(imgIcon, 128, 128);
        }
    }

    private static Icon resizeImageIcon(ImageIcon icon, int resized_width, int resized_height){
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resized_width, resized_height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);

    }

    public Icon getPlayerIcon(){
        return this.choicePic;
    }

}
