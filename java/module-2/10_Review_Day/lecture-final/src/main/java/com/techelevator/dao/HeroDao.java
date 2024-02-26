package com.techelevator.dao;

import com.techelevator.model.Hero;
import com.techelevator.model.Profession;

import java.util.List;

public interface HeroDao {

    public Hero getHeroById(int id);
    public Hero createNewHero (Hero hero);
    public Hero updateHero(Hero hero);
    public List<Profession> getListOfProfessions ();

}
