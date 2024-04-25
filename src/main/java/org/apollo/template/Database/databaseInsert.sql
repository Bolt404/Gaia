

USE DB_Gaia;

-- Inserting country; 

INSERT INTO [DB_Gaia].[dbo].[tbl_country] ([fld_countryName], [fld_countryCode])
VALUES ('Denmark', 'DK'),
       ('Germany', 'DE');

-- Inserting zipCity

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('1000', 'Copenhagen', 'Denmark'),
       ('5000', 'Odense', 'Denmark'),
       ('8000', 'Aarhus', 'Denmark'),
       ('9000', 'Aalborg', 'Denmark'),
       ('7100', 'Vejle', 'Denmark'),
       ('6000', 'Kolding', 'Denmark'),
       ('4700', 'Næstved', 'Denmark'),
       ('2300', 'Copenhagen S', 'Denmark'),
       ('7100', 'Vejle', 'Denmark'),
       ('2800', 'Kongens Lyngby', 'Denmark');

-- creating customers

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL123456', 'Lars', 'Nielsen', '11223344', 'lars.nielsen@example.com', 'DL123456', 'Hovedgaden 1', 1, 5),
       ('DKDL654321', 'Mette', 'Hansen', '22334455', 'mette.hansen@example.com', 'DL654321', 'Langgade 2', 2, 3),
       ('DKDL987654', 'Anders', 'Pedersen', '33445566', 'anders.pedersen@example.com', 'DL987654', 'Vej 3', 3, 7),
       ('DKDL456789', 'Lise', 'Jensen', '44556677', 'lise.jensen@example.com', 'DL456789', 'Gade 4', 4, 2),
       ('DKDL123789', 'Peter', 'Olsen', '55667788', 'peter.olsen@example.com', 'DL123789', 'Byvej 5', 5, 4),
       ('DKDL456123', 'Karen', 'Christensen', '66778899', 'karen.christensen@example.com', 'DL456123', 'Vinkelgade 6', 6, 6),
       ('DKDL789456', 'Jens', 'Rasmussen', '77889900', 'jens.rasmussen@example.com', 'DL789456', 'Bakkevej 7', 7, 1),
       ('DKDL321654', 'Anne', 'Mortensen', '88990011', 'anne.mortensen@example.com', 'DL321654', 'Skovvej 8', 8, 8),
       ('DKDL654987', 'Jørgen', 'Larsen', '99001122', 'jorgen.larsen@example.com', 'DL654987', 'Strandvej 9', 9, 9),
       ('DKDL987321', 'Maria', 'Andersen', '00112233', 'maria.andersen@example.com', 'DL987321', 'Bredgade 10', 10, 10);

-- Car types

INSERT INTO [dbo].[tbl_autocamperType]
           ([fld_type]
           ,[fld_typeDescription])
     VALUES
           ('LUXURY', 'Luxury autocampers offer top-of-the-line amenities and features, providing a luxurious travel experience for discerning travelers.'),
           ('REGULAR', 'Regular autocampers provide a comfortable and reliable option for travelers seeking a balance between comfort and affordability.'),
           ('BASIC', 'Basic autocampers are simple and functional, offering essential amenities for budget-conscious travelers looking for a no-frills camping experience.');

-- create campers

-- Insert 4 LUXURY campers
INSERT INTO [dbo].[tbl_autocamper]
           ([fld_chassisNo]
           ,[fld_registrationNo]
           ,[fld_kmCount]
           ,[fld_noOfRental]
           ,[fld_mainSeasonPrice]
           ,[fld_lowSeasonPrice]
           ,[fld_weight]
           ,[fld_length]
           ,[fld_width]
           ,[fld_height]
           ,[fld_purchaseDate]
           ,[fld_noOfBeds]
           ,[fld_noOfToilets]
           ,[fld_noOfSeatbelts]
           ,[fld_brand]
           ,[fld_comment]
           ,[fld_type])
     VALUES
           ('ABC12345L1', 'AR125909', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY'),
           ('ABC12345L2', 'BC236790', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY'),
           ('ABC12345L3', 'KL104050', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY'),
           ('ABC12345L4', 'KO103377', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY');

INSERT INTO [dbo].[tbl_insurance] ([fld_insuranceID], [fld_description])
VALUES 
    ('Basic', 'EASY CHEAP'),
    ('Super Cover Plus', 'EXPENSIVE');


-- SP 
GO

CREATE PROCEDURE [dbo].[customerDataFromEmail] 
    @email varchar(256)
AS
BEGIN

SELECT * FROM tbl_customer INNER JOIN tbl_zipCity ON tbl_customer.fld_zipCityID = tbl_zipCity.fld_zipCityID WHERE fld_customerEmail = @email
END
