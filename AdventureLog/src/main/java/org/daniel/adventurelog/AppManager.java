package org.daniel.adventurelog;

import javafx.application.Application;
import javafx.stage.Stage;
import org.daniel.adventurelog.jdbc.User;
import org.daniel.adventurelog.main.AdventureLog;
import org.daniel.adventurelog.user.AuthenticationScene;

public class AppManager extends Application {
    @Override
    public void start(Stage stage){
//        AuthenticationScene authenticationScene = new AuthenticationScene();
//
//        stage.setScene(authenticationScene);
//        stage.setTitle(AuthenticationScene.loginTitle);
//        stage.show();


        User user = new User(1, "danny@gmail.com", "12345");
        AdventureLog adventureLog = new AdventureLog(user);
        stage.setTitle("Adventure Log");
        stage.setScene(adventureLog);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
