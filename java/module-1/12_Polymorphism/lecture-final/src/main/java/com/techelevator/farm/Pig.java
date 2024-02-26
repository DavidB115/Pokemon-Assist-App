package com.techelevator.farm;

import java.math.BigDecimal;

public class Pig extends FarmAnimal implements Sellable {

    private BigDecimal price;
    private boolean sold = false;
    public Pig(){
        super("Pig", "oink!");
        price = new BigDecimal("300.00");
    }

    public BigDecimal getPrice(){
        return price;
    }
    public boolean isSold(){
        return sold;
    }
    @Override
    public void sell() {
        sold = true;
    }
}
