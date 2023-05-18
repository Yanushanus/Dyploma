package com.example.coursework;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AdminInfoOffer {

    @FXML
    private TextArea textOfferArea;

    @FXML
    void initialize(){
        textOfferArea.setText(AdminPage.getOffer());
    }
}
