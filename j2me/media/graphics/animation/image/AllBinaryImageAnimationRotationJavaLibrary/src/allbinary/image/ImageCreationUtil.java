package allbinary.image;

import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.GameFeatureImageCacheFactory;

import allbinary.game.configuration.feature.Features;

public class ImageCreationUtil
{
    private static final ImageCreationUtil instance = new ImageCreationUtil();

    public static ImageCreationUtil getInstance()
    {
        return instance;
    }
    
    private ImageCreationUtil()
    {
        
    }
    
    public Image getInstance(int width, int height) throws Exception
    {
        boolean isOpenGL = Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL);

        Image image;

        if (isOpenGL && width % 2 != 0)
        {
            image = GameFeatureImageCacheFactory.getInstance().get(
                    this.getClass().getName(), width + 1, height + 1);
        } else
        {
            image = GameFeatureImageCacheFactory.getInstance().get(
                    this.getClass().getName(), width, height);
        }
        return image;
    }
}
