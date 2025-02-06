package ch.supsi.os.frontend.util;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StageManager {

    private static StageManager instance;

    // Lista di tutti gli Stage registrati
    private final List<Stage> stages = new ArrayList<>();

    private StageManager() {
    }

    // Singleton
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }

    // Aggiungi uno Stage alla lista
    public void registerStage(Stage stage) {
        if (!stages.contains(stage)) {
            stages.add(stage);
        }
    }

    // Chiudi tutti gli Stage
    public void closeAllStages() {
        for (Stage stage : new ArrayList<>(stages)) { // Evita ConcurrentModificationException
            stage.close();
        }
        stages.clear();
    }
}
