package org.example.school_manager.models;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.school_manager.Interfaces.UserInterface;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class User implements UserInterface {
    protected int id;
    protected String email;
    protected String password;
    private ResultSet result;
    private static PreparedStatement prepare;
    private static Connection connection;
    @FXML
    private TextField date_of_birth;

    @FXML
    private static DatePicker dateB;

    @FXML
    private static TextField firstname;

    @FXML
    private static TextField lastname;

    @FXML
    private static TextField place_of_birth;

    @FXML
    private static TextField school_grade;
    private static LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void  createStudent() {
        date=dateB.getValue();
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
                    || dateB.getValue()== null){
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
            prepare.setString(5,school_grade.getText());

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

