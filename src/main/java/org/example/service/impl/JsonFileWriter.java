package org.example.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.example.service.Writer;

public class JsonFileWriter implements Writer, AutoCloseable {
    private final String outputFileName;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public JsonFileWriter(String outputFileName) {
        this.outputFileName = outputFileName;
        createWriter();
    }

    private void createWriter() {
        try {
            this.fileWriter = new FileWriter(outputFileName, true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Can`t open file");
        }
    }

    @Override
    public void writeToFile(String line) {
        try {
            this.bufferedWriter.write(line);
            this.bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file");
        }
    }

    @Override
    public void close() throws Exception {
        bufferedWriter.close();
        fileWriter.close();
    }
}
