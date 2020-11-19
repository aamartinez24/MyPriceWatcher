package edu.utep.cs.cs5390.mypricewatcher;

import java.util.concurrent.ThreadLocalRandom;

// Airam Martinez & Mildred Brito
public class PriceFinder {

    private double itemPrice;

    public static boolean validateUrl(String newItemUrl) {
        if (newItemUrl.contains("homedepot") || newItemUrl.contains("lowes"))
            return true;
        return false;
    }

    public static double findPrice(String url) {
        return ThreadLocalRandom.current().nextDouble(1, 100);
    }
}