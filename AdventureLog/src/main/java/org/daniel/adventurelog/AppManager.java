package org.daniel.adventurelog;

import javafx.application.Application;
import javafx.stage.Stage;
import org.daniel.adventurelog.user.AuthenticationScene;

public class AppManager extends Application {
    @Override
    public void start(Stage stage){
        AuthenticationScene authenticationScene = new AuthenticationScene();

        stage.setScene(authenticationScene);
        stage.setTitle(AuthenticationScene.loginTitle);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
