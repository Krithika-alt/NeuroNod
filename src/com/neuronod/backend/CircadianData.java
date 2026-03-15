package com.neuronod.backend;


public class CircadianData {
    private long time;
    private double heartRate;
    private int brainFog;

    public CircadianData(long time, double heartRate, int brainFog){
        this.time = time;
        this.heartRate = heartRate;
        this.brainFog = brainFog;
    }

    public double getHeartRate(){
        return heartRate;
    }

    public long getTime(){
        return time;
    }

    public int getBrainFog(){
        return brainFog;
    }
}
