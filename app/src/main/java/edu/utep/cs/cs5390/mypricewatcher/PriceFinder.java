package edu.utep.cs.cs5390.mypricewatcher;

import java.util.concurrent.ThreadLocalRandom;

// Airam Martinez
public class PriceFinder {

    private Item item;
    private double currentPrice;

    public PriceFinder(Item item){
        this.item = item;
    }

    public double findCurrPrice(){
        // Generate random number.
        double currPrice = ThreadLocalRandom.current().nextDouble(10, item.getInitalPrice());
        // Make double have two decimal places.
        this.currentPrice = Math.round(currPrice * 100.0) / 100.0;
        return this.currentPrice;
    }

    public double getPercentageChange(){
        // Calculate percentage difference.
        double difference = currentPrice - item.getInitalPrice();
        difference = difference/item.getInitalPrice();
        difference = difference*100;
        difference = Math.round(difference * 100.0) / 100.0;
        return difference;
    }

    public Item getItem(){
        return this.item;
    }

}