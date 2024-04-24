package org.apollo.template.Domain;

public class CoDriver {

    private String coDriverID, coDriverFirstName, coDriverLastName, coDriverPhoneNumber;
    private String coDriverEmail;
    private String coDriverDrivingLicenceNo;
    private String coDriverAddress;
    private String coDriverCountry;
    private int zipCity;


    public CoDriver(String coDriverID, String coDriverFirstName, String coDriverLastName, String coDriverPhoneNumber,
                    String coDriverEmail, String coDriverDrivingLicenceNo, String coDriverAddress, String coDriverCountry,
                    int zipCity){

        setCoDriverID(coDriverID);
        setCoDriverFirstName(coDriverFirstName);
        setCoDriverLastName(coDriverLastName);
        setCoDriverPhoneNumber(coDriverPhoneNumber);
        setCoDriverEmail(coDriverEmail);
        setCoDriverDrivingLicenceNo(coDriverDrivingLicenceNo);
        setCoDriverAddress(coDriverAddress);
        setCoDriverCountry(coDriverCountry);
        setZipCity(zipCity);
    }


    // region Getter and Setter
    public String getCoDriverID() {
        return coDriverID;
    }

    public void setCoDriverID(String coDriverID) {
        this.coDriverID = coDriverID;
    }

    public String getCoDriverFirstName() {
        return coDriverFirstName;
    }

    public void setCoDriverFirstName(String coDriverFirstName) {
        this.coDriverFirstName = coDriverFirstName;
    }

    public String getCoDriverLastName() {
        return coDriverLastName;
    }

    public void setCoDriverLastName(String coDriverLastName) {
        this.coDriverLastName = coDriverLastName;
    }

    public String getCoDriverPhoneNumber() {
        return coDriverPhoneNumber;
    }

    public void setCoDriverPhoneNumber(String coDriverPhoneNumber) {
        this.coDriverPhoneNumber = coDriverPhoneNumber;
    }

    public String getCoDriverEmail() {
        return coDriverEmail;
    }

    public void setCoDriverEmail(String coDriverEmail) {
        this.coDriverEmail = coDriverEmail;
    }

    public String getCoDriverDrivingLicenceNo() {
        return coDriverDrivingLicenceNo;
    }

    public void setCoDriverDrivingLicenceNo(String coDriverDrivingLicenceNo) {
        this.coDriverDrivingLicenceNo = coDriverDrivingLicenceNo;
    }

    public String getCoDriverAddress() {
        return coDriverAddress;
    }

    public void setCoDriverAddress(String coDriverAddress) {
        this.coDriverAddress = coDriverAddress;
    }

    public String getCoDriverCountry() {
        return coDriverCountry;
    }

    public void setCoDriverCountry(String coDriverCountry) {
        this.coDriverCountry = coDriverCountry;
    }

    public int getZipCity() {
        return zipCity;
    }

    public void setZipCity(int zipCity) {
        this.zipCity = zipCity;
    }
    // endregion



}
