package com.techelevator;

import java.util.*;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import com.techelevator.view.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class MainProgram {

    //Collection of Room Objects
    static Map<Integer, Room> rooms = new HashMap<>();

    //Collection of Monster Objects
    static Map<Integer, Monster> monsters = new HashMap<>();
    //Main hero Object
    static Hero myHero;
    //Cletus
    static Villager cletus;
    static RoomDao roomDao;
    static MonsterDao monsterDao;
    static HeroDao heroDao;
    static ConsoleService console = new ConsoleService();
    static BasicDataSource dataSource = new BasicDataSource();
    public static void main(String[] args) {
        boolean gameOver = false; //Used to control tha main game loop
        int currentRoom = 1; //Used to track the current room

        //Initialize The Data Source
        setUpDataSource();

        //Initialize the Dungeon Rooms
        generateRooms();
        //Initialize the Monsters
        generateMonsters();

        //Print Welcome Messages
        console.printWelcomeMessage();
        //Hero Creation Prompts
        myHero = console.inputHeroOptions(getProfessionList());
        //Save the Hero to the DB
        myHero = heroDao.createNewHero(myHero);
        //Print out the Mission Briefing
        console.printMissionMessage();

        //Begin the game
        while (!gameOver){
            //Ensure the Room Counter Does not exceed 4 rooms
            if (currentRoom > 4){
                currentRoom = 1;
            }
            //This is the Current Room (Based on the Integer Key of the Map)
            Room thisRoom = rooms.get(currentRoom);
            //This is the Current Monster (Based on the Integer Key of the Map)
            Monster monster = monsters.get(currentRoom);
            //Print out the Room Description
            console.printRoomDescription(thisRoom);

            //If there is a currently a monster
            if(monster != null){
                //Print out the Monster Description
                console.printMonsterDescription(monster);
                //Prompt for The Hero's Combat Action and resolve the hero's turn in the switch
                switch (console.inputCombatOptions()){
                    case 1: //Attack
                        console.printHeroAttack(monster, myHero);
                        break; //Break the Switch
                    case 2: //Run Away
                        System.out.println("You run away in fear!");
                        currentRoom++; //Advance to the Next Room
                        if(currentRoom == 5){
                            System.out.println("You Left Cletus!");
                            console.printGameOverMessage();
                            gameOver = true;//Game over switch
                        }
                        continue; // Go Back to the top of the loop

                    case 3:
                        System.out.println("You Surrendered.");
                        console.printGameOverMessage(); //Let them know the game is over
                        gameOver = true;//Game over switch
                        continue;// Go Back to the top of the loop (But not for long..The condition is flipped)
                    case 4:
                        System.out.println("You Try Tap Dancing");
                        console.printMonsterAttack(monster);
                        console.printCharacterDeathMessage();
                        console.printGameOverMessage();
                        gameOver = true;
                        continue;
                }
                //Print out the Monster Attack
                console.printMonsterAttack(monster);
                myHero.setHealth(myHero.getHealth() - monster.getAttack());
                console.printCharacterDeathMessage();
                console.printGameOverMessage();
                gameOver = true;
                myHero = heroDao.updateHero(myHero);

            }else {
                switch (console.inputExplorationOptions()) {
                    case 1: //Stay There
                        break; //Break the Switch
                    case 2: //Leave the Room
                        System.out.println("You move on to the next room!");
                        currentRoom++; //Advance to the NExt Room
                        continue; // Go Back to the top of the loop
                    case 3:
                        System.out.println("You Surrendered.");
                        console.printGameOverMessage(); //Let them know the game is over
                        gameOver = true;//Game over switch
                }
            }
        }
    }


    //Generator Methods
    public static void generateRooms(){
        //Initialize The Rooms
        roomDao = new jdbRoomDao(dataSource);
        rooms = roomDao.getMapOfRooms();
    }

    public static void generateMonsters(){
        //Initialize The Rooms
        monsterDao = new jdbcMonsterDao(dataSource);
        monsters = monsterDao.getMapOfMonsters();
    }

    public static List<Profession> getProfessionList(){
        heroDao = new jdbcHeroDao(dataSource);
        return heroDao.getListOfProfessions();
    }
    private static void setUpDataSource(){
        dataSource.setUrl("jdbc:postgresql://localhost:5432/useless_dungeon");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
    }
}