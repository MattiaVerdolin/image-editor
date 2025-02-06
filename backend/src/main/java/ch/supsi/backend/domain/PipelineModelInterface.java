package ch.supsi.backend.domain;

import ch.supsi.backend.domain.transformation.AvTransformationInterface;

import java.util.List;

public interface PipelineModelInterface {
    List<String> getTransformationsNames();
    void addTransformation(AvTransformationInterface transformation);
    void clearPipeline();
    PixelModel[][] runPipeline(PixelModel[][] imageMatrix);
}
