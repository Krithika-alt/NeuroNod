package com.neuronod.backend;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CircadianRepository repository = new CircadianRepository();

        String healingWindow = repository.getHealingWindowResult();

        System.out.println("=== NeuroNod Backend: System Online ===");
        System.out.println("Processing 10 hours of biometric data...");
        System.out.println("Predicted Healing Window: " + healingWindow);
        System.out.println("========================================");
    }
}