import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class Counter {

    public static String getClipboardString(){
        String text;
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable trans = c.getContents(null);
        try{
            if(trans!=null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)){
                text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                return text.trim();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static Map<String, Integer> parseString(String s){   
        Map<String, Integer> counts = new HashMap<>();

        // number of lines 
        int lineCount = s.lines().collect(Collectors.toList()).size();

        // the word and number count
        int[] resultCount = countWordAndNumbers(s);
        int wordCount = resultCount[0];
        int numCount = resultCount[1];

        // get the count for special characters
        int charCount = countSpecialCharacters(s);

        counts.put("Words", wordCount);
        counts.put("Special Characters", charCount);
        counts.put("Lines", lineCount);
        counts.put("Digits", numCount);
        
        return counts;
    }

    private static int[] countWordAndNumbers(String s){
        int wordCount = 0;
        int numCount = 0;
        String[] wordsArray = s.split("\\W+");
        List<String> wordsList = new ArrayList<>(Arrays.asList(wordsArray));
        wordsList = wordsList.stream().filter(value -> value!="").collect(Collectors.toList());     // to remove empty strings from the list
        System.out.println(wordsList);
        for(String word : wordsList){
            try{
                Integer.parseInt(word);
                numCount++;
            }
            catch(NumberFormatException e){
                wordCount++;
            }
        }

        return new int[]{wordCount, numCount};
    }

    private static int countSpecialCharacters(String s){
        List<Character> specialCharacters = new ArrayList<>();

        // define a pattern for searching for regex
        Pattern pattern = Pattern.compile("[^\\w0-9\\s]");
        Matcher matcher = pattern.matcher(s);

        while(matcher.find()){
            specialCharacters.add(matcher.group().charAt(0));
        }
        return specialCharacters.size();
    }
}
