package ch.supsi.backend.domain;

import ch.supsi.backend.domain.transformation.AvTransformationInterface;

import java.util.ArrayList;
import java.util.List;

public class PipelineModel implements PipelineModelInterface {
    private List<AvTransformationInterface> transformations = new ArrayList<>();
    private static PipelineModel myself;

    private PipelineModel() {}

    public static PipelineModel getInstance() {
        if (myself == null) {
            myself = new PipelineModel();
        }
        return myself;
    }

    public void addTransformation(AvTransformationInterface transformation) {
        transformations.add(transformation);
    }

    // Metodo per ottenere la lista dei nomi delle trasformazioni
    public List<String> getTransformationsNames() {
        List <String> transformationsNames = new ArrayList<>();
        for (AvTransformationInterface transformation : transformations) {
            transformationsNames.add(transformation.getName());
        }
        return transformationsNames;
    }

    public void clearPipeline() {
        transformations.clear();
    }

    public PixelModel[][] runPipeline(PixelModel[][] imageMatrix) {

        for (AvTransformationInterface transformation : transformations) {
            imageMatrix = transformation.apply(imageMatrix);
        }
        return imageMatrix;
    }
}
