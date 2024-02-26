package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Hero;
import com.techelevator.model.Profession;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class jdbcHeroDao implements HeroDao {
    private final JdbcTemplate jdbcTemplate;
    public jdbcHeroDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
@   Override
    public Hero getHeroById(int id){
        Hero myHero = new Hero();
        String sql = "SELECT hero_id, hero_name, hero_health, hero_defense, profession_id, profession_name, starting_health, starting_defense, starting_attack " +
                     "FROM hero " +
                     "INNER JOIN profession ON hero.hero_profession_id = profession.profession_id " +
                     "WHERE hero_id=?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                myHero = mapRowToHero(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return myHero;
    }

    @Override
    public Hero createNewHero (Hero hero){
        Hero newHero = null;
        String sql = "INSERT INTO hero(hero_name, hero_health, hero_defense, hero_profession_id) " +
                     "VALUES (?, ?, ?, ?) RETURNING hero_id;";
        try {
            int newHeroID = jdbcTemplate.queryForObject(sql, int.class,
                    hero.getName(), hero.getProfession().getStartingHealth(), hero.getProfession().getStartingDefense(),
                    hero.getProfession().getProfessionId());
            newHero = getHeroById(newHeroID);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newHero;
    }

    @Override
    public Hero updateHero(Hero hero) {
        Hero updatedHero = null;

        String sql = "UPDATE hero SET hero_name = ?, " +
                "hero_health = ?, hero_defense = ?, hero_profession_id = ? WHERE hero_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, hero.getName(), hero.getHealth(),
                    hero.getDefense(), hero.getProfession().getProfessionId(), hero.getId());

            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedHero = getHeroById(hero.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedHero;
    }

    @Override
    public List<Profession> getListOfProfessions (){
        List<Profession> professions = new ArrayList<>();
        String sql = "SELECT profession_id, profession_name, starting_health, starting_defense, starting_attack " +
                "FROM public.profession;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
               Profession profession = mapRowToProfession(results);
                professions.add(profession);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return professions;
    }


    public Hero mapRowToHero(SqlRowSet rowSet){
        Hero myHero = new Hero();
        Profession profession = new Profession();
        myHero.setId(rowSet.getInt("hero_id"));
        myHero.setName(rowSet.getString("hero_name"));
        myHero.setHealth(rowSet.getInt("hero_health"));
        myHero.setDefense(rowSet.getInt("hero_defense"));
        profession.setProfessionId(rowSet.getInt("profession_id"));
        profession.setStartingAttack(rowSet.getInt("starting_attack"));
        profession.setStartingDefense(rowSet.getInt("starting_health"));
        profession.setStartingHealth(rowSet.getInt("starting_defense"));
        profession.setProfessionName(rowSet.getString("profession_name"));
        myHero.setProfession(profession);
        myHero.setAttack(profession.getStartingAttack());
        return myHero;
    }
    public Profession mapRowToProfession(SqlRowSet rowSet){
       Profession profession = new Profession();
        profession.setProfessionId(rowSet.getInt("profession_id"));
        profession.setProfessionName(rowSet.getString("profession_name"));
        profession.setStartingHealth(rowSet.getInt("starting_health"));
        profession.setStartingDefense(rowSet.getInt("starting_defense"));
        profession.setStartingAttack(rowSet.getInt("starting_attack"));
        return profession;
    }

}
