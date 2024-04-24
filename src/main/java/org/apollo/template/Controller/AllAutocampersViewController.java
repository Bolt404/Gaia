package org.apollo.template.Controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.UI.CamperComponent;
import org.apollo.template.persistence.Dao.DAO;
import org.apollo.template.persistence.Dao.DAOImplCamperType;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class AllAutocampersViewController implements Initializable {
    private List<Autocamper> autocamperList = new ArrayList<>();
    @FXML
    private AnchorPane root;
    @FXML
    private GridPane gridPane;
    private int gridColumn;
    private int gridRow;

    private void loadGrid() {
        gridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);

        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(50);
        gridPane.setVgap(50);



        root.getChildren().add(gridPane);
        for (Autocamper autocamper : autocamperList) {
            addCompToGrid();
        }
    }

    public void addCompToGrid() {
        if (gridColumn < 6) { // Check if the column count is less than 6
            // Add a new button to the gridPane at the current gridRow and gridColumn
            CamperComponent camperComp = new CamperComponent();
            gridPane.add(camperComp, gridColumn++, gridRow);

            // If the gridColumn reaches the maximum, reset it to 0 and move to the next row
            if (gridColumn == 6) {
                gridColumn = 0;
                gridRow++;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(this::pik);
    }

    public void pik() {
        DaoImplAutoCamper daoImplAutoCamper = new DaoImplAutoCamper();
        autocamperList.addAll(daoImplAutoCamper.readAll());
        loadGrid();
    }
}

