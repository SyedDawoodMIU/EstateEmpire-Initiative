-- Insert data into the Property table
INSERT INTO Property (type, view_count) VALUES ('FOR_BUY', 100);
INSERT INTO Property (type, view_count) VALUES ('FOR_RENT', 50);
--
-- Insert data into the PropertyDetails table
INSERT INTO Property_details ( bedrooms, bathrooms, lot_size, rent_amount, security_deposit_amount, year_built, description)
VALUES (2, 1, 1000, 1500.00, 3000.00, 2000, 'Introducing an exquisite property that seamlessly blends luxury and comfort in a captivating setting. This stunning residence offers unparalleled elegance and contemporary design, featuring spacious living areas bathed in natural light. The gourmet kitchen boasts top-of-the-line appliances and sleek finishes, perfect for culinary enthusiasts. Retreat to the lavish master suite with a spa-like ensuite bathroom and breathtaking views. Outdoor entertainment is elevated with a meticulously landscaped garden, an inviting swimming pool, and a serene patio for al fresco dining. Complete with state-of-the-art amenities and a prime location, this property promises a lifestyle of opulence and serenity.');
INSERT INTO Property_details (bedrooms, bathrooms, lot_size, rent_amount, security_deposit_amount, year_built, description)
VALUES (3, 2, 1500, 2000.00, 4000.00, 1990,'Presenting an extraordinary property that epitomizes modern luxury and harmonious living. Step into this architectural masterpiece that exudes sophistication and style. The grand foyer welcomes you to an expansive floor plan adorned with exquisite details and craftsmanship. Indulge in the chef''s kitchen, equipped with high-end appliances and custom cabinetry. Unwind in the opulent master suite, featuring a spa-inspired ensuite and a private balcony overlooking breathtaking vistas. The outdoor oasis boasts a meticulously landscaped garden, a sparkling pool, and an inviting outdoor lounge area. With unrivaled amenities and an enviable location, this property offers a lifestyle of unparalleled opulence and tranquility.');

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
