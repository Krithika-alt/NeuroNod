package com.neuronod.backend;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CircadianMath {

    private static final long HOURS_IN_MS = 3600000L;

    public long findNadirTime(List<CircadianData> dataList){
        if (dataList == null || dataList.isEmpty()){
            return 0;
        }
        double lowestHR = dataList.getFirst().getHeartRate();
        long nadirTime = dataList.getFirst().getTime();

        for (int i = 1; i < dataList.size(); ++i){
            if (dataList.get(i).getHeartRate() < lowestHR){
                lowestHR = dataList.get(i).getHeartRate();
                nadirTime = dataList.get(i).getTime();
            }
        }

        return nadirTime;
    }

    public String calculateHealingWindow(long nadirTime){
        long startTime = nadirTime - (3 * HOURS_IN_MS);
        long endTime = nadirTime + (3 * HOURS_IN_MS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        String formattedStart = Instant.ofEpochMilli(startTime).atZone(ZoneId.systemDefault()).format(formatter);
        String formattedEnd = Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).format(formatter);

        return formattedStart + " - " + formattedEnd;
    }
}
