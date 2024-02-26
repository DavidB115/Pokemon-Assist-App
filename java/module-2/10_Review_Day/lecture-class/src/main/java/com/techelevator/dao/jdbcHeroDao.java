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

public class jdbcHeroDao implements HeroDao{

    private final JdbcTemplate jdbcTemplate;

    public jdbcHeroDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Hero getHeroById(int id){
        Hero myHero = new Hero();
        String sql = "SELECT hero_id, hero_name, hero_health, hero_attack, hero.profession_id, profession_name, hero_defense, " +
                     "starting_health, starting_defense, starting_attack, starting_attack_description " +
                    "FROM public.hero " +
                    "INNER JOIN profession on hero.profession_id = profession.profession_id " +
                    "WHERE hero_id =?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                myHero = mapRowToHero(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to COnnect to DB Server", e);
        } catch (Exception e){
            throw new DaoException("An UnKnOwN ErrOr has OccUrrEd!", e);
        }
        return myHero;
    }
    @Override
    public Hero createNewHero(Hero hero){
        Hero createdHero = null;
        String sql = "INSERT INTO hero( hero_name, hero_health, hero_attack, profession_id, hero_defense) " +
                "VALUES ( ?, ?, ?, ?,?) RETURNING hero_id;";
        try{
            int newHeroID = jdbcTemplate.queryForObject(sql, int.class,hero.getName(), hero.getHealth(), hero.getAttack(), hero.getProfession().getProfessionId(), hero.getDefense());
            createdHero = getHeroById(newHeroID);
        }catch (CannotGetJdbcConnectionException e){
                throw new DaoException("Unable to COnnect to DB Server", e);
        }catch (DataIntegrityViolationException e){
                throw new DaoException("Data Integrity Violation", e);
        }catch (Exception e){
            throw new DaoException("An UnKnOwN ErrOr has OccUrrEd!", e);
        }

        return createdHero;

    }
    @Override
    public Hero updateHero (Hero hero){
        Hero updatedHero = null;
        String sql = "UPDATE public.hero " +
                     "SET hero_name=?, hero_health=?, hero_attack=?, profession_id=?, hero_defense=? " +
                     "WHERE hero_id =?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql, hero.getName(), hero.getHealth(), hero.getAttack(), hero.getProfession().getProfessionId(), hero.getDefense(), hero.getId());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to COnnect to DB Server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }catch (Exception e){
            throw new DaoException("An UnKnOwN ErrOr has OccUrrEd!", e);
        }

        return updatedHero;
    }
    @Override
    public List<Profession> getListOfProfessions(){
        List<Profession> professions = new ArrayList<>();
        String sql = "SELECT profession_id, profession_name, starting_health, starting_defense, starting_attack, starting_attack_description " +
                "FROM profession;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Profession profession = mapRowToProfession(results);
                professions.add(profession);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to COnnect to DB Server", e);
        }catch (DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }catch (Exception e){
            throw new DaoException("An UnKnOwN ErrOr has OccUrrEd!", e);
        }

        return professions;
    }


    private Hero mapRowToHero(SqlRowSet results){
        Hero myHero = new Hero();
        myHero.setId(results.getInt("hero_id"));
        myHero.setName(results.getString("hero_name"));
        myHero.setHealth(results.getInt("hero_health"));
        myHero.setAttack(results.getInt("hero_attack"));
        myHero.setDefense(results.getInt("hero_defense"));
        myHero.setProfession(mapRowToProfession(results));
        return myHero;
    }

    public Profession mapRowToProfession(SqlRowSet results){
        Profession profession = new Profession();
        profession.setProfessionId(results.getInt("profession_id"));
        profession.setProfessionName(results.getString("profession_name"));
        profession.setStartingHealth(results.getInt("starting_health"));
        profession.setStartingAttack(results.getInt("starting_attack"));
        profession.setStartingAttackDescription(results.getString("starting_attack_description"));
        profession.setStartingDefense(results.getInt("starting_defense"));
        return profession;
    }
}
