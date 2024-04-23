package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Domain.CamperType;
import org.apollo.template.Service.Alert.Alert;
import org.apollo.template.Service.Alert.AlertType;
import org.apollo.template.View.BorderPaneRegion;
import org.apollo.template.View.ViewList;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DAOImplCamperType;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.net.URL;
import java.util.ResourceBundle;

import static org.apollo.template.Service.ListenerUtill.attatchIntegerValidation;
import static org.apollo.template.Service.TextToImageBind.bindTextToImage;

public class CreateAutocamperController implements Initializable {

    @FXML
    private TextField tfRegistrationNo, tfChassisNo, tfBrand, tfKmCount, tfWeight, tfLength, tfWidth, tfHeight,
            tfNoBeds, tfNoSeatBelts, tfNoToilets, tfNoRentals, tfPurchaseDate, tfCamperType,
            tfInSesonPrice, tfOutSesonPrice;

    @FXML
    ImageView imgRegNo, imgChassisNo, imgBrand, imgKmCount, imgWeight, imgLength, imgWidth, imgHeight, imgNumberOfBeds,
        imgNumberOfSeatBelts, imgNumberOfToilets, imgNumberOfRentals, imgPurchaseDate, imgInSesonPrice, imgOutSesonPrice,
            imgCamperType;

    @FXML
    private ChoiceBox<CamperType> cbCamperType;
    @FXML
    private TextArea taComment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting up textfield props.
        attatchIntegerValidation(tfKmCount, tfWeight, tfLength, tfWeight, tfHeight, tfNoBeds, tfNoSeatBelts, tfNoToilets);

        // binding images to buttons
        bindImageToButton();

        // loading choose boxes.
        final DAO<CamperType, String> dao = new DAOImplCamperType();
        cbCamperType.getItems().addAll(dao.readAll());

    }

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
        bindTextToImage(tfPurchaseDate, imgPurchaseDate);
        bindTextToImage(tfInSesonPrice, imgInSesonPrice);
        bindTextToImage(tfOutSesonPrice, imgOutSesonPrice);

    }

    @FXML
    protected void onSaveClick(){

        final Alert ALERT_SUCESS = new Alert(MainController.getInstance(), 3, AlertType.SUCCESS, "auto camper was successfully created!");
        final Alert ALERT_ERROR = new Alert(MainController.getInstance(), 3, AlertType.ERROR, "The auto camper could not be created");
        final DAO<Autocamper, String> dao = new DaoImplAutoCamper();

        dao.add(new Autocamper(tfChassisNo.getText(),
                tfRegistrationNo.getText(),
                tfBrand.getText(),
                taComment.getText(),
                cbCamperType.getTypeSelector(),

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
                Integer.parseInt(tfOutSesonPrice.getText())
                ));

    };

    @FXML
    protected void onCancelClick(){
        MainController.getInstance().changeView(ViewList.HOME, BorderPaneRegion.CENTER);
    };

}
