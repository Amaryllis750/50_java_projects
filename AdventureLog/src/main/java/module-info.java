module org.daniel.adventurelog.adventurelog {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.daniel.adventurelog.adventurelog to javafx.fxml;
    exports org.daniel.adventurelog.adventurelog;
}