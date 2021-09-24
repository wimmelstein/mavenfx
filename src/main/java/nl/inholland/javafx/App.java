package nl.inholland.javafx;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App extends Application {
    public static boolean
    isValidPassword(String password, Label errMessage)
    {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z,A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        if (!m.matches()) {
            errMessage.setText("Your password needs to contain letter, number, special character and minimum of 8 characters!");
            return false;
        } else {
            errMessage.setText("");
            return true;
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(600);
        window.setWidth(800);

        // Login
        window.setTitle("Login screen");
        GridPane gridPane = new GridPane();
        Label usernameLabel = new Label("Username");
        TextField username = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField password = new PasswordField();
        TextField passwordVisible = new TextField();
        Button loginBtn = new Button("Login");
        Label errorMessage = new Label();
        errorMessage.setTextFill(Color.web("#ff0000"));
        CheckBox passwordVisibilityCheck = new CheckBox("Show Password");


        // styling
        gridPane.add(usernameLabel, 0,0);
        gridPane.add(username, 1,0);

        gridPane.add(passwordLabel, 0,1);
        gridPane.add(password, 1, 1);
        gridPane.add(passwordVisible, 1, 1);

        gridPane.add(loginBtn, 1,3);
        gridPane.add(errorMessage, 1,2);
        gridPane.add(passwordVisibilityCheck, 1, 4);
        loginBtn.setVisible(false);
        passwordVisible.setVisible(false);
        StringProperty passwordFieldProperty = password.textProperty();
        StringProperty passwordVisibleFieldProperty = passwordVisible.textProperty();

        // show/hide password
        passwordVisibilityCheck.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                        if (passwordVisibilityCheck.isSelected()) {
                            passwordVisible.setText(password.getText());
                            passwordVisibilityCheck.setText("Hide password");
                            password.setVisible(false);
                            passwordVisible.setVisible(true);


                        } else {
                            passwordVisibilityCheck.setText("Show password");
                            password.setText(passwordVisible.getText());
                            password.setVisible(true);
                            passwordVisible.setVisible(false);
                        }

                });

        // Validate password
        passwordFieldProperty.addListener((InvalidationListener) e -> {
          if (isValidPassword(passwordFieldProperty.getValue(), errorMessage)) {
             loginBtn.setVisible(true);
          } else {
              loginBtn.setVisible(false);
          }
        });

        passwordVisibleFieldProperty.addListener((InvalidationListener) e -> {
            if (isValidPassword(passwordVisibleFieldProperty.getValue(), errorMessage)) {
                loginBtn.setVisible(true);
            } else {
                loginBtn.setVisible(false);
            }
        });
        //Home

        GridPane gridPane2 = new GridPane();
        Label greeting = new Label();
        StringProperty userNameFieldProperty = username.textProperty();

        Button logoutBtn = new Button("Logout");

        greeting.setFont(Font.font ("Verdana", 30));
        gridPane2.add(greeting, 3, 1);
        gridPane2.add(logoutBtn, 3, 2);



        Scene login = new Scene(gridPane);
        Scene home = new Scene(gridPane2);

        // Login
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (username.getText().isEmpty()) {
                    errorMessage.setText("Please enter you username");
                } else {
                    greeting.setText("Welcome " + username.getText() + "!");
                    errorMessage.setText("");
                    window.setTitle("Home screen");

                    window.setScene(home);
                }
            }
        });

        // Logout
        logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                window.setTitle("Login screen");
                greeting.setText("");
                username.clear();
                password.clear();
                passwordVisible.clear();
                window.setScene(login);
            }
        });

        window.setScene(login);
        window.show();
    }
}
