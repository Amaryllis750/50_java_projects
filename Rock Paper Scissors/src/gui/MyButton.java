package gui;
import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton{
    public MyButton(String text){
        super(text);
        setBackground(Color.white);
        setForeground(Color.black);
    }
    public MyButton(){
        super();
        setBackground(Color.white);
    }
    public MyButton(Icon icon){
        super(icon);
        setBackground(Color.white);
    }
}
