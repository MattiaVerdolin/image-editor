package ch.supsi.backend.domain.transformation;

import ch.supsi.backend.domain.PixelModel;

public interface AvTransformationInterface {
    PixelModel[][] apply(PixelModel[][] matrix);
    String getName();
    String getId();
}
