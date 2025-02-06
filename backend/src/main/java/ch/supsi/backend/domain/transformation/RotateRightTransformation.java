package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.domain.PixelModel;

import java.util.UUID;

public class RotateRightTransformation implements AvTransformationInterface{

    private final static String NAME = "transformationR.rotate";
    private final String ID;
    TranslationController controller;

    public RotateRightTransformation() {
        controller = TranslationController.getInstance();
        ID = UUID.randomUUID().toString();
    }

    @Override
    public PixelModel[][] apply(PixelModel[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        PixelModel[][] rotatedMatrix = new PixelModel[width][height]; // Note the swapped dimensions

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedMatrix[j][height - 1 - i] = matrix[i][j]; // Rotate 90 degrees clockwise
            }
        }
        return rotatedMatrix;
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
