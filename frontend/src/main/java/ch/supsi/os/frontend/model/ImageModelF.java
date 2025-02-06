package ch.supsi.os.frontend.model;

import ch.supsi.backend.application.ImageController;
import ch.supsi.backend.domain.PixelModel;

import java.util.List;

public class ImageModelF extends AbstractModel implements ImageModelFrontInterface{
    private final ImageController imageController;
    private static ImageModelF myself;

    public static ImageModelF getInstance() {
        if (myself == null) {
            myself = new ImageModelF();
        }
        return myself;
    }

    public ImageModelF() {
        imageController = ImageController.getInstance();
    }

    @Override
    public boolean ImageAbstraction(String filePath){
        return imageController.ImageAbstraction(filePath);
    }

    @Override
    public PixelModel[][] getImage(){
        return imageController.getImage();
    }

    @Override
    public List<String> getExtensions(){
        return imageController.getExtensions();
    }

    @Override
    public String getImagePath(){
        return imageController.getImagePath();
    }

    @Override
    public void saveImage(String exportPath){
        imageController.saveImage(exportPath);
    }

}
