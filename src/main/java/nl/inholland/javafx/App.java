package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    Label userLabel, passwordLabel;
    TextField userInput;
    PasswordField passwordField;
    Button button;

    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(200);
        window.setWidth(320);
        window.setTitle("Login screen");

        userLabel = new Label("Username");
        passwordLabel = new Label("Password");
        userInput = new TextField();
        userInput.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        button = new Button("Log in");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(8);
        gridPane.setVgap(10);

        // name // hor position // ver position
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userInput, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(button, 1, 2);


        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();

        button.setVisible(false);

        StringProperty passwordProperty = passwordField.textProperty();
        passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {

                char[] chars = passwordField.getText().toCharArray();
                boolean validLength = false;
                boolean hasChar = false;
                boolean hasDigit = false;
                boolean hasSpecialChar = false;

                String specialChars = "!@#$%&*()’+,-./:;<=>?[]^_`{|}";

                for(char c : chars){

                    // making 4 criterias for pass
                    if (passwordField.getText().length() >= 8){
                        validLength = true;
                    }
                    if(Character.isDigit(c)){
                        hasDigit = true;
                    }
                    if(Character.isLetter(c)){
                        hasChar = true;
                    }

                    // converting the char into a string
                    String s = String.valueOf(c);
                    if (specialChars.contains(s)){
                        hasSpecialChar = true;
                    }

                    // checking the four criterias for password
                    if (((validLength) && (hasChar) && (hasDigit) && (hasSpecialChar))){
                        button.setVisible(true);
                    }
                    else{
                        button.setVisible(false);
                    }

                }
            }
        });

    }



}
