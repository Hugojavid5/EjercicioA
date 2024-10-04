module org.hugo.ejercicioa {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.hugo.ejercicioa to javafx.fxml;
    exports org.hugo.ejercicioa;
}