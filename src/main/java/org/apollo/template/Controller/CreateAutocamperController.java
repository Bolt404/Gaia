package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.CamperType;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.Service.DateTimeUtill;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DAOImplCamperType;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static org.apollo.template.Service.JavaFxControlerToImageViewBind.*;
import static org.apollo.template.Service.ListenerUtill.attatchIntegerValidation;

/**
 * Controller class for creating Autocampers.
 */
public class CreateAutocamperController implements Initializable {

    // initializing text fields.
    @FXML
    private TextField tfRegistrationNo, tfChassisNo, tfBrand, tfKmCount, tfWeight, tfLength, tfWidth, tfHeight,
            tfNoBeds, tfNoSeatBelts, tfNoToilets, tfNoRentals, tfInSesonPrice, tfOutSesonPrice;

    // initializing ImageView
    @FXML
    private ImageView imgRegNo, imgChassisNo, imgBrand, imgKmCount, imgWeight, imgLength, imgWidth, imgHeight, imgNumberOfBeds,
            imgNumberOfSeatBelts, imgNumberOfToilets, imgNumberOfRentals, imgPurchaseDate, imgInSesonPrice, imgOutSesonPrice,
            imgCamperType;

    @FXML
    private DatePicker dpPurchaseDate;

    // initializing choise box
    @FXML
    private ChoiceBox<CamperType> cbCamperType;

    // initializing text area
    @FXML
    private TextArea taComment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // setting up textfield props.
        attatchIntegerValidation(tfKmCount, tfWeight, tfLength, tfWeight, tfHeight, tfNoBeds, tfNoSeatBelts,
                tfNoToilets, tfInSesonPrice, tfOutSesonPrice);

        // binding images to buttons
        bindImageToButton();

        // loading choose boxes.
        final DAO<CamperType, String> dao = new DAOImplCamperType();
        cbCamperType.getItems().addAll(dao.readAll());
    }

    /**
     * Helper method, used to make initialize method shorter, and hide repetitive
     * code. Binds text fields to image views for validation feedback.
     */
    private void bindImageToButton() {
        bindTextToImage(tfRegistrationNo, imgRegNo);
        bindTextToImage(tfChassisNo, imgChassisNo);
        bindTextToImage(tfBrand, imgBrand);
        bindTextToImage(tfKmCount, imgKmCount);
        bindTextToImage(tfWeight, imgWeight);
        bindTextToImage(tfLength, imgLength);
        bindTextToImage(tfWidth, imgWidth);
        bindTextToImage(tfHeight, imgHeight);
        bindTextToImage(tfNoBeds, imgNumberOfBeds);
        bindTextToImage(tfNoSeatBelts, imgNumberOfSeatBelts);
        bindTextToImage(tfNoToilets, imgNumberOfToilets);
        bindTextToImage(tfNoRentals, imgNumberOfRentals);
        bindTextToImage(tfInSesonPrice, imgInSesonPrice);
        bindTextToImage(tfOutSesonPrice, imgOutSesonPrice);

        bindDatePickerToImage(dpPurchaseDate, imgPurchaseDate);
        bindChoiseBoxToImage(cbCamperType, imgCamperType);
    }

    /**
     * Handles the save button click event. Adds a new autocamper to the database.
     */
    @FXML
    protected void onSaveClick() {
        // TODO Add validation for creating an autocamper.

        final Alert ALERT_SUCCESS = new Alert(MainController.getInstance(), 3, AlertType.SUCCESS, "auto camper was successfully created!");
        final Alert ALERT_ERROR = new Alert(MainController.getInstance(), 3, AlertType.ERROR, "The auto camper could not be created");
        final DAO<Autocamper, String> dao = new DaoImplAutoCamper();

        // checking if all requirements are for field.
        if (!validateCamperCreation()){
            ALERT_ERROR.start();
            return;
        }

        Autocamper autocamper = new Autocamper(tfChassisNo.getText(),
                tfRegistrationNo.getText(),
                tfBrand.getText(),
                taComment.getText(),
                cbCamperType.getSelectionModel().getSelectedItem().getType(),
                Integer.parseInt(tfKmCount.getText()),
                Integer.parseInt(tfNoRentals.getText()),
                Integer.parseInt(tfWeight.getText()),
                Integer.parseInt(tfLength.getText()),
                Integer.parseInt(tfWidth.getText()),
                Integer.parseInt(tfHeight.getText()),
                Integer.parseInt(tfNoBeds.getText()),

                Integer.parseInt(tfNoToilets.getText()),
                Integer.parseInt(tfNoSeatBelts.getText()),
                Integer.parseInt(tfInSesonPrice.getText()),
                Integer.parseInt(tfOutSesonPrice.getText()),
                DateTimeUtill.LocalTimeToDate(dpPurchaseDate.getValue())
                );


        dao.add(autocamper);

        ALERT_SUCCESS.start();

    }

    private boolean validateCamperCreation() {

        final TextField[] textFields = new TextField[]{
                tfRegistrationNo, tfChassisNo, tfBrand, tfKmCount, tfWeight, tfLength, tfWidth, tfHeight,
                tfNoBeds, tfNoSeatBelts, tfNoToilets, tfNoRentals, tfInSesonPrice, tfOutSesonPrice
        };

        for (TextField textField : textFields) {
            if (textField.getText().isEmpty()) return false;
        }

        return true;



    }

    /**
     * Handles the cancel button click event. Changes the view back to the home view.
     */
    @FXML
    protected void onCancelClick() {
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    }
}
