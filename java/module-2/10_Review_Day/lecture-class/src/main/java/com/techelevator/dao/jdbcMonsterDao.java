package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Monster;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class jdbcMonsterDao implements MonsterDao{

    private final JdbcTemplate jdbcTemplate;
    public jdbcMonsterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Monster getMonsterById(int id){
        Monster monster = new Monster();
        String sql = "SELECT monster_id, monster_name, monster_health, monster_defense, monster_attack_description, monster_damage " +
                "FROM monster " +
                "WHERE monster_id=?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                monster = mapRowToMonster(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return monster;
    }

    @Override
    public Map<Integer, Monster> getMapOfMonsters() {
        Map<Integer, Monster> monsters = new HashMap<>();
        String sql = "SELECT monster_id, monster_name, monster_health, monster_defense, monster_attack_description, monster_damage " +
                "FROM monster;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Monster monster =  mapRowToMonster(results);
                monsters.put(monster.getId() + 1, monster);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return monsters;
    }

    public Monster mapRowToMonster(SqlRowSet rowSet){
        Monster monster = new Monster();
        //monster_id, monster_name, monster_health, monster_defense, monster_attack_description, monster_damage
        monster.setId(rowSet.getInt("monster_id"));
        monster.setName(rowSet.getString("monster_name"));
        monster.setHealth(rowSet.getInt("monster_health"));
        monster.setDefense(rowSet.getInt("monster_defense"));
        monster.setAttack(rowSet.getInt("monster_damage"));
        monster.setAttackDescription(rowSet.getString("monster_attack_description"));
        return monster;
    }
}
