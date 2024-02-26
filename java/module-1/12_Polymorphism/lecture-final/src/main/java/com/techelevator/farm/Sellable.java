package com.techelevator.farm;

import java.math.BigDecimal;

public interface Sellable {

    String getName();
    BigDecimal getPrice();

    void sell();

    public boolean isSold();

}
