package org.allbinary.logic.visual.media;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.AbFileNativeUtil;

/**
 *
 * @author user
 */
public class ImageIOUtil {

    public static BufferedImage read(AbFile file) throws Exception
    {
        return ImageIO.read(AbFileNativeUtil.get(file));
    }

    public static boolean write(
        RenderedImage renderedImage, String name, AbFile file) throws Exception
    {
        return ImageIO.write(renderedImage,
                name, AbFileNativeUtil.get(file));
    }
}
