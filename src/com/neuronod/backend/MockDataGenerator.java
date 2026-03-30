package com.neuronod.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockDataGenerator {
    public static List<CircadianData> generateNightOfData() {
        List<CircadianData> data = new ArrayList<>();
        Random rand = new Random();
        long startTime = 1711519200000L; // 11:00 PM

        for (int i = 0; i < 120; i++) { // 120 points = 10 hours of data (every 5 mins)
            long time = startTime + (i * 5 * 60 * 1000);

            // Create a "U-Shape" curve for the heart rate
            // High at start/end, low in the middle (around point 60)
            double baseHR = 70.0 - (15.0 * Math.sin(Math.PI * i / 120.0));

            // Add some "Noise" (Random jitters)
            double noise = rand.nextDouble() * 5.0;

            // Occasionally add a "Glitch" (Outlier) to test your filter
            double finalHR = (rand.nextInt(50) == 0) ? 250.0 : baseHR + noise;

            data.add(new CircadianData(time, finalHR, rand.nextInt(5) + 1));
        }
        return data;
    }
}