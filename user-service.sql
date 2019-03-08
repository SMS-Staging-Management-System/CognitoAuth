SET SCHEMA 'user_service';

CREATE TABLE addresses (
	address_id SERIAL PRIMARY KEY,
	alias TEXT,
	street TEXT NOT NULL,
	zip TEXT NOT NULL,
	city TEXT NOT NULL,
	state TEXT NOT NULL,
	country TEXT NOT NULL DEFAULT 'United States',
	is_training_location BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE sms_users (
    sms_user_id SERIAL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone_number TEXT,
    address INTEGER NOT NULL REFERENCES addresses (address_id),
    CONSTRAINT sms_users_PK PRIMARY KEY (sms_user_id)
);

CREATE TABLE cohorts (
    cohort_id SERIAL,
    cohort_name TEXT NOT NULL UNIQUE,
    cohort_description TEXT,
    cohort_token TEXT,
    trainer_id INTEGER NOT NULL,
    start_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	  address INTEGER  NOT NULL REFERENCES addresses (address_id),
    CONSTRAINT sms_cohorts_PK PRIMARY KEY (cohort_id),
    CONSTRAINT sms_cohorts_FK_trainer FOREIGN KEY (trainer_id)
    REFERENCES sms_users (sms_user_id) ON DELETE CASCADE
);

CREATE TABLE users_cohorts (
    sms_user_id INTEGER NOT NULL,
    cohort_id INTEGER NOT NULL,
    CONSTRAINT sms_users_cohorts_PK PRIMARY KEY (sms_user_id, cohort_id),
    CONSTRAINT sms_users_cohorts_FK_user FOREIGN KEY (sms_user_id)
    REFERENCES sms_users (sms_user_id) ON DELETE CASCADE,
    CONSTRAINT sms_users_cohorts_FK_cohort FOREIGN KEY (cohort_id)
    REFERENCES cohorts (cohort_id) ON DELETE CASCADE
);
