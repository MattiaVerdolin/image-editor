package ch.supsi.os.frontend.model;

import javafx.stage.Stage;

public interface AboutModelInterface {
    String getApplicationName();
    String getVersion();
    String getBuildTimestamp();
    String getDevelopers();
    String getDescription();
}
