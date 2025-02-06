package ch.supsi.os.frontend.model;

import ch.supsi.backend.domain.PixelModel;

import java.util.List;

public interface ImageModelFrontInterface {
    boolean ImageAbstraction(String filePath);
    PixelModel[][] getImage();
    String getImagePath();
    List<String> getExtensions();
    void saveImage(String exportPath);
}
