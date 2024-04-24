package org.apollo.template.Domain.Rental;

public class Customer {

    private String customerID, customerFirstName, customerLastName, customerPhoneNumber;
    private String customerEmail;
    private String customerDrivingLicenceNo;
    private String customerAddress;



    private String customerCountry;
    private int zipCity, noOfRentals;

    public Customer(String customerID, String customerFirstName, String customerLastName, String customerPhoneNumber,
                    String customerEmail, String customerDrivingLicenceNo, String customerAddress, String customerCountry,
                    int zipCity, int noOfRentals){

        setCustomerID(customerID);
        setCustomerFirstName(customerFirstName);
        setCustomerLastName(customerLastName);
        setCustomerPhoneNumber(customerPhoneNumber);
        setCustomerEmail(customerEmail);
        setCustomerDrivingLicenceNo(customerDrivingLicenceNo);
        setCustomerAddress(customerAddress);
        setCustomerCountry(customerCountry);
        setZipCity(zipCity);
        setNoOfRentals(noOfRentals);

    }

    // region Getter & Setter

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerDrivingLicenceNo() {
        return customerDrivingLicenceNo;
    }

    public void setCustomerDrivingLicenceNo(String customerDrivingLicenceNo) {
        this.customerDrivingLicenceNo = customerDrivingLicenceNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCountry() { return customerCountry; }

    public void setCustomerCountry(String customerCountry) { this.customerCountry = customerCountry;}

    public int getZipCity() {
        return zipCity;
    }

    public void setZipCity(int zipCity) {

        // if zipCity is negative
        if(!validateIntegerNotNegative(zipCity)) throw new RuntimeException("In setZipCity; Integer is negative! " + zipCity);

        this.zipCity = zipCity;
    }

    public int getNoOfRentals() {
        return noOfRentals;
    }

    public void setNoOfRentals(int noOfRentals) {

        // if noOfRentals is negative
        if(!validateIntegerNotNegative(noOfRentals)) throw new RuntimeException("In setNoOfRentals; Integer is negative! " + noOfRentals);

        this.noOfRentals = noOfRentals;
    }

    // endregion

    /**
     * Method that checks if an integer is negative.
     * @param integer to be checked.
     * @return boolean, false for negative.
     */
    private boolean validateIntegerNotNegative(int integer){
        return integer > 0;
    }

}
