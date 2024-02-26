BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, landmarks, itineraries, itinerary_landmarks, images;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE landmarks (
    landmark_id SERIAL,
    landmark_name varchar(100) NOT NULL,
    landmark_address varchar(100) NOT NULL,
    landmark_details varchar(1500),
    landmark_longitude varchar(25),
    landmark_latitude varchar(25),
    CONSTRAINT PK_landmark PRIMARY KEY (landmark_id)
);

CREATE TABLE itineraries (
    itinerary_id SERIAL,
    user_id int NOT NULL,
    itinerary_name varchar(50) NOT NULL,
    starting_address varchar(100) NOT NULL,
    CONSTRAINT PK_itinerary PRIMARY KEY (itinerary_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) references users (user_id)
);

CREATE TABLE itinerary_landmarks (
    itinerary_id int NOT NULL,
    landmark_id int NOT NULL,
    CONSTRAINT FK_itinerary_id FOREIGN KEY (itinerary_id) REFERENCES itineraries (itinerary_id),
    CONSTRAINT FK_landmark_id FOREIGN KEY (landmark_id) REFERENCES landmarks (landmark_id)
);


CREATE SEQUENCE seq_images_id
	INCREMENT BY 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;

CREATE TABLE images (
	image_id int DEFAULT nextval('seq_images_id'::regclass) NOT NULL,
	user_id int,
	mediatype varchar(100) NULL,
	data BYTEA NULL,
	CONSTRAINT PK_images PRIMARY KEY (image_id),
	CONSTRAINT FK_images_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);



--TODO: Create remaining tables for application (images, reviews)

COMMIT TRANSACTION;
