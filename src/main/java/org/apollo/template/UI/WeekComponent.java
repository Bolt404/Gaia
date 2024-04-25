package org.apollo.template.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Calendar;

public class WeekComponent extends VBox {

    private Label label;
    private int contentCount;
    private Calendar calendar;

    public WeekComponent(Calendar week) {

        int calenderWeek = week.get(calendar.getWeeksInWeekYear());

        this.label = new Label(String.valueOf(calenderWeek));

        // attaching lable to Vbox;
        getChildren().add(label);

    }

    public void addBookingComponent(BookingComponent bookingComponent){
        getChildren().add(bookingComponent);
    }


}
