package org.example.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.example.service.Reader;

public class XmlFileReader implements Reader {
    private static final String DELIMITER = "/>";
    private final String inputFileName;
    private Scanner scanner;

    public XmlFileReader(String inputFileName) {
        this.inputFileName = inputFileName;
        createScanner();
    }

    @Override
    public String readLine() {
        if (scanner.hasNext()) {
            return scanner.next();
        }
        return "";
    }

    private void createScanner(){
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFileName);
            this.scanner = new Scanner(fileInputStream, StandardCharsets.UTF_8).useDelimiter(DELIMITER);
        } catch (IOException e) {
            throw new RuntimeException("Can`t read data from file");
        }
    }
}
