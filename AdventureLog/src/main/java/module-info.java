module org.daniel.adventurelog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    opens org.daniel.adventurelog to javafx.fxml;
    opens org.daniel.adventurelog.user to javafx.fxml;
    exports org.daniel.adventurelog;
//    exports org.daniel.adventurelog.user;
}