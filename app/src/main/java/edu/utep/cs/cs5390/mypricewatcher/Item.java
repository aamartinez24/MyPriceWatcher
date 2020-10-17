package edu.utep.cs.cs5390.mypricewatcher;

import java.util.ArrayList;
import java.util.List;

// Airam Martinez & Mildred Brito
public class Item {

    private static List<Item> allItems = new ArrayList<>();

    private String name;
    private double initalPrice;
    private String url;

    public Item(String name, String url){
        this.name = name;
        this.url = url;
    }

    public Item(String name, double initalPrice, String url){
        this.name = name;
        this.initalPrice = initalPrice;
        this.url = url;
    }

    public void setName(String itemName) { this.name = itemName; }

    public void setInitalPrice(double initalPrice){
        this.initalPrice = initalPrice;
    }

    public void setUrl(String itemURL) { this.url = itemURL; }

    public String getItemName(){
        return this.name;
    }

    public double getInitalPrice(){
        return this.initalPrice;
    }

    public String getUrl(){
        return this.url;
    }

    public static List<Item> allItems() {
        return allItems;
    }
}
