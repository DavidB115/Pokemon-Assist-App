--DB SCHEMA STATEMENTS GO HERE
BEGIN TRANSACTION;
	DROP TABLE IF EXISTS hero, profession, monster, room;


	CREATE TABLE IF NOT EXISTS profession (
		profession_id int NOT NULL,
		profession_name varchar(20) NOT NULL,
		starting_health int NOT NULL,
		starting_defense int NOT NULL,
		starting_attack int NOT NULL,
		starting_attack_description varchar(20) NOT NULL,
		CONSTRAINT pk_profession PRIMARY KEY (profession_id)
	);

	
	CREATE TABLE IF NOT EXISTS hero (
		hero_id serial NOT NULL,
		hero_name varchar(20) NOT NULL,
		hero_health int NOT NULL,
		hero_attack int NOT NULL, 
		profession_id int NOT NULL,
		CONSTRAINT pk_hero PRIMARY KEY(hero_id),
		CONSTRAINT fk_hero_profession FOREIGN KEY (profession_id) REFERENCES profession (profession_id)
			
	);
	
	CREATE TABLE IF NOT EXISTS monster (
		monster_id int NOT NULL,
		monster_name varchar(20) NOT NULL,
		monster_health int NOT NULL,
		monster_defense int NOT NULL,
		monster_attack_description text NOT NULL,
		monster_damage int NOT NULL,
		CONSTRAINT pk_monster PRIMARY KEY (monster_id)
	);
	
	CREATE TABLE IF NOT EXISTS room (
		room_id int NOT NULL,
		room_name varchar(20) NOT NULL,
		room_description text NOT NULL,
		CONSTRAINT pk_room PRIMARY KEY (room_id)
	);
	
COMMIT;

