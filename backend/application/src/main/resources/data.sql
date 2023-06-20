-- Insert data into the Property table
INSERT INTO Property (type, view_count) VALUES ('Apartment', 100);
INSERT INTO Property (type, view_count) VALUES ('House', 50);
--
-- Insert data into the PropertyDetails table
INSERT INTO Property_details ( bedrooms, bathrooms, lot_size, rent_amount, security_deposit_amount, year_built)
VALUES (2, 1, 1000, 1500.00, 3000.00, 2000);
INSERT INTO Property_details (bedrooms, bathrooms, lot_size, rent_amount, security_deposit_amount, year_built)
VALUES (3, 2, 1500, 2000.00, 4000.00, 1990);

-- Insert data into the Address table
INSERT INTO Address (address_id,street, city, state, zip_code, country)
VALUES (1,'123 Main St', 'Cityville', 'Stateville', '12345', 'USA');
INSERT INTO Address (address_id,street, city, state, zip_code, country)
VALUES (2,'456 Elm St', 'Townville', 'Stateville', '54321', 'USA');

-- Update Property table with the foreign key references to PropertyDetails
UPDATE Property SET details_id = 1 WHERE property_id = 1;
UPDATE Property SET details_id = 2 WHERE property_id = 2;

-- Update Property table with the foreign key references to Address
UPDATE Property SET address_address_id = 1 WHERE property_id = 1;
UPDATE Property SET address_address_id = 2 WHERE property_id = 2;
