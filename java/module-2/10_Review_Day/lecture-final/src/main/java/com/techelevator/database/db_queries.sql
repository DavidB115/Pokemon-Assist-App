--Collection of Rooms for loading:
SELECT room_id, room_name, room_description
FROM room;

--Collection Monsters By Room ID for Loading
SELECT room_monsters.room_id, monster.monster_id, monster_name, monster_health, monster_defense, monster_attack_description, monster_damage
FROM monster
INNER JOIN room_monsters on monster.monster_id = room_monsters.monster_id;


--Collection NPCS By Room ID for Loading
SELECT room_npcs.room_id, npc.npc_id, npc_name, npc_health, npc_defense, npc_attack
FROM npc
INNER JOIN room_npcs on npc.npc_id = room_npcs.npc_id;

--Collection of Professions for Loading
SELECT profession_id, profession_name, starting_health, starting_defense, starting_attack
FROM profession;
