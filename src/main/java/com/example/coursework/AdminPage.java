package com.example.coursework;

import configs.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPage {

    private static String offerList="";
    @FXML
    private Button acceptButton;

    @FXML
    private Button showButton;


    @FXML
    void initialize() {
        acceptButton.setOnAction(action -> {
            DataBaseHandler dataBaseHandler = new DataBaseHandler();

            try {
                dataBaseHandler.updateOffer();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        showButton.setOnAction(action->
        {
            try {
                offerList=getOfferList();
                offerList+="\nUser count: "+getUserCount();
                offerList+="\nAll the sum: "+ getSum();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            showButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminInfoOffer.fxml"));
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


    }
    private String getOfferList()throws SQLException{
        String list="";
        DataBaseHandler dataBaseHandler=new DataBaseHandler();
        ResultSet resultSet=dataBaseHandler.getOfferList();
        while (resultSet.next()){
            list+="Name: "+resultSet.getString("name")+"; Surname: "+resultSet.getString("surname")+"; List of clubs: "+
                    resultSet.getString("club_list")+"; Sum: "+resultSet.getString("offer_sum")+"; Status: "+
                    resultSet.getString("status")+"\n";
        }
        return list;
    }
    private String getUserCount()throws SQLException{
        int count=0;
        String str="";
        DataBaseHandler dataBaseHandler=new DataBaseHandler();
        ResultSet resultSet=dataBaseHandler.getUserCount();
        while (resultSet.next()){
            count++;
        }
        str=""+count;
        return str;
    }
    private String getSum()throws SQLException{
        int count=0;
        String str="";
        DataBaseHandler dataBaseHandler=new DataBaseHandler();
        ResultSet resultSet=dataBaseHandler.getAllSum();
        while (resultSet.next())count+= resultSet.getInt("offer_sum");
        count-=1;
        str=""+count;
        return str;
    }
    public static String getOffer(){
        return offerList;
    }
}
