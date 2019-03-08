SET SCHEMA 'user_service';

INSERT INTO addresses (alias, street, zip, city, state, is_training_location)
	VALUES ('Reston', '11730 Plaza America Dr #205', 20190, 'Reston', 'VA', TRUE),
		   ('USF', 'Northwest Educational Complex', '33613', 'Tampa', 'FL', TRUE);
		   
INSERT INTO sms_users (first_name, last_name, email, phone_number, address)
	VALUES ('Blake', 'Kruppa', 'blake.kruppa@revature.com', '9093804081', (SELECT address_id FROM addresses WHERE alias = 'USF'));

