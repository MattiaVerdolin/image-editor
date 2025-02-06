package ch.supsi.backend.domain;

import ch.supsi.backend.domain.transformation.AvTransformationInterface;

import java.util.List;
import java.util.Map;

public interface AvTransformationModelBackInterface {
    Map<String, String> getTransformationsNamesId();
    List<AvTransformationInterface> getTransformationsItems();
    List<String> getTransformationsNames();
}
