package org.allbinary.logic.visual.media;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.util.HashMap;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.media.image.ImageUtil;
//import com.sun.imageio.plugins.common.ImageUtil;

public class MediaUtil
{
    private static final MediaUtil instance = new MediaUtil();

    private MediaUtil()
    {
    }

    public static void saveImageFile(
        AbFile originalImageFile, String newImageFileName,
        String category, MediaData mediaData, int newWidth, int newHeight)
        throws Exception
    {
        if (originalImageFile == null || !originalImageFile.isFile())
        {
            throw new Exception("Original Image File Does Not Exist.");
        }

        BufferedImage bufferedImage = ImageIOUtil.read(originalImageFile);

        if (bufferedImage == null)
        {
            throw new Exception("Unable to find ImageReader for this file.");
        }

        if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().VIEW))
        {
            HashMap hashMap = MediaUtil.getImageBufferPropertyHashMap(bufferedImage);
            LogUtil.put(LogFactory.getInstance(
                "Image Properties: " + hashMap.toString(), instance, "saveImageFile()"));
        }

        final AbFile imageFile = new AbFile(category + newImageFileName);
        imageFile.createNewFile();

        final ImageUtil imageUtil = ImageUtil.getInstance();
        BufferedImage newBufferedImage = imageUtil.createBufferedImage(
            bufferedImage, newWidth, newHeight);

        boolean isWritten = ImageIOUtil.write(
            (RenderedImage) newBufferedImage,
            mediaData.getName(), imageFile);

        if (!isWritten)
        {
            throw new Exception("Unable to write.");
        }

        if (LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().VIEW))
        {
        	StringBuffer stringBuffer = new StringBuffer();

        	stringBuffer.append("Get Path: ");
        	stringBuffer.append(originalImageFile.getPath());
        	stringBuffer.append("\nNewImageFileName: ");
        	stringBuffer.append(newImageFileName);
        	stringBuffer.append("\nCategory: ");
        	stringBuffer.append(category);
        	stringBuffer.append("\nSave File Type: ");
        	stringBuffer.append(mediaData.getName());
            //"\nWidth: " + width +
            //"\nHeight: " + height +
        	stringBuffer.append("\nNew Width: ");
        	stringBuffer.append(newWidth);
        	stringBuffer.append("\nNew Height: ");
        	stringBuffer.append(newHeight);
            //"\nNew Double Width: " + d_newWidth +
            //"\nNew Double Height: " + d_newHeight +
            //"\nWidth Ratio: " + widthRatio +
            //"\nHeight Ratio: " + heightRatio +
        	stringBuffer.append("\nFile Length: ");
        	stringBuffer.append(originalImageFile.length());
        	stringBuffer.append("\nNew File Length: ");
        	stringBuffer.append(imageFile.length());

            LogUtil.put(LogFactory.getInstance(
            		stringBuffer.toString(), instance, "saveImageFile()"));        }
    }

    private static HashMap getImageBufferPropertyHashMap(
        final BufferedImage bufferedImage)
    {
        final HashMap hashMap = new HashMap();
        final String[] propertyStringArray = bufferedImage.getPropertyNames();
        if (propertyStringArray != null)
        {
            for (int index = 0; index < propertyStringArray.length; index++)
            {
                //String propertyString =
                Object propertyObject =
                    bufferedImage.getProperty(propertyStringArray[index]);
                hashMap.put(propertyStringArray[index], propertyObject.toString());
            }
        }
        return hashMap;
    }
}
