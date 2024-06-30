import java.util.Random;

import player.Player;

import java.io.*;

public class GameEngine {
    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    private static int NO_OF_PLAYERS = 2;  // sets the number of players in the match
    // this is useful for the number of grids that will be shown

    public static Player gamePlay(Player player1, Player player2){
        Player result = null;
        
        if(player1.getChoice() == player2.getChoice()){
            result = null;
        }
        else if((player1.getChoice() == "rock" && player2.getChoice() == "scissors") ||
                (player1.getChoice() == "scissors" && player2.getChoice() == "paper") ||
                (player1.getChoice() == "paper" && player2.getChoice() == "rock")){
                    result = player1;
                }
        else{
            result = player2;
        }

        return result;
    }

      
    public static String getAutomatedChoice(){
        Random random = new Random();
        String choice = CHOICES[random.nextInt(3)];
        return choice;
    }

    public static void setNumOfPlayers(int num){
        NO_OF_PLAYERS = num;
    }

    public static int getNumOfPlayers(){
        return NO_OF_PLAYERS;
    }


}
