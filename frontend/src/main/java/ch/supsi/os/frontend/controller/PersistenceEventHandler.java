package ch.supsi.os.frontend.controller;

public interface PersistenceEventHandler extends MenuBarEventHandler{
    void save();
    void saveAs();
}
