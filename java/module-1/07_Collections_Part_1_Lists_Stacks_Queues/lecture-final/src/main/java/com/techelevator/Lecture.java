package com.techelevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

		List<String> names = new ArrayList<String>();
		names.add("Frodo");
		names.add("Sam");
		names.add("Pippin");
		names.add("Merry");
		names.add(0,"Gandalf");
		names.add("Aragorn");
		names.add("Boromir");
		names.add("Gimli");
		names.add("Legolas");



		//System.out.println(names.get(0));

		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		for (int i =0; i < names.size(); i++){
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");
		names.add("Sam");

		for (String name : names ) {
			System.out.println(name);
		}

		System.out.println("####################");
		System.out.println("Stop when we get to Merry");
		System.out.println("####################");

		for (String name : names ) {
			System.out.println(name);
			if (name == "Merry"){
				System.out.println(" I Found Merry!");
				break;
			}
		}


		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		names.add(2, "Anthony");
		for(String name : names){
			System.out.println(name);
		}


		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");
		names.remove(2);
		names.remove(names.size() -1);

		for(String name : names){
			System.out.println(name);
		}



		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
		boolean nameInList = false;
		nameInList = names.contains("Sam");

		System.out.println("The fellowship is at 100% strength = " + nameInList);

		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		int indexOfGandalf = names.indexOf("Gandalf");
		System.out.println("Where is Gandalf? He is at: " + indexOfGandalf);

		System.out.println("####################");
		System.out.println("Remove By IndexOf");
		System.out.println("####################");

		//These do the same thing
		names.remove(names.indexOf("Sam"));
		names.remove("Sam");
		for(String name : names){
			System.out.println(name);
		}
		System.out.println("The Fellowship is at 1%");


		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");
		String[] namesArray = names.toArray(new String[names.size()]);
		//For loop through array
		for (int i = 0; i < namesArray.length; i++){
			System.out.println(namesArray[i]);
		}
		System.out.println("For Each Through Array");
		//For Each
		for (String name : namesArray) {
			System.out.println(name);
		}

		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");
		Collections.sort(names);
		for(String name : names){
			System.out.println(name);
		}

		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");
		Collections.reverse(names);
		for(String name : names){
			System.out.println(name);
		}

		System.out.println("####################");
		System.out.println("      Copy List and Sort Copy");
		System.out.println("####################");
		System.out.println();
		//Real Copy
		//List<String> copyOfNames = new ArrayList<>(names);

		//Address Copy
		List<String> copyOfNames = names;

		Collections.sort(copyOfNames);
		for(String name : names){
			System.out.println(name);
		}

	}
}
