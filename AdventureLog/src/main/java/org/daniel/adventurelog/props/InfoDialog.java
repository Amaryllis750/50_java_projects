package org.daniel.adventurelog.props;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class InfoDialog extends Dialog<String> {
    public InfoDialog(String message, String title){
        super();
        ButtonType buttonType = new ButtonType("OK", ButtonType.OK.getButtonData());
        getDialogPane().getButtonTypes().add(buttonType);
        getDialogPane().setStyle("-fx-background-color:white;");
        setContentText(message);
        setTitle(title);
    }
}
