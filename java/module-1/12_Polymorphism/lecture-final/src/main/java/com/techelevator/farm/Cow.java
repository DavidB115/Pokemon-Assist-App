package com.techelevator.farm;

import java.math.BigDecimal;

public class Cow extends FarmAnimal implements Sellable {

	private BigDecimal price;
	private boolean sold = false;
	public Cow() {
		super("Cow", "moo!");
		price = new BigDecimal("1500.00");
	}

	public BigDecimal getPrice(){
		return price;
	}

	public boolean isSold(){
		return sold;
	}

	public void sell() {
		sold = true;
	}
}