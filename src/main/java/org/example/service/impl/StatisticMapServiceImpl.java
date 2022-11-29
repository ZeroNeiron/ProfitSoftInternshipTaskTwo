package org.example.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.service.Reader;
import org.example.service.StatisticMapService;

public class StatisticMapServiceImpl implements StatisticMapService {
    private static final String TYPE = "<type>";
    private static final String FINE_AMOUNT = "<fine_amount>";
    private String delimiter = "</violation>";

    @Override
    public Map<StringBuilder, Double> createStatisticMap(String inputFilesPath) {
        List<Path> allFileNamesInDirectory = getAllFileNamesInDirectory(inputFilesPath);
        Map<StringBuilder, Double> statisticMap = new LinkedHashMap<>();

        for (Path path : allFileNamesInDirectory) {

            Reader reader = new XmlFileReader(path.toString(), delimiter);
            String line = reader.readLine();

            while (!line.trim().equals("</violations>")) {
                int typeIndex = line.indexOf(TYPE);
                int amountIndex = line.indexOf(FINE_AMOUNT);
                String type = line.substring(line.indexOf(">", typeIndex) + 1,
                        line.indexOf("<", typeIndex + 1));
                Double value = Double.valueOf(line.substring(
                        line.indexOf(">", amountIndex) + 1, line.indexOf("<", amountIndex + 1)));
                statisticMap.put(new StringBuilder(type), value);

                line = reader.readLine();
            }
        }
        return statisticMap;
    }

    private List<Path> getAllFileNamesInDirectory(String path) {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can`t get files");
        }
    }
}
