package ch.supsi.backend.application;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ch.supsi.backend.domain.LogModel;

import java.util.ArrayList;

public class LogController {

    private static LogController myself;
    private final LogModel logModel;

    public static LogController getInstance() {
        if (myself == null) {
            myself = new LogController();
        }
        return myself;
    }

    public LogController() {
        this.logModel = LogModel.getInstance();
    }

    public void addMessage(String message) {
        LocalDateTime myObj = LocalDateTime.now();  // Use LocalDateTime instead of LocalTime
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myObj.format(myFormatObj);  // This works with LocalDateTime
        logModel.addMessage("["+formattedDate+"]" + " " + message);
    }

    public ArrayList<String> getMessagesList(){
        return logModel.getMessagesList();
    }
}
