module com.example.nikitasergeevfinalfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;


    opens com.example.nikitasergeevfinalfx to javafx.fxml, javafx.base;
    opens com.example.nikitasergeevfinalfx.jdbc to javafx.fxml;
    opens db;
    opens images;
    exports com.example.nikitasergeevfinalfx;
    exports com.example.nikitasergeevfinalfx.jdbc;
}