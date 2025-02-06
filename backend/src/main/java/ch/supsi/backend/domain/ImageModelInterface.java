package ch.supsi.backend.domain;

import java.util.List;

public interface ImageModelInterface {
    PixelModel[][] getPixelMatrix();
    String getImagePath();
    boolean setPixelMatrix(String path);
    void updatePixelMatrix(PixelModel[][] newPixelMatrix);
    List<String> getExtensions();
    void saveImage(String exportPath);
}
