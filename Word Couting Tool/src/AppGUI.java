import java.awt.*;
import javax.swing.*;
import java.util.*;

public class AppGUI {
    private JTextArea displayArea;

    public void createGUI(){
        JFrame frame = new JFrame("Word counting tool");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        displayArea = new JTextArea(10, 20);
        displayArea.setLineWrap(true);
        displayArea.setEnabled(false);
        
        JScrollPane scroller = new JScrollPane(displayArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scroller);

        JButton showButton = new JButton("Show Count");
        showButton.addActionListener(event -> showCount());
        showButton.setBackground(Color.white);

        mainPanel.add(showButton);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    public void showCount(){
        // get the counts from the Counter class
        Map<String, Integer> counts = Counter.parseString();
        int wordCount = counts.get("Words");
        int charCount = counts.get("Special Characters");
        int lineCount = counts.get("Lines");
        int numCount = counts.get("Digits");

        String text = "Words: " + wordCount + "\n";
        text += "Special Characters: " + charCount + "\n";
        text += "Lines: " + lineCount + "\n";
        text += "Digits: " + numCount;

        displayArea.setText(text);
    }
}
