package ch.supsi.backend.domain;

public interface LogObserver {
    void onLogUpdate(String newLogMessage);
}
