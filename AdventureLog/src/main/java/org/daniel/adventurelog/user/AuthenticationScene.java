package org.daniel.adventurelog.user;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationScene extends Scene {
    public final static String loginTitle = "Login to Adventure Log";
    public final static String registerTitle = "Create An Account on Adventure Log";
    private static final Logger log = LoggerFactory.getLogger(AuthenticationScene.class);
    private FXMLLoader fxmlLoader;

    public AuthenticationScene() {
        this(new Group(new Label("Hello there, this is the group")), 900, 600);
    }

    private AuthenticationScene(Parent root, double width, double height){
        super(root, width, height);
        setFxmlLoader();
        try{
            setRoot(fxmlLoader.load());
        }
        catch (IOException e) {
            log.error("e: ", e);
        }
    }

    private void setFxmlLoader(){
        fxmlLoader = new FXMLLoader(getClass().getResource("/org/daniel/adventurelog/authentication-view.fxml"));
    }

}
