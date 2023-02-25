package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger implements Loggable {
    String fileName;

    public Logger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void log(String message) {
        try (FileWriter writer = new FileWriter(fileName, true)) { // PrintWriter writer = new PrintWriter(fileName,
                                                                   // "Cp1251")
            Date date = new Date();
            writer.append(" " + date + " " + message + "\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}