package com.techelevator.dndapi;

import com.techelevator.dndapi.model.*;
import com.techelevator.dndapi.services.ClassService;
import com.techelevator.dndapi.services.ConsoleService;
import com.techelevator.dndapi.services.MonsterService;

public class App {
    private final ConsoleService consoleService = new ConsoleService();
    private final ClassService classService = new ClassService();
    private final MonsterService monsterService = new MonsterService();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection();
            if (menuSelection == 1) {
                handleListAllClasses();
            } else if (menuSelection == 2) {
                handleFindCharacterClassById();
            } else if (menuSelection == 3) {
                handleListAllMonsters();
            } else if (menuSelection == 4) {
                handleFindMonsterById();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
            }
        }
    }

    private void handleListAllClasses(){
        try {
            Root classList = classService.getListOfClasses();
            consoleService.printClassList(classList);
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned Incorrect Object ");
        }
        catch (NullPointerException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned NUll Class List.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleListAllMonsters(){
        try {
            Root monsterList = monsterService.getListOfMonsters();
            consoleService.printMonsterList(monsterList);
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve Monsters from API. Service Returned Incorrect Object ");
        }
        catch (NullPointerException e){
            System.out.println("Unable to retrieve Monsters from API. Service Returned NUll Monster List.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void handleFindCharacterClassById() {
        try {
            handleListAllClasses();
            String id = consoleService.promptForCharacterClass();
            if (id != null) {
                CharacterClass characterClass = classService.getCharacterClassById(getCharacterClassUrlFromId(id));
                consoleService.printCharacterClassDetails(characterClass);
            }
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned Incorrect Object ");
        }
        catch (NullPointerException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned NUll Character Class.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getCharacterClassUrlFromId(String id){
        try {
            Root classList = classService.getListOfClasses();
            Result selectedClass = classList.getResults().get(Integer.parseInt(id) - 1);
            return selectedClass.getIndex();
        } catch (NumberFormatException e){
            System.out.println("Unable to retrieve Specific Character Class from ID: Id returned was not numeric.");
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned Incorrect Object ");
        } catch (NullPointerException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned NUll Character Class.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private void handleFindMonsterById() {
        try {
            handleListAllMonsters();
            String id = consoleService.promptForMonster();
            if (id != null) {
                Monster monster = monsterService.getMonsterById(getMonsterUrlFromId(id));
                consoleService.printMonstersDetails(monster);
            }
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned Incorrect Object ");
        }
        catch (NullPointerException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned NUll Character Class.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getMonsterUrlFromId(String id){
        try {
            Root classList = monsterService.getListOfMonsters();
            Result selectedMonster = classList.getResults().get(Integer.parseInt(id)-1);
            return selectedMonster.getIndex();
        }  catch (NumberFormatException e){
            System.out.println("Unable to retrieve Specific Character Class from ID: Id returned was not numeric.");
        } catch (ClassCastException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned Incorrect Object ");
        } catch (NullPointerException e){
            System.out.println("Unable to retrieve character classes from API. Service Returned NUll Character Class.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";

    }


}
