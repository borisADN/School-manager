package org.example.school_manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.school_manager.HelloApplication;
import org.example.school_manager.Interfaces.LoginInterface;
import org.example.school_manager.models.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginController implements LoginInterface {
    private ResultSet result;
    private PreparedStatement prepare;
    private Connection connection;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;


    @FXML
    void login() {
        String sql = "SELECT * FROM  User WHERE email=? AND password =?";
       connection=Database.connectDb();
        try {
            System.out.println("connectee");
            Alert alert;


            prepare = connection.prepareStatement(sql);
            prepare.setString(1, email.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                email.getScene().getWindow().hide();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Connection etablie");
                alert.showAndWait();

                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("AdminDashboard.fxml")));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Faux identifiant/Mot de passe");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}