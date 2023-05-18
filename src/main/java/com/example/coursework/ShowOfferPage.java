package com.example.coursework;

import configs.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowOfferPage {

    private String getText="";
    @FXML
    private Button exitButton;

    @FXML
    private TextArea infoArea;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        getOffers(HelloController.getName(),HelloController.getSurname());
        infoArea.setText(getText);
        exitButton.setOnAction(action->
        {
            exitButton.getScene().getWindow().hide();
        });
    }

    private void getOffers(String name,String surname) throws SQLException, ClassNotFoundException {
        DataBaseHandler dataBaseHandler=new DataBaseHandler();
        ResultSet resultSet=dataBaseHandler.getOffer(name, surname);
        while (resultSet.next()){
            getText+="Name: "+resultSet.getString("name")+"; Surname: "+resultSet.getString("surname")+"; List of clubs: "+
                    resultSet.getString("club_list")+"; Sum: "+resultSet.getString("offer_sum")+"; Status: "+
                    resultSet.getString("status")+"\n";
        }
    }
}
