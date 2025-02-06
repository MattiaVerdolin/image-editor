package ch.supsi.backend.application;

import ch.supsi.backend.domain.ImageModel;
import ch.supsi.backend.domain.ImageModelInterface;
import ch.supsi.backend.domain.PixelModel;

import java.util.List;

public class ImageController {
    private final ImageModelInterface imageModelBack;
    private static ImageController myself;

    public static ImageController getInstance() {
        if (myself == null) {
            myself = new ImageController();
        }
        return myself;
    }

    public ImageController() {
        imageModelBack = ImageModel.getInstance();
    }

    public PixelModel[][] getImage(){
        return imageModelBack.getPixelMatrix();
    }

    public String getImagePath(){
        return imageModelBack.getImagePath();
    }

    public List<String> getExtensions(){
        return imageModelBack.getExtensions();
    }

    public void saveImage(String exportPath){
        imageModelBack.saveImage(exportPath);
    }

    public boolean ImageAbstraction(String path){
        return imageModelBack.setPixelMatrix(path);
    }

    public void setNewMatrix(PixelModel[][] newMatrix){
        imageModelBack.updatePixelMatrix(newMatrix);
    }
}
