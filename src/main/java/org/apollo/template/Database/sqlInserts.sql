
USE DB_Gaia;

    -- Inserting country;

GO;

INSERT INTO [DB_Gaia].[dbo].[tbl_country] ([fld_countryName], [fld_countryCode])
VALUES ('Denmark', 'DK');

INSERT INTO [DB_Gaia].[dbo].[tbl_country] ([fld_countryName], [fld_countryCode])
VALUES ('Germany', 'DE');

-- Inserting zipCity

GO;

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('1000', 'Copenhagen', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('5000', 'Odense', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('8000', 'Aarhus', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('9000', 'Aalborg', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('7100', 'Vejle', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('6000', 'Kolding', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('4700', 'Næstved', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('2300', 'Copenhagen S', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('7100', 'Vejle', 'Denmark');

INSERT INTO [DB_Gaia].[dbo].[tbl_zipCity] ([fld_zipcode], [fld_city], [fld_countryName])
VALUES ('2800', 'Kongens Lyngby', 'Denmark');

-- creating coustomers

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL123456', 'Lars', 'Nielsen', '11223344', 'lars.nielsen@example.com', 'DL123456', 'Hovedgaden 1', 11, 5);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL654321', 'Mette', 'Hansen', '22334455', 'mette.hansen@example.com', 'DL654321', 'Langgade 2', 12, 3);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL987654', 'Anders', 'Pedersen', '33445566', 'anders.pedersen@example.com', 'DL987654', 'Vej 3', 13, 7);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL456789', 'Lise', 'Jensen', '44556677', 'lise.jensen@example.com', 'DL456789', 'Gade 4', 14, 2);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL123789', 'Peter', 'Olsen', '55667788', 'peter.olsen@example.com', 'DL123789', 'Byvej 5', 15, 4);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL456123', 'Karen', 'Christensen', '66778899', 'karen.christensen@example.com', 'DL456123', 'Vinkelgade 6', 16, 6);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL789456', 'Jens', 'Rasmussen', '77889900', 'jens.rasmussen@example.com', 'DL789456', 'Bakkevej 7', 17, 1);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL321654', 'Anne', 'Mortensen', '88990011', 'anne.mortensen@example.com', 'DL321654', 'Skovvej 8', 18, 8);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL654987', 'Jørgen', 'Larsen', '99001122', 'jorgen.larsen@example.com', 'DL654987', 'Strandvej 9', 19, 9);

INSERT INTO [DB_Gaia].[dbo].[tbl_customer] ([fld_customerID], [fld_customerFirstName], [fld_customerLastName], [fld_customerPhoneNo], [fld_customerEmail], [fld_customerDrivingLicenceNo], [fld_customerStreetAdress], [fld_zipCityID], [fld_noOfRental])
VALUES ('DKDL987321', 'Maria', 'Andersen', '00112233', 'maria.andersen@example.com', 'DL987321', 'Bredgade 10', 20, 10);

-- Cmper types.

INSERT INTO [dbo].[tbl_autocamperType]
([fld_type]
    ,[fld_typeDescription])
VALUES
    ('LUXURY', 'Luxury autocampers offer top-of-the-line amenities and features, providing a luxurious travel experience for discerning travelers.'),
    ('REGULAR', 'Regular autocampers provide a comfortable and reliable option for travelers seeking a balance between comfort and affordability.'),
    ('BASIC', 'Basic autocampers are simple and functional, offering essential amenities for budget-conscious travelers looking for a no-frills camping experience.')


-- create campers
    GO

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
    ('ABC12345L1', -- Unique chassis number for LUXURY
    'AR125909',     -- Unique registration number for LUXURY
    50000,        -- Kilometer count
    10,           -- Number of rentals
    150.50,       -- Main season price
    100.00,       -- Low season price
    3000,         -- Weight in kg
    600,          -- Length in cm
    250,          -- Width in cm
    350,          -- Height in cm
    '2022-05-15', -- Purchase date
    4,            -- Number of beds
    1,            -- Number of toilets
    4,            -- Number of seatbelts
    'CamperCo',   -- Brand
    'Great condition', -- Comment
    'LUXURY'      -- Type
    ),
    ('ABC12345L2', 'BC236790', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY'),
    ('ABC12345L3', 'KL104050', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY'),
    ('ABC12345L4', 'KO103377', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'LUXURY')

-- Insert 4 REGULAR campers
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
    ('ABC12345R1', -- Unique chassis number for REGULAR
    'OK203040',     -- Unique registration number for REGULAR
    50000,        -- Kilometer count
    10,           -- Number of rentals
    150.50,       -- Main season price
    100.00,       -- Low season price
    3000,         -- Weight in kg
    600,          -- Length in cm
    250,          -- Width in cm
    350,          -- Height in cm
    '2022-05-15', -- Purchase date
    4,            -- Number of beds
    1,            -- Number of toilets
    4,            -- Number of seatbelts
    'CamperCo',   -- Brand
    'Great condition', -- Comment
    'REGULAR'     -- Type
    ),
    ('ABC12345R2', 'LO113450', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'REGULAR'),
    ('ABC12345R3', 'UK207789', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'REGULAR'),
    ('ABC12345R4', 'UL223466', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'REGULAR')

-- Insert 4 BASIC campers
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
    ('ABC12345B1', -- Unique chassis number for BASIC
    'XY721911',     -- Unique registration number for BASIC
    50000,        -- Kilometer count
    10,           -- Number of rentals
    150.50,       -- Main season price
    100.00,       -- Low season price
    3000,         -- Weight in kg
    600,          -- Length in cm
    250,          -- Width in cm
    350,          -- Height in cm
    '2022-05-15', -- Purchase date
    4,            -- Number of beds
    1,            -- Number of toilets
    4,            -- Number of seatbelts
    'CamperCo',   -- Brand
    'Great condition', -- Comment
    'BASIC'       -- Type
    ),
    ('ABC12345B2', 'LZ114820', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'BASIC'),
    ('ABC12345B3', 'DK203490', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'BASIC'),
    ('ABC12345B4', 'LL703053', 50000, 10, 150.50, 100.00, 3000, 600, 250, 350, '2022-05-15', 4, 1, 4, 'CamperCo', 'Great condition', 'BASIC')
