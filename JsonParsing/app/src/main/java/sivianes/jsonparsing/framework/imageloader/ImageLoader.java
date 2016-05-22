package sivianes.jsonparsing.framework.imageloader;

/**
 * Created by Javier on 23/05/2016.
 */
public class ImageLoader {
    private static ImageLoader ourInstance = new ImageLoader();

    public static ImageLoader getInstance() {
        return ourInstance;
    }

    private ImageLoader() {



    }
}
