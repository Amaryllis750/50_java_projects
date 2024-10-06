package org.daniel.adventurelog.main.props;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.daniel.adventurelog.main.AdventureLogController;

public class SideBarController {
    private Color backgroundColor;

    @FXML
    private VBox sidebarContainer;

    public void initialize(){
        this.setBackgroundColor(AdventureLogController.themeColor);
    }

    public void setBackgroundColor(Color color){
        this.backgroundColor = color;
        applyBackgroundColor();
    }

    private void applyBackgroundColor(){
        if(sidebarContainer != null && backgroundColor != null){
            BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            sidebarContainer.setBackground(background);
        }
    }
}
