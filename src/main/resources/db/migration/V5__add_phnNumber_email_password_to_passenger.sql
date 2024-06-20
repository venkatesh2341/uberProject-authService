ALTER TABLE passenger ADD COLUMN email VARCHAR(225) NOT NULL,
                       ADD COLUMN phone_number VARCHAR(225) NOT NULL,
                       ADD COLUMN password VARCHAR(225) NOT NULL,
                       MODIFY COLUMN name VARCHAR(225) NOT NULL;