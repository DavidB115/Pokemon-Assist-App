package com.techelevator;

import java.util.*;

public class MainProgram {

	//Collection of Room Objects
	static Map<Integer, Room> rooms = new HashMap<>();
	//Collection of Monster Objects
	static Map<Integer, Monster> monsters = new HashMap<>();
	//Main hero Object
	static Hero myHero;
	//Cletus
	static Villager cletus;

	static ConsoleService console = new ConsoleService();
	public static void main(String[] args) {
		boolean gameOver = false; //Used to control tha main game loop
		int currentRoom = 1; //Used to track the current room

		//Initialize The Rooms
		generateRooms();
		//Initialize the Monsters
		generateMonsters();

		//Print Welcome Messages
		console.printWelcomeMessage();
		//Hero Creation Prompts
		myHero = console.inputHeroOptions();
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
								System.out.println("Game Over");
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
							System.out.println(monster.getName() + " attacks with " + monster.getAttackDescription());
							System.out.println("You take " + monster.getDamage() + " points of damage");
							System.out.println("You Are Dead.");
							System.out.println("Game Over.");
							break;
				}
				//Print out the Monster Attack
				console.printMonsterAttack(monster);
				gameOver = true;

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
		rooms.put(1, new Room("This 10 foot by 20 foot entry hall has stone walls with a Door on the east side leading to another room."));
		rooms.put(2, new Room("This 20 foot by 30 foot dinning area has Velvet Elvis Paintings with a Door on the south wall leading to another room."));
		rooms.put(3, new Room("This 40 foot by 50 foot dance studio has Mirrored Walls with a Door on the west wall leading to another room."));
		rooms.put(4, new Room("This 20 foot by 20 foot arcade has posters of Metallica  with a Door on the north wall leading to another room. Cletus is gently restrained in the comfy chair."));
	}

	public static void generateMonsters(){
		monsters.put(2, new Monster("Velociraptor", 100, 100, "Tube Launched Optically Tracked Wire Guided Anti Personnel Missile ",500));
		monsters.put(3, new Monster("Darth Vader", 500, 100, "Laser Sword ",5000));
		monsters.put(4, new Monster("The Gelatinous Cube", 1000, 100, "Gelatinous Envelopment. Yuk!",10000));
	}

}