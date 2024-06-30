package player;
public class Player {
    private String choice;
    private String playerName;
    
    public Player(String name){
        this.playerName = name;
    }


    public void setChoice(String choice){
        this.choice = choice;
    }

    public String getChoice(){
        return this.choice;
    }

    public String getName(){
        return this.playerName;
    }
}
