package org.apollo.template.Service;

import org.apollo.template.Domain.Autocamper;

import java.sql.Date;

public class StartedRental {

    public static Date startOate = null;
    public static Date endOate = null;
    public static String autoCamperType = null;
    public static Autocamper autocamper = null;


    public static void setStartOate(Date selectedStartDate){
        startOate = selectedStartDate;
    }

    public static String getStartOate(){
        if(startOate != null){
            return startOate.toString();
        }
        return null;
    }

    public static void setEndOate(Date selectedEndDate){
        endOate = selectedEndDate;
    }

    public static String getEndOate(){
        if(endOate != null){
            return endOate.toString();
        }
        return null;
    }

    public static void setAutoCamperType(String selectedAutoCamperType){
        autoCamperType = selectedAutoCamperType;
    }

    public static String getAutoCamperType(){
        return autoCamperType;
    }


    public static void setSelectedAutocamper(Autocamper selectedAutocamper){
        autocamper = selectedAutocamper;
    }

    public static Autocamper getSelectedAutocamper(){
        if(autocamper != null){
            return autocamper;
        }
        return null;
    }

}
