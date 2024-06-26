import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class AppGUI {
    private JTextArea displayArea;
    private JFrame frame;
    private File loadedFile;
    private int wordCount, lineCount, numCount, charCount;
    private JButton getFromClipboard, getFromFile;

    public void createGUI(){
        frame = new JFrame("Word counting tool");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        displayArea = new JTextArea(10, 20);
        displayArea.setLineWrap(true);
        displayArea.setEnabled(false);
        displayArea.setFont(new Font("Dialog", Font.PLAIN, 28));
        displayArea.setForeground(Color.black);
        
        JScrollPane scroller = new JScrollPane(displayArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scroller);
        
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        // button gets text from the clipboard
        getFromClipboard = new JButton("Load text from clip board");
        getFromClipboard.addActionListener(event -> parseClipboardText());
        getFromClipboard.setBackground(Color.white);

        getFromFile = new JButton("Load text from file");
        getFromFile.setEnabled(false);    // only enable this button when the loaded file is not equals to null
        getFromFile.setBackground(Color.white);

        buttonBox.add(getFromClipboard);
        buttonBox.add(getFromFile);
        mainPanel.add(buttonBox);

        // create the menu bar
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadFileMenuItem = new JMenuItem("Load File");
        loadFileMenuItem.addActionListener(event -> fileLoader());
        fileMenu.add(loadFileMenuItem);
        menu.add(fileMenu);

        frame.setJMenuBar(menu);
        getFromFile.addActionListener(event->parseFileText(loadedFile));
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    public void showCount(){
        String text = "Words: " + wordCount + "\n";
        text += "Special Characters: " + charCount + "\n";
        text += "Lines: " + lineCount + "\n";
        text += "Digits: " + numCount;

        displayArea.setText("");
        displayArea.setText(text);
    }

    private void resetCount(){
        wordCount = 0;
        numCount = 0;
        charCount = 0;
        lineCount = 0;
    }

    public void fileLoader(){
        JFileChooser fileLoad = new JFileChooser();
        fileLoad.showOpenDialog(frame);
        loadedFile = fileLoad.getSelectedFile();
        getFromFile.setEnabled(true);
    }

    public void parseClipboardText(){
        Map<String, Integer> counts = Counter.parseString(Counter.getClipboardString());
        wordCount = counts.get("Words");
        charCount = counts.get("Special Characters");
        lineCount = counts.get("Lines");
        numCount = counts.get("Digits");

        showCount();
        resetCount();
    }

    public void parseFileText(File file){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                Map<String, Integer> result = Counter.parseString(line);
                wordCount += result.get("Words");
                numCount += result.get("Digits");
                charCount += result.get("Special Characters");
                lineCount++;
            }
            reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        showCount();
        resetCount();
    }
}
