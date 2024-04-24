package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.UI.CamperComponent;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class AllAutocampersViewController implements Initializable {
    @FXML
    private ListView<Autocamper> listViewAutocamper;
    @FXML
    private TableView<Autocamper> tableViewAutocamper;

    @FXML
    private TableColumn<Autocamper, String> registration;
    @FXML
    private TableColumn<Autocamper, String> type;
    @FXML
    private TableColumn<Autocamper, Integer> noBeds;
    @FXML
    private TableColumn<Autocamper, Integer> noToilets;
    @FXML
    private TableColumn<Autocamper, Integer> noSeats;
    @FXML
    private TableColumn<Autocamper, Float> high;
    @FXML
    private TableColumn<Autocamper, Float> low;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }



    public void setListViewAutocamper() {
        listViewAutocamper.getItems().addAll(new DaoImplAutoCamper().readAll());

        ArrayList<Autocamper> arrayList = new ArrayList<>();
        arrayList.addAll(new DaoImplAutoCamper().readAll());
        System.out.println(arrayList.get(1).toString());

    }

    public ListView<Autocamper> getListViewAutocamper() {
        return listViewAutocamper;
    }
}

