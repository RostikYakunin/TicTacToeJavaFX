module com.example.tictactoefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.tictactoefx to javafx.fxml;
    exports com.example.tictactoefx;
    exports com.example.tictactoefx.controller;
    exports com.example.tictactoefx.user;
    exports com.example.tictactoefx.checking_algoritms;
    opens com.example.tictactoefx.controller to javafx.fxml;
}