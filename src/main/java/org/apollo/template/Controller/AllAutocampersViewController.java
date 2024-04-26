package org.apollo.template.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import org.apollo.template.Domain.Autocamper;
import org.apollo.template.Service.Debugger.DebugMessage;
import org.apollo.template.UI.CamperComponent;
import org.apollo.template.persistence.Dao.DaoAbleImplAutoCamper;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
public class AllAutocampersViewController implements Initializable {

    private static AllAutocampersViewController instance = null;

    private List<Autocamper> autocamperList = new ArrayList<>();
    private GridPane gridPane;
    @FXML
    private VBox mainVbox;
    private int gridColumn;
    private int gridRow;
    private boolean hasRun = false;

    private DaoAbleImplAutoCamper daoImplAutoCamper = new DaoAbleImplAutoCamper();

    private AllAutocampersViewController() {

    }

    /**
     * Generate Grid of Autocampers.
     */
    private void loadGrid() {
        if (gridPane == null) {
            DebugMessage.info(this,"LoadGrid: GridPane was Null");
            gridPane = new GridPane();

            gridPane.setPadding(new Insets(30, 30, 30, 30));
            gridPane.setHgap(50);
            gridPane.setVgap(50);

            DebugMessage.info(this,"LoadGrid: New GridPane " + gridPane);
            mainVbox.getChildren().add(gridPane);
        } else {
            DebugMessage.error(this, "LoadGrid: GirdPane wasn't Null " + gridPane + " Columns: " + gridPane.getColumnCount() + " Rows: " + gridPane.getRowCount());
            gridPane = null; //Setting gridPane to NULL so that we can start over fresh, it's easier to recreate than change the existing one.
            DebugMessage.info(this, "LoadGrid: Setting GRID to NULL and recreating.");
            loadGrid();
        }


        // Reset grid row and column counters
        gridColumn = 0;
        gridRow = 0;
        // Add components to the grid
        getAutoCampersFromDB();
        DebugMessage.info(this,"GridGen: Length of autocamperList " + autocamperList.size());
        for (Autocamper autocamper : autocamperList) {
            DebugMessage.info(this,"Grid Gen: Trying to add Autocamper " + autocamper.getChassisNo());
            addCompToGrid(autocamper);
        }
    }
    /**
     * Add CamperComponent to grid.
     * @param autocamper Object
     */
    private void addCompToGrid(Autocamper autocamper) {
        // Create a new CamperComponent
        CamperComponent camperComp = new CamperComponent(autocamper, randomGenImagePath());

        // Add the component to the grid at the specified position
        gridPane.add(camperComp, gridColumn, gridRow);
        DebugMessage.info(this,"AddCompToGrid: Component was added " + camperComp + " " + autocamper.getChassisNo());

        // Increment column and row counters
        gridColumn++;


        // If the column count reaches the maximum, reset it to 0 and move to the next row
        if (gridColumn == 3) {
            gridColumn = 0;
            gridRow++;
        }
    }

    /**
     * Placeholder for actual images.
     * @return
     */
    private String randomGenImagePath (){
        Random random = new Random();
        File resources = new File("src/main/resources/org/apollo/template/images/autocampers");
        File[] files = resources.listFiles();

        int randomIndex = random.nextInt(files.length);

        File randomFile = files[randomIndex];

        return randomFile.getPath();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadGrid();
    }

    /**
     * Generates a List of Autocamper Objects based on information from the DB.
     */
    public void getAutoCampersFromDB() {
        autocamperList.clear();

        List<Autocamper> dblist = daoImplAutoCamper.readAll();

        for (Autocamper autocamper : dblist) {
            if (!autocamperList.contains(autocamper)){
                autocamperList.add(autocamper);
                DebugMessage.info(this, "AutoCamperFromDB: Added to list " + autocamper.getChassisNo());
                DebugMessage.info(this,"AutoCamperFromDB: Length of autocamperList " + autocamperList.size());
            }
        }
    }

    public static AllAutocampersViewController getInstance(){
        if (instance == null) {
            instance = new AllAutocampersViewController();
        }

        return instance;
    }
}