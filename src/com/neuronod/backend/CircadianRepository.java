package com.neuronod.backend;

import java.util.List;

public class CircadianRepository {
    private final CircadianMath engine;

    public CircadianRepository() {
        this.engine = new CircadianMath();
    }

    public String getHealingWindowResult() {
        List<CircadianData> rawData = MockDataGenerator.generateNightOfData();

        return engine.getHealingWindow(rawData);
    }
}