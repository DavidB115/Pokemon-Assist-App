package com.techelevator.farm;

public class OldMacdonald {
	public static void main(String[] args) {

		//FarmAnimal[] farmAnimals = new FarmAnimal[] { new Cow(), new Chicken(), new Pig() };
		Singable[] singables = new Singable[] {new Cow(), new Chicken(), new Pig(), new Tractor()};



		for (Singable singable : singables) {
			String name = singable.getName();
			String sound = singable.getSound();
			if(singable instanceof Sellable){
				if(singable.getName() == "Cow"){
					Sellable cow = (Sellable) singable;
					cow.sell();
				}
			}


			if(singable instanceof Sellable){
				Sellable animal = (Sellable) singable;
				if (animal.isSold() == true){
					System.out.println("Sold the " + animal.getName());
				}else {
					System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
					System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
					System.out.println("With a " + sound + " " + sound + " here");
					System.out.println("And a " + sound + " " + sound + " there");
					System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
					System.out.println();
				}
			}else {
				System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
				System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
				System.out.println("With a " + sound + " " + sound + " here");
				System.out.println("And a " + sound + " " + sound + " there");
				System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
				System.out.println();
			}
		}

		//Collection of Sellables
		Sellable[] sellables = new Sellable[] {new Cow(), new Pig(), new Egg()};

		for(Sellable sellable: sellables){
			System.out.println("Step right up and get your " + sellable.getName());
			System.out.println(" For the low low price of " + sellable.getPrice());
		}

	}
}