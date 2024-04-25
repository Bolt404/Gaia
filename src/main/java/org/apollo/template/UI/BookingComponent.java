package org.apollo.template.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookingComponent extends VBox {

    private Label startWeek, endWeek, licensPlate;
    private HBox startWeekHbox, endWeekHbox, licencePlateHbox;

    public BookingComponent(Label startWeek, Label endWeek, Label licensPlate) {
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.licensPlate = licensPlate;

        // atatching labels the assosiated hbox;
        startWeekHbox.getChildren().addAll(new Label("Starting week: "), startWeek);
        endWeekHbox.getChildren().addAll(new Label("end week: "), endWeek);
        licencePlateHbox.getChildren().addAll(new Label("License plate: "), licensPlate);



        getChildren().addAll(startWeek, endWeek, licensPlate);

    }
}
