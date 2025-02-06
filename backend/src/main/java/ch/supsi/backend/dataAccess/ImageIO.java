package ch.supsi.backend.dataAccess;

import ch.supsi.backend.application.LogController;
import ch.supsi.backend.application.TranslationController;
import ch.supsi.backend.dataAccess.ExstensionsIO.IOinterface;
import ch.supsi.backend.domain.PixelModel;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ImageIO implements ImageIOInterface{

    private static ImageIO instance;
    private final static String FORMAT_PACKAGE = "ch.supsi.backend.dataAccess.ExstensionsIO";
    private Set<Class<? extends IOinterface>> ioClasses;
    private LogController logController;
    private TranslationController translationController;

    // Singleton: costruttore privato
    private ImageIO() {
        ioClasses = new HashSet<>();
        populateIOClasses();
        logController = LogController.getInstance();
        translationController = TranslationController.getInstance();
    }

    // Metodo per ottenere l'istanza singleton
    public static ImageIO getInstance() {
        if (instance == null) {
            instance = new ImageIO();
        }
        return instance;
    }

    @Override
    public Set<Class<? extends IOinterface>> getIoClasses() {
        return ioClasses;
    }

    // Usa Reflections per trovare automaticamente tutte le classi che implementano IOinterface
    private void populateIOClasses() {
        Reflections reflections = new Reflections(FORMAT_PACKAGE);

        // Trova tutte le classi che implementano IOinterface
        Set<Class<? extends IOinterface>> classes = reflections.getSubTypesOf(IOinterface.class);
        ioClasses.addAll(classes); // Aggiungi tutte le classi trovate al set
    }

    //metodo per leggere il magic number


    //metodo per estrarre i pixel da un'immagine
    @Override
    public PixelModel[][] load(String imagePath) {
        File file = new File(imagePath);

        try (InputStream inputStream = new FileInputStream(file)) {
            Scanner scanner = new Scanner(inputStream);
            String magicNumber = scanner.nextLine();
            
            //###CONTROLLO MAGIC NUMBER###
            if (!magicNumber.startsWith("P")) {
                throw new IOException("Formato non supportato");
            }
            
            IOinterface identifiedClass = getIdentifiedClass(magicNumber);
            
            //###CONTROLLO CLASSE IDENTIFICATA###
            if(identifiedClass == null) {
                throw new IOException("Formato non supportato");
            }
            
            // Legge altezza e larghezza immagine
            int width = scanner.nextInt();
            int height = scanner.nextInt();

            //ritorna la matrice costruita
            return identifiedClass.getMatrix(scanner, width, height);
            
        }catch (IOException e){
            logController.addMessage(translationController.translate("log.notSupportedFile"));

            return null;
        }
    }

    @Override
    // Metodo che ritorna l'istanza della classe associata al parametro input (magic number o estensione del file)
    public IOinterface getIdentifiedClass(String input) {
        for (Class<? extends IOinterface> ioClass : ioClasses) {
            try {
                // Creiamo una nuova istanza della classe
                IOinterface ioInstance = ioClass.getDeclaredConstructor().newInstance();

                // Controlliamo il magic number
                if (ioInstance.getMagicNumber().equals(input) || ioInstance.getFormat().equals(input)) {
                    return ioInstance; // Restituisce l'istanza se il magic number corrisponde
                }

            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                e.printStackTrace(); // Gestione eccezioni se la classe non può essere istanziata
            }
        }

        return null; // Se nessuna classe corrisponde al magic number
    }

    //metodi per test
    // Aggiungi questo metodo per i test
    public static void setInstance(ImageIO mockInstance) {
        instance = mockInstance;
    }

}
