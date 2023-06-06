package org.allbinary.logic.visual.media;

import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class MediaIOUtil
{

    private static final MediaIOUtil instance = new MediaIOUtil();

    /**
     * @return the instance
     */
    public static MediaIOUtil getInstance()
    {
        return instance;
    }

    public String[] getReaderFormatNames()
    {
        return ImageIO.getReaderFormatNames();
    }

    public String[] getWriterFormatNames()
    {
        return ImageIO.getWriterFormatNames();
    }

}
