BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS images;
DROP SEQUENCE IF EXISTS seq_images_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
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



COMMIT TRANSACTION;
