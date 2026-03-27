package com.neuronod.backend;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public static List<CircadianData> parseHeartRateJson(String jsonResponse) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CircadianData>>(){}.getType();
        return gson.fromJson(jsonResponse, listType);
    }
}