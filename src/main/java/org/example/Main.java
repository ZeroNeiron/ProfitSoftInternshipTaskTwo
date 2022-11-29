package org.example;

import java.util.Map;
import org.example.service.ChangeNameService;
import org.example.service.Reader;
import org.example.service.StatisticMapService;
import org.example.service.StatisticService;
import org.example.service.Writer;
import org.example.service.impl.ChangeNameServiceImpl;
import org.example.service.impl.JsonFileWriter;
import org.example.service.impl.StatisticMapServiceImpl;
import org.example.service.impl.StatisticServiceImpl;
import org.example.service.impl.XmlFileReader;
import org.example.service.impl.XmlFileWriter;

public class Main {
    private static final String INPUT_PATH = "src/main/resources/inputXmlFiles/persons.xml";
    private static final String INPUT_STATISTIC_PATH = "src/main/resources/years";
    private static final String OUTPUT_PATH = "src/main/resources/outputFiles/persons.xml";
    private static final String OUTPUT_JSON_PATH = "src/main/resources/outputFiles/statistic.json";

    public static void main(String[] args) {
        Reader reader = new XmlFileReader(INPUT_PATH, "/>");
        Writer writer = new XmlFileWriter(OUTPUT_PATH);
        ChangeNameService changeNameService = new ChangeNameServiceImpl();
        String line = reader.readLine();
        while (!line.equals("")) {
            writer.writeToFile(changeNameService.changeNameAttribute(line));
            line = reader.readLine();
        }

        StatisticMapService statisticMapService = new StatisticMapServiceImpl();
        Map<StringBuilder, Double> statisticMap = statisticMapService
                .createStatisticMap(INPUT_STATISTIC_PATH);
        StatisticService statisticService = new StatisticServiceImpl();
        String statistic = statisticService.getStatistic(statisticMap);
        Writer jsonWriter = new JsonFileWriter(OUTPUT_JSON_PATH);
        jsonWriter.writeToFile(statistic);
    }
}
