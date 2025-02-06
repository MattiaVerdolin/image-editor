package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.domain.PixelModel;

import java.util.UUID;

public class FlipSide implements AvTransformationInterface{
    private final static String NAME = "transformation.flipSide";
    private final String ID;
    TranslationController controller;

    public FlipSide() {
        controller = TranslationController.getInstance();
        ID = UUID.randomUUID().toString();
    }

    @Override
    public PixelModel[][] apply(PixelModel[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        PixelModel[][] flippedMatrix = new PixelModel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flippedMatrix[i][j] = matrix[i][width - 1 - j]; // Reverse the columns within each row
            }
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
