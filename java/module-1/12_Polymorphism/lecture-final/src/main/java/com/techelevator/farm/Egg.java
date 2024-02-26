package com.techelevator.farm;

import java.math.BigDecimal;

public class Egg implements Sellable {
    private String name;
    private BigDecimal price;

    private boolean sold = false;

    public Egg(){
        name = "Egg";
        price = new BigDecimal("0.25");
    }

    public String getName(){
        return name;
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
