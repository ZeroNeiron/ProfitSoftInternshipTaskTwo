package org.example.service.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.service.StatisticService;
import org.json.simple.JSONValue;

public class StatisticServiceImpl implements StatisticService {
    @Override
    public String getStatistic(Map<StringBuilder, Double> statisticMap) {
        Map<String, Double> result = new LinkedHashMap<>();
        statisticMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getKey().toString(),
                        Collectors.averagingDouble(Map.Entry::getValue)
                )).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return JSONValue.toJSONString(result);
    }
}
