package org.daniel.adventurelog.user;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.daniel.adventurelog.jdbc.MyJDBC;
import org.daniel.adventurelog.jdbc.User;
import org.daniel.adventurelog.props.InfoDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
        Stage stage = (Stage) ((Hyperlink)event.getSource()).getScene().getWindow();

        loginButton.setVisible(false);
        titleLabel.setText("Create An Account on Adventure Log");

        viewHyperlink.setText("Already Have An Account");
        registerButton.setVisible(true);

        stage.setTitle(AuthenticationScene.registerTitle);

        currentScene = "REGISTER";
    }


    public void moveToLoginScene(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();


        registerButton.setVisible(false);
        loginButton.setVisible(true);

        titleLabel.setText("Welcome back to Adventure Log");

        viewHyperlink.setText("Don't have An Account");

        stage.setTitle(AuthenticationScene.loginTitle);

        currentScene = "LOGIN";
    }

    public void registerUser(ActionEvent event){
        if(!validateInput()){
            System.out.println("There was a problem with the input");
            return ;
        }

        User user = new User(emailField.getText(), passwordField.getText());
        Map<Boolean, String> result= MyJDBC.createUser(user);

      if(result.containsKey(true)){
          moveToLoginScene(event);
      }
      else {
          System.out.println("There was an issue registering the account");
          InfoDialog infoDialog = new InfoDialog(result.get(false), "Error");
          infoDialog.showAndWait();

      }
    }
}
