package ch.supsi.os.frontend.controller;

public interface PipelineEventHandler extends EventHandler {
    void cancelPipeline();
    void run();
}
