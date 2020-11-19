package edu.utep.cs.cs5390.mypricewatcher;

import java.io.Serializable;

// Airam Martinez & Mildred Brito
// Class to represent a shopping item.
public class Item implements Serializable {

    private int id;
    private String name;
    private double initialPrice;
    private double currentPrice;
    private String url;
    private double percentageChange;

    public Item(String name, String url) {
        this.name = name;
        this.url = url;
        // Finds the initial price of the item by getting it from the url.
        // TODO fix PriceFinder
        this.initialPrice = PriceFinder.findPrice(url);
        this.currentPrice = initialPrice;
        this.percentageChange = calculatePercentageDiff();
    }

    public Item(String name, double initialPrice, String url) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = PriceFinder.findPrice(url);
        this.url = url;
        this.percentageChange = calculatePercentageDiff();
    }

    public Item(String name, double initialPrice, double currentPrice, String url) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.url = url;
        this.percentageChange = calculatePercentageDiff();
    }

    public Item(int id, String name, double initialPrice, double currentPrice, String url) {
        this.id = id;
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.url = url;
        this.percentageChange = calculatePercentageDiff();
    }

    // Method to calculate the percentage difference between the initial
    // and the current price of the item.
    public double calculatePercentageDiff() {
        double difference = currentPrice - initialPrice;
        difference = difference/initialPrice;
        difference = difference*100;
        difference = Math.round(difference * 100.0) / 100.0;
        return difference;
    }

    // Method to get the current price of the item by using the PriceFinder class.
    // TODO fix PriceFinder
    public void findCurrentPrice() {
        double priceFound = PriceFinder.findPrice(url);
        if(priceFound >= 0) {
            this.currentPrice = priceFound;
            setPercentageChange();
        }
    }

    // Setters and Getters.
    public void setId(int id) { this.id = id; }

    public void setName(String itemName) { this.name = itemName; }

    public void setInitialPrice(double initialPrice) { this.initialPrice = initialPrice; }

    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public void setUrl(String url) { this.url = url; }

    public void setPercentageChange() { this.percentageChange = calculatePercentageDiff(); }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public double getInitialPrice() { return this.initialPrice; }

    public double getCurrentPrice() { return this.currentPrice; }

    public String getUrl() { return this.url; }

    public double getPercentageChange() { return this.percentageChange;}

}
