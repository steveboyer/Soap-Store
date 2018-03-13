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

    // To json string, pretty print enabled
    public static String toString(Object object){
        return gson().toJson(object);
    }
}
