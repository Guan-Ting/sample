package com.java.sample.java8.collection;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JSONArrayTest2 {
    public static void main(String[] args) throws Exception{
        String json = "[{\"grantServiceId\":\"f7e7c4a5-e5db-4717-8a3b-583c6a469018\",\"seq\":7},{\"grantServiceId\":\"41c6a92d-4d63-4040-9d4c-1b9b0c3b7f83\",\"seq\":6},{\"grantServiceId\":\"2361116b-5ef6-4658-ae49-4867bcccc466\",\"seq\":5},{\"grantServiceId\":\"81090e17-477b-48c8-81e1-2e1ad7b67a4a\",\"seq\":4},{\"grantServiceId\":\"39b2f17c-e891-419f-856a-8eb92ce46e71\",\"seq\":3},{\"grantServiceId\":\"adc416ac-d14e-44f0-8d15-0ee35f4789fd\",\"seq\":2},{\"grantServiceId\":\"34d53513-e929-41a2-bfed-0495322a1989\",\"seq\":1}]";
        JSONArray jsonArray = new JSONArray(json);
        JSONObject j1=(JSONObject) jsonArray.get(0);
        j1.remove("seq");
        System.out.println(j1.toString());
        j1.put("seq",6);
        System.out.println(j1.toString());



//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            System.out.println(jsonObject);
//            String id=jsonObject.get("grantServiceId").toString();
//            System.out.println(id);
//        }
    }
}

