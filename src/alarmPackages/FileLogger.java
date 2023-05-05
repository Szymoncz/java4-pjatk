package alarmPackages;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private String fileName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void log(PinEvent event) {
        try {
            FileWriter fileWriter = new FileWriter(this.fileName, true);
            fileWriter.write(event.getSeverity().toString() + " " + event.getSource().toString() + " " + event.getMessage() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
