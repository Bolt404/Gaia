-- Script to GAIA database

CREATE DATABASE DB_Gaia;

GO

use DB_Gaia;

-- Creating the structure for the GAIA database


-- 1. table:
CREATE TABLE tbl_autocamperType(
	fld_type varchar(8) PRIMARY KEY,
	fld_typeDescription varchar(256)
);


-- 2. table:
CREATE TABLE tbl_autocamper(
	fld_chassisNo varchar(20) PRIMARY KEY,
	fld_registrationNo varchar(10),
	fld_kmCount INTEGER,
	fld_noOfRental INTEGER,
	fld_mainSeasonPrice FLOAT,
	fld_lowSeasonPrice FLOAT,
	fld_weight INTEGER,
	fld_length INTEGER,
	fld_width INTEGER,
	fld_height INTEGER,
	fld_purchaseDate Date,
	fld_noOfBeds INTEGER,
	fld_noOfToilets INTEGER,
	fld_noOfSeatbelts INTEGER,
	fld_brand varchar(30),
	fld_comment varchar(256),
	fld_type varchar(8),
	FOREIGN KEY (fld_type) REFERENCES tbl_autocamperType(fld_type),
);


-- 3. table:
CREATE TABLE tbl_insurance(
	fld_insuranceID varchar(50) PRIMARY KEY,
	fld_description varchar(250)
);


-- 4. table:
CREATE TABLE tbl_rental(
	fld_rentalID INTEGER IDENTITY(100,1) PRIMARY KEY,		
	fld_startDate DATE,
	fld_endDate DATE,
	fld_chassisNo varchar(20),
	fld_insuranceID varchar(50),
	FOREIGN KEY (fld_chassisNo) REFERENCES tbl_autocamper (fld_chassisNo),
	FOREIGN KEY (fld_insuranceID) REFERENCES tbl_insurance (fld_insuranceID)
);


-- 5. table:
CREATE TABLE tbl_billType(
	fld_typeNo INTEGER IDENTITY(1,1) PRIMARY KEY,
	fld_typeOfBill varchar(50)
);


-- 6. table:
CREATE TABLE tbl_bill(
	fld_billID INTEGER IDENTITY (10,1) PRIMARY KEY,
	fld_typeNo INTEGER,
	fld_rentalID INTEGER,
	FOREIGN KEY (fld_typeNo) REFERENCES tbl_billType (fld_typeNo),
	FOREIGN KEY (fld_rentalID) REFERENCES tbl_rental (fld_rentalID)
);


-- 7. table:
CREATE TABLE tbl_country(
	fld_countryName varchar(60) PRIMARY KEY,				-- varchar(60) is based on the longest official country name in English, 
															-- "The United Kingdom of Great Britain and Northern Ireland"
	fld_countryCode varchar(3)
);


-- 8. table:
CREATE TABLE tbl_zipCity(
	fld_zipCityID INTEGER IDENTITY (1,1) PRIMARY KEY,
	fld_zipcode varchar(12),
	fld_city varchar(128),
	fld_countryName varchar(60)
	FOREIGN KEY(fld_countryName) REFERENCES tbl_country (fld_countryName)
);


-- 9. table:
CREATE TABLE tbl_customer(
	fld_customerID varchar(32) PRIMARY KEY,					-- customerID consists of an "ISO 3166-1 alpha-2" country code followed by the customer's 
															-- unique driving licence number. This composite key is used because driving licence numbers 
															-- alone may not be unique globally. Including the country code ensures a unique identifier 
															-- across international customers, which is crucial for maintaining unique records in the database.
	fld_customerFirstName varchar(50),
	fld_customerLastName varchar(100),
	fld_customerPhoneNo varchar(20),
	fld_customerEmail varchar(256),
	fld_customerDrivingLicenceNo varchar(30),
	fld_customerStreetAdress varchar(128),
	fld_customerCountry varchar(50),
	fld_zipCityID INTEGER,
	fld_noOfRental INTEGER,
	FOREIGN KEY (fld_zipCityID) REFERENCES tbl_zipCity (fld_zipCityID)
);


-- 10. table:
CREATE TABLE tbl_customerRental(
	fld_customerRentalID INTEGER IDENTITY (1,1) PRIMARY KEY,
	fld_rentalID INTEGER,
	fld_customerID varchar(32),
	FOREIGN KEY (fld_rentalID) REFERENCES tbl_rental (fld_rentalID),
	FOREIGN KEY (fld_customerID) REFERENCES tbl_customer (fld_customerID)
);


-- 11. table:
CREATE TABLE tbl_coDriver(
	fld_coDriverID INTEGER IDENTITY (1000,1) PRIMARY KEY,
	fld_coFirstName varchar(50),
	fld_coLastName varchar(100),
	fld_coPhoneNo varchar(20),
	fld_coEmail varchar(256),
	fld_coDrivingLicenceNo varchar(30),
	fld_coStreetAdress varchar(128),
	fld_zipCityID INTEGER,
	fld_rentalID INTEGER,
	FOREIGN KEY (fld_zipCityID) REFERENCES tbl_zipCity (fld_zipCityID),
	FOREIGN KEY (fld_rentalID) REFERENCES tbl_rental (fld_rentalID)
);