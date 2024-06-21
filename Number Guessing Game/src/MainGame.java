public class MainGame{
    public static void main(String[] args){
        MainGame game = new MainGame();
        game.go();
    }

    public void go(){
        RandomNumberEngine randomEngine = new RandomNumberEngine();
        randomEngine.setRandomNumber();
        System.out.println(randomEngine.getRandomNumber());
        GameGUI.createGameGui(randomEngine);
    }
}