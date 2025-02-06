package ch.supsi.os.frontend.model;

import java.util.ArrayList;

public interface LogModelInterface {
    ArrayList<String> getMessagesList();
    void addMessage(String message);
}
