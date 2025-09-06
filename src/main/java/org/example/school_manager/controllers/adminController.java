package org.example.school_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.school_manager.models.Database;
import org.example.school_manager.models.User;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class adminController {
    private ResultSet result;
    private PreparedStatement prepare;
    private Connection connection;
    @FXML
    private TextField date_of_birth;

    @FXML
    private DatePicker dateB;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField place_of_birth;

    @FXML
    private TextField school_grade;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }


    @FXML
    void save_students() throws SQLException {
        date = dateB.getValue();
        String sql = "INSERT INTO Student"
                + "(firstname,lastname,dateOfBirth,placeOfBirth,schoolGrade)"
                + "VALUES(?,?,?,?,?)";
        connection = Database.connectDb();

        try {
            Alert alert;
            if (school_grade.getText().isEmpty()
                    || lastname.getText().isEmpty()
                    || firstname.getText().isEmpty()
                    || place_of_birth.getText().isEmpty()
                    || dateB.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplissez tout les champs vides svp");
                alert.showAndWait();
            }
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, firstname.getText());
            prepare.setString(2, lastname.getText());
            prepare.setDate(3, Date.valueOf(dateB.getValue()));
            prepare.setString(4, place_of_birth.getText());
            prepare.setString(5, school_grade.getText());

            prepare.executeUpdate();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Enregistré avec succes!");
            alert.showAndWait();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}







