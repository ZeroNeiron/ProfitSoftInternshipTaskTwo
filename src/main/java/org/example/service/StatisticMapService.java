package org.example.service;

import java.util.Map;

public interface StatisticMapService {
    Map<StringBuilder, Double> createStatisticMap(String inputFilePath);
}
