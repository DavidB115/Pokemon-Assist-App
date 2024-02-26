package com.techelevator.dao;
import com.techelevator.model.Monster;

import java.util.Map;


public interface MonsterDao {
    public Monster getMonsterById(int id);
    public Map<Integer, Monster> getMapOfMonsters();

}
