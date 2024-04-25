package org.apollo.template.Domain;

/*
    Class for creating Receipt from a rental
 */

import org.apollo.template.Domain.Rental.Rental;
import org.apollo.template.Service.FileService;

import java.io.*;

public class Receipt {

    private String ReceiptName;
    private final String HEDDER = "GAIA AUTOCAMPER RENTAL A/S";
    private Rental rental;
    private final String PHONE_NUMBER = "+45 21 52 12 93";
    private final String EMAIL = "Wagner@gaia.de";

    public Receipt(Rental rental) {

        // checks if rental not null
        if (rental == null) throw new NullPointerException("In receipt constructor; Rental object can not be null!");

        this.rental = rental;

        // giving the recept a name
        ReceiptName = "Receipt_" + rental.getStartDate() + "_" + rental.getEndDate() + "_" + rental.getChassisNo();

        writeRecept();


    }

    private void writeRecept(){
        FileService fs = new FileService(ReceiptName + ".txt");


        fs.writeToFile(HEDDER);
        fs.writeToFile(" ", true);
        fs.writeToFile(" ", true);
        fs.writeToFile(" ", true);

        final String BOOKING_MESSAGE = "Thank you for booking your trip with us " + HEDDER;
        fs.writeToFile(BOOKING_MESSAGE, true);
        fs.writeToFile(" ", true);

        final String STARTING_DATE = "The trips starting date: " + rental.getStartDate();
        fs.writeToFile(STARTING_DATE, true);

        final String ENDING_DATE = "The trips starting date: " + rental.getEndDate();
        fs.writeToFile(ENDING_DATE, true);

        fs.writeToFile(" ", true);
        fs.writeToFile(" ", true);
        fs.writeToFile(" ", true);
        final String REGARDS = "With kind regards " + HEDDER;
        fs.writeToFile(REGARDS, true);

        fs.writeToFile(EMAIL, true);
        fs.writeToFile(PHONE_NUMBER, true);


    }

}
