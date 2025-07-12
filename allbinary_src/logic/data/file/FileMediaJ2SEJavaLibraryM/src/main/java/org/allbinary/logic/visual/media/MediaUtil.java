package org.allbinary.logic.visual.media;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.HashMap;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageUtil;
import org.allbinary.string.CommonLabels;
//import com.sun.imageio.plugins.common.ImageUtil;

public class MediaUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final MediaUtil instance = new MediaUtil();

    public static MediaUtil getInstance() {
        return instance;
    }

    private MediaUtil()
    {
    }

    public void saveImageFile(
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
            HashMap hashMap = this.getImageBufferPropertyHashMap(bufferedImage);
            logUtil.put("Image Properties: " + hashMap.toString(), this, "saveImageFile()");
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
            final CommonLabels commonLabels = CommonLabels.getInstance();

        	final StringMaker stringBuffer = new StringMaker();

        	stringBuffer.append("Get Path: ");
        	stringBuffer.append(originalImageFile.getPath());
        	stringBuffer.append("\nNewImageFileName: ");
        	stringBuffer.append(newImageFileName);
        	stringBuffer.append("\nCategory: ");
        	stringBuffer.append(category);
        	stringBuffer.append("\nSave File Type: ");
        	stringBuffer.append(mediaData.getName());
            //"\n" + commonLabels + width +
            //"\n" + commonLabels + height +
        	stringBuffer.append("\nNew").append(commonLabels.WIDTH_LABEL);
        	stringBuffer.append(newWidth);
        	stringBuffer.append("\nNew").append(commonLabels.HEIGHT_LABEL);
        	stringBuffer.append(newHeight);
            //"\nNew Double" commonLabels+ d_newWidth +
            //"\nNew Double" commonLabels+ d_newHeight +
            //"\ncommonLabels Ratio: " + widthRatio +
            //"\ncommonLabels Ratio: " + heightRatio +
        	stringBuffer.append("\nFile Length: ");
        	stringBuffer.append(originalImageFile.length());
        	stringBuffer.append("\nNew File Length: ");
        	stringBuffer.append(imageFile.length());

            logUtil.put(
            		stringBuffer.toString(), this, "saveImageFile()");        }
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
