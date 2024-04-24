package org.apollo.template.Domain;

import java.util.Date;

/**
 * Data model of auto camper.
 */
public class Autocamper {

    private String chassisNo, registrationNo, brand, comment, type;
    private Date purchaseDate;
    private int kmCount, noOfRental, weight, length, width, height, noOfBeds, noOfToilets, noOfSeatbelts;
    private float mainSeasonPrice, lowSeasonPrice;

    public Autocamper(){}

    public Autocamper(String chassisNo, String registrationNo, String brand, String comment, String type,
                      int kmCount, int noOfRental, int weight, int length, int width, int height,
                      int noOfBeds, int noOfToilets, int noOfSeatbelts, float mainSeasonPrice, float lowSeasonPrice) {

        setChassisNo(chassisNo);
        setRegistrationNo(registrationNo);
        setBrand(brand);
        setComment(comment);
        setType(type);
        setKmCount(kmCount);
        setNoOfRental(noOfRental);
        setWeight(weight);
        setLength(length);
        setWidth(width);
        setHeight(height);
        setNoOfBeds(noOfBeds);
        setNoOfToilets(noOfToilets);
        setNoOfSeatbelts(noOfSeatbelts);
        setMainSeasonPrice(mainSeasonPrice);
        setLowSeasonPrice(lowSeasonPrice);

    }

// region getter & setters

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getKmCount() {
        return kmCount;
    }

    public void setKmCount(int kmCount) throws RuntimeException{

        // checks if kmCount is negative.
        if (!validateIntegerNotNegative(kmCount)) throw new RuntimeException("In setKmCount; Integer is negative! " + kmCount);

        this.kmCount = kmCount;
    }

    public int getNoOfRental() throws RuntimeException{
        return noOfRental;
    }

    public void setNoOfRental(int noOfRental) throws RuntimeException{

        // checks if noOfRental is negative.
        if (!validateIntegerNotNegative(kmCount)) throw new RuntimeException("In setNoOfRental; Integer is negative! " + noOfRental);

        this.noOfRental = noOfRental;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws RuntimeException{

        // checks if weight is negative.
        if (!validateIntegerNotNegative(weight)) throw new RuntimeException("In setWeight; Integer is negative! " + weight);

        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) throws RuntimeException{

        // checks if length is negative.
        if (!validateIntegerNotNegative(weight)) throw new RuntimeException("In setLength; Integer is negative! " + length);

        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws RuntimeException{

        // checks if width is negative.
        if (!validateIntegerNotNegative(weight)) throw new RuntimeException("In setWidth; Integer is negative! " + width);

        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws RuntimeException{

        // checks if width is negative.
        if (!validateIntegerNotNegative(height)) throw new RuntimeException("In setHeight; Integer is negative! " + height);

        this.height = height;
    }

    public int getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(int noOfBeds) throws RuntimeException{

        // checks if width is negative.
        if (!validateIntegerNotNegative(noOfBeds)) throw new RuntimeException("In setNoOfBeds; Integer is negative! " + noOfBeds);

        this.noOfBeds = noOfBeds;
    }

    public int getNoOfToilets() {
        return noOfToilets;
    }

    public void setNoOfToilets(int noOfToilets) throws RuntimeException{

        // checks if width is negative.
        if (!validateIntegerNotNegative(noOfToilets)) throw new RuntimeException("In setNoOfToilets; Integer is negative! " + noOfToilets);

        this.noOfToilets = noOfToilets;
    }

    public int getNoOfSeatbelts() {
        return noOfSeatbelts;
    }

    public void setNoOfSeatbelts(int noOfSeatbelts) throws RuntimeException{

        // checks if width is negative.
        if (!validateIntegerNotNegative(noOfSeatbelts)) throw new RuntimeException("In setNoOfSeatbelts; Integer is negative! " + noOfSeatbelts);

        this.noOfSeatbelts = noOfSeatbelts;
    }

    public float getMainSeasonPrice() {
        return mainSeasonPrice;
    }

    public void setMainSeasonPrice(float mainSeasonPrice) throws RuntimeException{

        if (!validateFloatNotNegative(mainSeasonPrice)) throw  new RuntimeException("In setMainSeasonPrice; Float is negative! " + mainSeasonPrice);

        this.mainSeasonPrice = mainSeasonPrice;
    }

    public float getLowSeasonPrice() {
        return lowSeasonPrice;
    }

    public void setLowSeasonPrice(float lowSeasonPrice) throws RuntimeException{

        if (!validateFloatNotNegative(lowSeasonPrice)) throw  new RuntimeException("In setLowSeasonPrice; Float is negative! " + lowSeasonPrice);


        this.lowSeasonPrice = lowSeasonPrice;
    }

    public java.sql.Date getSQLPurchaseDate() {
        return (java.sql.Date) purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    /**
     * Method that checks if an floatingPointNumber is negative.
     * @param floatingPointNumber to be checked.
     * @return boolean, false for negative.
     */
    private boolean validateFloatNotNegative(float floatingPointNumber){
        return floatingPointNumber > 0;
    }


    public String toString(){
        return String.format("%s  -  %s  -  %d  -  %d  -  %.2f  -  %.2f",getType(),getBrand(),getNoOfBeds(),getNoOfSeatbelts(),getMainSeasonPrice(),getLowSeasonPrice());
    }

}
