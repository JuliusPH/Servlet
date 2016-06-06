INSERT INTO address VALUES (default, '1', 'Malinta', 'Valenzuela City', '1440');

INSERT INTO person VALUES (default, 'Peter', 'Odiaman', 'Elloreg', '1995-10-30', 80, null, false, 'Male');

INSERT INTO contact VALUES (default, 'Email', 'peter@gmail.com', 3);

UPDATE contact SET value = 'elloreg@gmail.com' WHERE person_id = 3 AND contact_type = 'Email';