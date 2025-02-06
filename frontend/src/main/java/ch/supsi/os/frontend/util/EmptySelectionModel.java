package ch.supsi.os.frontend.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;

public class EmptySelectionModel<T> extends MultipleSelectionModel<T> {

    private final ObservableList<Integer> emptyIndices = FXCollections.observableArrayList();
    private final ObservableList<T> emptyItems = FXCollections.emptyObservableList();

    @Override
    public ObservableList<Integer> getSelectedIndices() {
        return emptyIndices;
    }

    @Override
    public ObservableList<T> getSelectedItems() {
        return emptyItems;
    }

    @Override
    public void selectIndices(int index, int... indices) {
        // Do nothing
    }

    @Override
    public void selectAll() {
        // Do nothing
    }

    @Override
    public void selectFirst() {
        // Do nothing
    }

    @Override
    public void selectLast() {
        // Do nothing
    }

    @Override
    public void clearAndSelect(int index) {
        // Do nothing
    }

    @Override
    public void select(int index) {
        // Do nothing
    }

    @Override
    public void select(T obj) {
        // Do nothing
    }

    @Override
    public void clearSelection(int index) {
        // Do nothing
    }

    @Override
    public void clearSelection() {
        // Do nothing
    }

    @Override
    public void selectPrevious() {
        // Do nothing
    }

    @Override
    public void selectNext() {
        // Do nothing
    }

    @Override
    public boolean isSelected(int index) {
        return false; // no items are selected
    }

    @Override
    public boolean isEmpty() {
        return true; // selection is always empty
    }
}

