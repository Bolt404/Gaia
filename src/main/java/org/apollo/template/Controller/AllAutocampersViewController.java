package org.apollo.template.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.UI.CamperComponent;
import org.apollo.template.persistence.Dao.DaoImplAutoCamper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class AllAutocampersViewController implements Initializable {
    private List<Autocamper> autocamperList = new ArrayList<>();
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox mainVbox;
    private int gridColumn;
    private int gridRow;

    /**
     * Generate Grid of Autocampers.
     */
    private void loadGrid() {
        gridPane = new GridPane();

        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(50);
        gridPane.setVgap(50);

        mainVbox.getChildren().add(gridPane);
        for (Autocamper autocamper : autocamperList) {
            addCompToGrid(autocamper);
        }
    }

    /**
     * Add CamperComponent to grid.
     * @param autocamper Object
     */
    public void addCompToGrid(Autocamper autocamper) {
        if (gridColumn < 3) { // Check if the column count is less than 6
            // Add a new button to the gridPane at the current gridRow and gridColumn
            CamperComponent camperComp = new CamperComponent(autocamper);
            gridPane.add(camperComp, gridColumn++, gridRow);

            // If the gridColumn reaches the maximum, reset it to 0 and move to the next row
            if (gridColumn == 3) {
                gridColumn = 0;
                gridRow++;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(this::getAutoCampersFromDB); //We are using runLater because apparently containers are generated after initialize?
    }

    public void getAutoCampersFromDB() {
        DaoImplAutoCamper daoImplAutoCamper = new DaoImplAutoCamper();
        autocamperList.addAll(daoImplAutoCamper.readAll());
        loadGrid();
    }
}

