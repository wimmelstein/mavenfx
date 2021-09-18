package nl.inholland.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(600);
        stage.setWidth(800);
        stage.setTitle("Inholland JavaFX Starter Project");

        BorderPane pane = new BorderPane();
        Label hello = new Label("Hello World");
        pane.setCenter(hello);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
