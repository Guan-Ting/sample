package com.java.sample.java8.collection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSONArrayTest {
    public static void main(String[] args) {
        JSONArray pets = new JSONArray();
        String strMain = "姓名,表號,水號";
        String[] arrSplit = strMain.trim().split(",");
        for (int i = 0; i < arrSplit.length; i++) {
            pets.put(arrSplit[i]);
        }
        System.out.println(pets.toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("123",pets);
        System.out.println(jsonObject.toString());
    }
}
