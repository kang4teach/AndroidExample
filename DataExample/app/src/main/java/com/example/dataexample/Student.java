package com.example.dataexample;

import com.google.gson.Gson;

public class Student {
    public String name;
    public int age;
    private static Gson gson;
    public static Student fromJson(String jsonStr){
        gson = getGson();
        return gson.fromJson(jsonStr,Student.class);
    }

    public static Gson getGson() {
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public String toJson(){
        gson = getGson();
        return gson.toJson(this);
    }
}
