package org.example;

import java.util.Objects;
import org.example.service.ChangeNameService;
import org.example.service.Reader;
import org.example.service.Writer;
import org.example.service.impl.ChangeNameServiceImpl;
import org.example.service.impl.XmlFileWriter;
import org.example.service.impl.XmlFileReader;

public class Main {
    private static final String INPUT_PATH = "src/main/resources/inputXmlFiles/persons.xml";
    private static final String OUTPUT_PATH = "src/main/resources/outputXmlFiles/persons.xml";

    public static void main(String[] args) {
        Reader reader = new XmlFileReader(INPUT_PATH);
        Writer writer = new XmlFileWriter(OUTPUT_PATH);
        ChangeNameService changeNameService = new ChangeNameServiceImpl();
        String line = reader.readLine();
        while (!line.equals("")) {
            writer.writeLineToFile(changeNameService.changeNameAttribute(line));
            line = reader.readLine();
        }
    }
}