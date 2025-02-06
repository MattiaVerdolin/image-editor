package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.domain.PixelModel;

import java.util.UUID;

public class RotateLeftTransformation  implements AvTransformationInterface{

    private final static String NAME = "transformationL.rotate";
    private final String ID;
    TranslationController controller;

    public RotateLeftTransformation() {
        controller = TranslationController.getInstance();
        ID = UUID.randomUUID().toString();
    }

    @Override
    public PixelModel[][] apply(PixelModel[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        PixelModel[][] rotatedMatrix = new PixelModel[width][height]; // Nota le dimensioni invertite

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedMatrix[width - 1 - j][i] = matrix[i][j]; // Ruota 90 gradi in senso antiorario
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
