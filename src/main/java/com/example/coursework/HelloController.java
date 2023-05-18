package com.example.coursework;

import animations.Shake;
import configs.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import userClasses.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    private static String name;
    private static String surname;
    @FXML
    private Button authorizationButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registrationButton;

    @FXML
    void initialize() {

        registrationButton.setOnAction(actionEvent -> {
            registrationButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registrationPage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
        authorizationButton.setOnAction(actionEvent -> {
            String loginText = loginTextField.getText().trim();
            String passwordText = passwordTextField.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else System.out.println("Login or password is empty");
        });

    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        String role = null;
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        ResultSet resultSet = dataBaseHandler.getUser(user);

        int counter = 0;
        while (resultSet.next()) {
            name = resultSet.getString("firstname");
            surname = resultSet.getString("secondname");
            role = resultSet.getString("role");
            counter++;
        }
        if (counter >= 1) {
            if (role.equals("admin")) {
                authorizationButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("adminPage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else {
                authorizationButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("mainPage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        } else {
            Shake userLogin = new Shake(loginTextField);
            Shake userPassword = new Shake(passwordTextField);
            userLogin.playAnimation();
            userPassword.playAnimation();
        }
    }

    public static Label getInfoAboutUser(Label label) {
        label.setText("Welcome back " + getName() + " " + getSurname());
        return label;
    }

    public static String getName() {
        return name;
    }

    public static String getSurname() {
        return surname;
    }
}