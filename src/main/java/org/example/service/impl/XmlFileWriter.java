package org.example.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.example.service.Writer;

public class XmlFileWriter implements Writer {
    private static final String DELIMITER = "/>";
    private final String outputFileName;
    private BufferedWriter bufferedWriter;

    public XmlFileWriter(String outputFileName) {
        this.outputFileName = outputFileName;
        createWriter();
    }

    private void createWriter(){
        try {
            FileWriter fileWriter = new FileWriter(outputFileName, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file");
        }
    }
    @Override
    public void writeLineToFile(String line) {
        try {
            this.bufferedWriter.write(line.endsWith(">") ? line : line + DELIMITER);
            this.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file");
        }
    }
}
