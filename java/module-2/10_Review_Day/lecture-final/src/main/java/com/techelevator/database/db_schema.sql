
BEGIN TRANSACTION;

DROP TABLE IF EXISTS hero, monster, npc, profession, room, room_monsters, room_npcs;


CREATE TABLE IF NOT EXISTS hero
(
    hero_id serial NOT NULL,
    hero_name varchar(20) NOT NULL,
    hero_health int NOT NULL,
    hero_profession_id int NOT NULL,
    CONSTRAINT pk_hero PRIMARY KEY (hero_id)
);


CREATE TABLE IF NOT EXISTS monster
(
    monster_id int NOT NULL,
    monster_name varchar(20) NOT NULL,
    monster_health int NOT NULL,
    monster_defense int NOT NULL,
	monster_attack_description text NOT NULL,
    monster_damage int NOT NULL,
    CONSTRAINT pk_monster PRIMARY KEY (monster_id)
);

CREATE TABLE IF NOT EXISTS npc
(
    npc_id int NOT NULL,
    npc_name varchar(20) NOT NULL,
    npc_health int NOT NULL,
    npc_defense int NOT NULL,
    npc_attack int NOT NULL,
    CONSTRAINT pk_npc PRIMARY KEY (npc_id)
);

CREATE TABLE IF NOT EXISTS profession
(
    profession_id int NOT NULL,
    profession_name varchar(20) NOT NULL,
    starting_health int NOT NULL,
    starting_defense int NOT NULL,
    starting_attack int NOT NULL,
    starting_attack_description varchar(20) NOT NULL,
    CONSTRAINT pk_profession PRIMARY KEY (profession_id)
);

CREATE TABLE IF NOT EXISTS room
(
    room_id integer NOT NULL,
    room_name character varying COLLATE pg_catalog."default" NOT NULL,
    room_description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_room PRIMARY KEY (room_id)
);



CREATE TABLE IF NOT EXISTS room_monsters
(
    room_id integer NOT NULL,
    monster_id integer NOT NULL,
    CONSTRAINT pk_room_monsters PRIMARY KEY (room_id, monster_id),
    CONSTRAINT fk_room_monsters_to_room FOREIGN KEY (room_id) REFERENCES room (room_id),
	CONSTRAINT fk_room_monsters_to_monster FOREIGN KEY (monster_id) REFERENCES monster (monster_id)
);

CREATE TABLE IF NOT EXISTS room_npcs
(
    room_id integer NOT NULL,
    npc_id integer NOT NULL,
    CONSTRAINT pk_room_npcs PRIMARY KEY (room_id, npc_id),
    CONSTRAINT fk_room_npcs_to_room FOREIGN KEY (room_id) REFERENCES room (room_id),
	 CONSTRAINT fk_room_npcs_to_npc FOREIGN KEY (npc_id) REFERENCES npc (npc_id)
);

COMMIT;