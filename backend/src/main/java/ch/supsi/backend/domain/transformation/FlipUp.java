package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.domain.PixelModel;

import java.util.UUID;

public class FlipUp implements AvTransformationInterface{

    private final static String NAME = "transformation.flipUp";
    private final String ID;
    TranslationController controller;

    public FlipUp() {
        controller = TranslationController.getInstance();
        ID = UUID.randomUUID().toString();
    }

    @Override
    public PixelModel[][] apply(PixelModel[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        PixelModel[][] flippedMatrix = new PixelModel[height][width];

        for (int i = 0; i < height; i++) {
            flippedMatrix[i] = matrix[height - 1 - i]; // Reverse the rows
        }

        return flippedMatrix;
    }

    @Override
    public String getName() {
        return controller.translate(NAME);
    }

    @Override
    public String getId() {
        return ID;
    }


}
