package org.apollo.template.Domain.Rental;

import org.apollo.template.Domain.Autocamper;

import java.sql.Date;

public class Rental {

    private Date startDate, endDate;
    private String chassisNo, insuranceID;
    private Autocamper autocamper;

    public Rental(){}

    public Rental(Date startDate, Date endDate, String chassisNo, String insuranceID) {
        setStartDate(startDate);
        setEndDate(endDate);
        setChassisNo(chassisNo);
        setInsuranceID(insuranceID);
    }




    // region Getter and Setter
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Autocamper getAutocamper() {
        return autocamper;
    }

    public void setAutocamper(Autocamper autocamper) {
        this.autocamper = autocamper;
    }

    // endregion
}
