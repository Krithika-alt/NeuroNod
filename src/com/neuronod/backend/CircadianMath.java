package com.neuronod.backend;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public List<CircadianData> filterOutliers(List<CircadianData> rawData){
        List<CircadianData> cleanedData = new ArrayList<>();
        for (int i = 0; i < rawData.size(); ++i){
            if ((rawData.get(i).getHeartRate() > 30) && (rawData.get(i).getHeartRate() < 200)){
                cleanedData.add(rawData.get(i));
            }
        }

        return cleanedData;
    }

    public List<CircadianData> calculateMovingAverage(List<CircadianData> cleanData, int windowSize){
        List<CircadianData> smoothedList = new ArrayList<>();
        for (int i = 0; i < cleanData.size(); ++i){
            double sum = 0;
            int count = 0;
            double averageHR = 0;
            int ogBrainFog = 0;
            long ogTime = 0;

            int start = Math.max(0, i - windowSize);
            int end = Math.min(cleanData.size() - 1, i + windowSize);

            for (int j = start; j <= end; j++){
                sum += cleanData.get(j).getHeartRate();
                count++;
            }

            averageHR = sum/count;
            ogBrainFog = cleanData.get(i).getBrainFog();
            ogTime = cleanData.get(i).getTime();

            smoothedList.add(new CircadianData(ogTime, averageHR, ogBrainFog));
        }

        return smoothedList;
    }

    public String getHealingWindow(List<CircadianData> rawData){
        List<CircadianData> cleaned = filterOutliers(rawData);
        List<CircadianData> smoothed = calculateMovingAverage(cleaned, 5);
        long nadirTime = findNadirTime(smoothed);

        return calculateHealingWindow(nadirTime);
    }
}
