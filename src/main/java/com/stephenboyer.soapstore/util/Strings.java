package com.stephenboyer.soapstore.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Strings {
    // Gson/json builder thing to keep instantiated so we can avoid doing it all the time
    private static Gson gson;

    // Get the gson/json builder thing
    public static Gson gson(){
        if(gson == null) {
            // pretty printing i.e. \n & \t used
            gson = new GsonBuilder().setPrettyPrinting().create();
        }

        return gson;
    }

    public static String getPriceString(Long price){
        String sPrice = Long.toString(price);

        // No price data
        if(sPrice.isEmpty()){
            return "$0.00";
        }

        // Price < $0.10
        if(sPrice.length() == 1){
            return "$0.0" + sPrice;
        }

        // $0.10 < Price < $1.00
        if(sPrice.length() == 2){
            return "$0." + sPrice;
        }

        // Price > $1.00 or no data
        try {
            return "$" + sPrice.substring(0, sPrice.length() - 2) + "." + sPrice.substring(sPrice.length() - 2);
        } catch (NullPointerException ex){
            return "$0.00";
        }
    }

    // To json string, pretty print enabled
    public static String toString(Object object){
        return gson().toJson(object);
    }
}
