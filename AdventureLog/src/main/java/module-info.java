module org.daniel.adventurelog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires com.zaxxer.hikari;

    opens org.daniel.adventurelog to javafx.fxml;
    opens org.daniel.adventurelog.user to javafx.fxml;
    opens org.daniel.adventurelog.main to javafx.fxml;
    opens org.daniel.adventurelog.main.props to javafx.fxml;

    // exports
    exports org.daniel.adventurelog;
    exports org.daniel.adventurelog.main.props;
    exports org.daniel.adventurelog.jdbc;
//    exports org.daniel.adventurelog.user;
}