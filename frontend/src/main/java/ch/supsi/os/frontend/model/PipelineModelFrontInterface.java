package ch.supsi.os.frontend.model;

import java.util.List;

public interface PipelineModelFrontInterface {
     void addTransformation(String id);
     void clearPipeline();
     void runPipeline();
     List<String> transformationsInPipeline();
}
