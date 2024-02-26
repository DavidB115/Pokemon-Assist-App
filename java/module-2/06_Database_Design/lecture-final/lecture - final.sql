START TRANSACTION;

DROP TABLE IF EXISTS person, contact, entity_contact, contact_type, art, purchase;

CREATE TABLE person (
	person_id serial,
	person_name varchar(64),
	constraint pk_person primary key (person_id) 
);

CREATE TABLE contact(
	contact_id serial,
	contact_street_address_1 varchar(30),
	contact_street_address_2 varchar(30),
	contact_city varchar(20),
	contact_state varchar(2),
	contact_zip varchar(10),
	contact_phone varchar(10),
	constraint pk_contact primary key (contact_id) 
);

CREATE TABLE contact_type (
	contact_type_id int not null,
	contact_type_name varchar(10),
	constraint pk_contact_type primary key (contact_type_id)
);

CREATE TABLE entity_contact(
	contact_id int not null,
	person_id int not null,
	contact_type_id int not null,
	constraint pk_entity_contact primary key (contact_id, person_id),
	constraint fk_entity_contact_contact foreign key (contact_id) references contact (contact_id),
	constraint fk_entity_contact_person foreign key (person_id) references person (person_id),
	constraint fk_entity_contact_contact_type foreign key (contact_type_id) references contact_type (contact_type_id)
);


CREATE TABLE art (
	art_id serial,
	person_id int NOT NULL,
	title varchar(100),
	base_price decimal(10,2),
	CONSTRAINT pk_art primary key (art_id)
);

CREATE TABLE purchase (
	purchase_id serial,
	art_id int not null,
	person_id int not null,
	purchase_date date not null,
	sale_price decimal(10,2),
	constraint pk_purchase primary key (purchase_id),
	constraint fk_purchase_art foreign key (art_id) references art (art_id),
	constraint fk_purchase_person foreign key (person_id) references person (person_id)
);


COMMIT;