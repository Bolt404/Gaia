package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.camperType;

import java.net.URL;
import java.util.ResourceBundle;

import static org.apollo.template.Service.ListenerUtill.attatchIntegerValidation;

public class CreateAutocamperController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attatchIntegerValidation(tfKmCount, tfWeight, tfLength, tfWeight, tfHeight, tfNoBeds, tfNoSeatBelts, tfNoToilets);

    }

    @FXML
    private TextField tfRegistrationNo, tfChassisNo, tfBrand, tfKmCount, tfWeight, tfLength, tfWidth, tfHeight,
            tfNoBeds, tfNoSeatBelts, tfNoToilets, tfNoRentals, tfPurchaseDate, tfCamperType,
            tfInSesonPrice, tfOutSesonPrice;
    @FXML
    private ChoiceBox<camperType> cbCamperType;
    @FXML
    private TextArea taComment;

    @FXML
    protected void onSaveClick(){

        final Alert ALERT_SUCESS = new Alert(MainController.getInstance(), 3, AlertType.SUCCESS, "auto camper was successfully created!");
        final Alert ALERT_ERROR = new Alert(MainController.getInstance(), 3, AlertType.ERROR, "The auto camper could not be created");


    };

    @FXML
    protected void onCancelClick(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    };

}
