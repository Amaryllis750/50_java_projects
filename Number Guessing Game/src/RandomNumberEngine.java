import java.util.Random;

public class RandomNumberEngine {
    private int randomNumber;

    public void setRandomNumber(){
        Random random = new Random();
        this.randomNumber = random.nextInt(100);
    }

    public int getRandomNumber(){
        return this.randomNumber;
    }

    public String validateRandomNumber(String num){
        String string = "";
        try{
            int number = Integer.parseInt(num);

            if(number == this.randomNumber){
                string =  "You are correct";
            }
            else{
                if(number < this.randomNumber){
                    string = "Your number is lower";
                }
                else{
                    string = "Your number is higher";
                }
            }

        }   
        catch(NumberFormatException e){
            string =  "This is not a valid input";
        }
        return string;
    }
}
