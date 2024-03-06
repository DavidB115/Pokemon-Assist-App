BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, pokemon_types;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE pokemon_types (
    type_id SERIAL,
    type_name varchar(50) NOT NULL UNIQUE,
    normal_effectiveness float NOT NULL,
    fighting_effectiveness float NOT NULL,
    flying_effectiveness float NOT NULL,
    poison_effectiveness float NOT NULL,
    ground_effectiveness float NOT NULL,
    rock_effectiveness float NOT NULL,
    bug_effectiveness float NOT NULL,
    ghost_effectiveness float NOT NULL,
    steel_effectiveness float NOT NULL,
    fire_effectiveness float NOT NULL,
    water_effectiveness float NOT NULL,
    grass_effectiveness float NOT NULL,
    electric_effectiveness float NOT NULL,
    psychic_effectiveness float NOT NULL,
    ice_effectiveness float NOT NULL,
    dragon_effectiveness float NOT NULL,
    dark_effectiveness float NOT NULL,
    fairy_effectiveness float NOT NULL
);

COMMIT TRANSACTION;
