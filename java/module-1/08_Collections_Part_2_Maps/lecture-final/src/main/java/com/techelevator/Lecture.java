package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

		//New Map
		Map<String, String> nameToZip = new HashMap<>();

		//And an item to the map
		nameToZip.put("Anthony", "18031");
		nameToZip.put("Dan", "44124");
		nameToZip.put("Larry", "44012");

		//Retrieve Items from a map
		String name = "Anthony";
		System.out.println("Anthony lives in: " + nameToZip.get(name));
		System.out.println("Dan lives in: " + nameToZip.get("Dan"));
		System.out.println("Larry lives in: " + nameToZip.get("Larry"));

		//Get key by value
		System.out.println("*******************************************************");
		System.out.println("Looping through keys with a set");

		Set<String> keys = nameToZip.keySet();
		for (String key : keys){
			System.out.println(key + " lives in " + nameToZip.get(key));
		}

		System.out.println("*******************************************************");
		System.out.println("Change Anthony's Zip Code");
		nameToZip.put("Anthony", "19148");

		for(Map.Entry<String, String> nameZip : nameToZip.entrySet()){
			System.out.println(nameZip.getKey() + " lives in: " + nameZip.getValue());
		}
		System.out.println("*******************************************************");


		nameToZip.remove("Dan");
		nameToZip.put("Joe", "19148");

		for(Map.Entry<String, String> nameZip : nameToZip.entrySet()){
			if(nameZip.getValue() == "19148"){
				System.out.println(nameZip.getKey() + "  Wants to move ");
				nameToZip.put(nameZip.getKey(), "18031");
			}
		}
		System.out.println("*******************************************************");
		for(Map.Entry<String, String> nameZip : nameToZip.entrySet()){
			System.out.println(nameZip.getKey() + " lives in: " + nameZip.getValue());
		}
		System.out.println("*******************************************************");
	}

}
