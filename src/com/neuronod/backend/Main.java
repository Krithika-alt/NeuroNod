package com.neuronod.backend;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String mockJson = "[" +
                "{\"time\": 1711519200000, \"heartRate\": 75.0, \"brainFog\": 2}," +
                "{\"time\": 1711526400000, \"heartRate\": 62.0, \"brainFog\": 1}," +
                "{\"time\": 1711533600000, \"heartRate\": 50.0, \"brainFog\": 1}," +
                "{\"time\": 1711540800000, \"heartRate\": 68.0, \"brainFog\": 3}" +
                "]";

        List<CircadianData> rawData = DataParser.parseHeartRateJson(mockJson);

        CircadianMath math = new CircadianMath();
        String healingWindow = math.getHealingWindow(rawData);

        System.out.println("NeuroNod Analysis Successful!");
        System.out.println("Predicted Healing Window: " + healingWindow);
    }
}