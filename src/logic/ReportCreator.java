package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportCreator {

    private ReportCreator(){}; //cannot initialize Object

    public static void generateReport(String fileName, String content) {
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }
    }
}
