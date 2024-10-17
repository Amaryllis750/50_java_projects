package org.daniel.adventurelog.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.daniel.adventurelog.jdbc.User;

import java.io.IOException;

public class AdventureLog extends Scene {
    private FXMLLoader fxmlLoader;
    public AdventureLog(User user){
        this(new Group(new Label("Welcome to adventure log")), 900, 600);
    }

    private AdventureLog(Parent root, double width, double height){
        super(root, width, height);
        setFXMLLoader();
        try{
            setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        getRoot().setStyle("-fx-background-color: " + AdventureLogController.toHex(AdventureLogController.themeColor));
    }

    private void setFXMLLoader(){
        fxmlLoader = new FXMLLoader(getClass().getResource("/org/daniel/adventurelog/adventure-log-view.fxml"));
    }
}
