package com.andrew.Statistics;

import com.andrew.Model.StatisticsModel;
import com.andrew.interfaces.Statistics;

public class NumDotsStatistics implements Statistics {

    private final String nameOfStat = "Number of dots ";


    @Override
    public StatisticsModel getStatistics(String fileContent) {
        long count = fileContent.replaceAll("[^\\.]", "").length();
        StatisticsModel statistics = new StatisticsModel(nameOfStat, String.valueOf(count));
        return statistics;
    }
}
