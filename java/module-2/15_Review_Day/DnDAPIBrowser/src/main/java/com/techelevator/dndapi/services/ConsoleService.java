package com.techelevator.dndapi.services;

import java.util.Scanner;

import com.techelevator.dndapi.model.*;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection() {
        int menuSelection;
        System.out.print("Please choose an option: ");
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("----Dungeons and Dragons Open API---");
        System.out.println("1: List all Character Classes");
        System.out.println("2: List details for a specific Character Class");
        System.out.println("3: List all Character Monsters");
        System.out.println("4: List details for a specific Monster");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printClassList(Root classList) {
        int classID = 0;
        if (classList != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Character Classes");
            System.out.println("--------------------------------------------");
            for (Result index : classList.getResults())
            {
                classID++;
                System.out.println(classID + "--" +index.getName());
            }
        }
    }

    public void printMonsterList(Root monsterList) {
        int monsterID=0;
        if (monsterList != null) {
            System.out.println("--------------------------------------------");
            System.out.println("Monsters");
            System.out.println("--------------------------------------------");
            for (Result index : monsterList.getResults())
            {
                monsterID++;
                System.out.println(monsterID + " -- " + index.getName());
            }
        }
    }

    public void printCharacterClassDetails(CharacterClass characterClass){
        if (characterClass != null){
            System.out.println("Character Class: " + characterClass.getName());
            System.out.println("Hit Die: " + characterClass.getHit_die());
        }
    }

    public String promptForCharacterClass() {
        System.out.println("--------------------------------------------");
        System.out.print("Enter the id of the Character Class you would like to view: ");
        return scanner.nextLine();
    }

    public void printMonstersDetails(Monster monster){
        if (monster != null){
            System.out.println("Monster Name: " + monster.getName());
            System.out.println("Alignment: " + monster.getAlignment());
            System.out.println("Hit Points" + monster.getHit_points());
        }
    }

    public String promptForMonster() {
        System.out.println("--------------------------------------------");
        System.out.print("Enter the id of the Monster you would like to view: ");
        return scanner.nextLine();
    }

}
