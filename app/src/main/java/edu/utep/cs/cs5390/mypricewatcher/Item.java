package edu.utep.cs.cs5390.mypricewatcher;

// Airam Martinez
public class Item {

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

    public void setInitalPrice(double initalPrice){
        this.initalPrice = initalPrice;
    }

    public String getItemName(){
        return this.name;
    }

    public double getInitalPrice(){
        return this.initalPrice;
    }

    public String getUrl(){
        return this.url;
    }
}
