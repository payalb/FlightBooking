ALTER TABLE flight
ADD COLUMN first_price NUMERIC,
ADD COLUMN business_price NUMERIC,
ADD COLUMN economy_price NUMERIC;

UPDATE flight
SET first_price = 3809.1,
business_price = 1000,
economy_price = 131.8;