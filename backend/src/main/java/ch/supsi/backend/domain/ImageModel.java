package ch.supsi.backend.domain;

import ch.supsi.backend.dataAccess.ExstensionsIO.IOinterface;
import ch.supsi.backend.dataAccess.ImageIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ImageModel implements ImageModelInterface {

    private PixelModel [][] pixelMatrix;
    private final ImageIO imageIO;
    private static ImageModel myself;
    private String imagePath;
    private LogModel logModel;


    public static ImageModel getInstance() {
        if (myself == null) {
            myself = new ImageModel();
        }
        return myself;
    }

    @Override
    public List<String> getExtensions() {
        List<String> compatibilityExtensions = new ArrayList<>();
        try {
            // Ottieni tutte le classi che implementano IOinterface tramite imageIO
            Set<Class<? extends IOinterface>> ioClasses = imageIO.getIoClasses();

            // Per ciascuna classe, crea un'istanza e aggiungi il formato alla lista delle estensioni
            for (Class<? extends IOinterface> ioClass : ioClasses) {
                IOinterface ioInstance = ioClass.getDeclaredConstructor().newInstance();  // Crea un'istanza della classe
                compatibilityExtensions.add(ioInstance.getFormat());  // Aggiunge il formato
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compatibilityExtensions;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public void saveImage(String exportPath) {
        // Estrai il formato (estensione) dal percorso di esportazione
        String format = exportPath.substring(exportPath.lastIndexOf('.') + 1);

        // Ottieni l'istanza del formato usando l'estensione
        IOinterface formatInstance = imageIO.getIdentifiedClass(format);

        // Verifica se il formato è supportato e salva l'immagine
        if (formatInstance != null) {
            formatInstance.save(exportPath, pixelMatrix);
        } else {
            logModel.addMessage("Formato non supportato!" + format);
            throw new IllegalArgumentException("Formato non supportato: " + format);
        }
    }

    public ImageModel() {
        imageIO = ImageIO.getInstance();
        this.logModel = LogModel.getInstance();
    }

    @Override
    public boolean setPixelMatrix(String path) {
        this.pixelMatrix = imageIO.load(path);
        if (pixelMatrix != null) {
            this.imagePath = path;
            return true;
        }
        return false;
    }


    public PixelModel[][] getPixelMatrix() {
        return pixelMatrix;
    }

    public void updatePixelMatrix(PixelModel[][] newPixelMatrix) {
        this.pixelMatrix = newPixelMatrix;
    }

}















