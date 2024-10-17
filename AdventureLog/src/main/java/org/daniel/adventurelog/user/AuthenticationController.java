package org.daniel.adventurelog.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.daniel.adventurelog.jdbc.UserJDBC;
import org.daniel.adventurelog.jdbc.User;
import org.daniel.adventurelog.main.AdventureLog;
import org.daniel.adventurelog.props.InfoDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;

public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    public String currentScene = "LOGIN";

    @FXML
    public StackPane imagePane;

    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    public Label titleLabel;
    @FXML
    public Hyperlink viewHyperlink;
    @FXML
    public void printHello(){
        System.out.println("Hello there");
    }

    public boolean validateInput() {
        if (emailField.getText().isBlank() || passwordField.getText().isBlank()) {
            System.out.println("All the fields are required");
            return false;
        }
        return true;
    }

    @FXML
    public void moveScene(ActionEvent event){
        if(Objects.equals(currentScene, "LOGIN")){
            moveToRegisterScene(event);
        }
        else{
            moveToLoginScene(event);
        }
    }

    public void moveToRegisterScene(ActionEvent event){
        Stage stage = getStage((Node) event.getSource());

        loginButton.setVisible(false);
        titleLabel.setText("Create An Account on Adventure Log");

        viewHyperlink.setText("Already Have An Account");
        registerButton.setVisible(true);

        stage.setTitle(AuthenticationScene.registerTitle);

        currentScene = "REGISTER";
    }


    public void moveToLoginScene(ActionEvent event){
        Stage stage = getStage((Node) event.getSource());

        registerButton.setVisible(false);
        loginButton.setVisible(true);

        titleLabel.setText("Welcome back to Adventure Log");

        viewHyperlink.setText("Don't have An Account");

        stage.setTitle(AuthenticationScene.loginTitle);

        currentScene = "LOGIN";
    }

    public void moveToAdventureLogScene(ActionEvent event, User user){
        Stage stage = getStage((Node) event.getSource());
        AdventureLog adventureLog = new AdventureLog(user);
        stage.setScene(adventureLog);
    }

    public void registerUser(ActionEvent event){
        if(!validateInput()){
            System.out.println("There was a problem with the input");
            InfoDialog infoDialog = new InfoDialog("No Field is to be left blank", "Error");
            infoDialog.showAndWait();
            return ;
        }

        Map<Boolean, String> result= UserJDBC.createUser(emailField.getText(), passwordField.getText());

      if(result.containsKey(true)){
          moveToLoginScene(event);
      }
      else {
          System.out.println("There was an issue registering the account");
          InfoDialog infoDialog = new InfoDialog(result.get(false), "Error");
          infoDialog.showAndWait();

      }
    }

    public void loginUser(ActionEvent e){
        if(!validateInput()){
            InfoDialog infoDialog = new InfoDialog("No Field is to be left blank", "Error");
            infoDialog.showAndWait();
            return ;
        }

        String emailString = emailField.getText();
        String passwordString = passwordField.getText();

        User loggedInUser = UserJDBC.loginUser(emailString, passwordString);
        if(loggedInUser == null){
            InfoDialog infoDialog = new InfoDialog("The email or password is wrong", "Error");
            return ;
        }


        // go to the adventure Log
        moveToAdventureLogScene(e, loggedInUser);
    }

    private Stage getStage(Node node){
        return (Stage) (node.getScene().getWindow());
    }
}
