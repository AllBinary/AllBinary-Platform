/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package org.allbinary.media.image;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonLabels;

public class ImageUnifierUtil
{
    private static final ImageUnifierUtil instance = new ImageUnifierUtil();

    /**
     * @return the instance
     */
    public static ImageUnifierUtil getInstance() {
        return instance;
    }
    
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    
    private ImageUnifierUtil()
    {
    }
    
    public GraphicsConfiguration getDefaultConfiguration()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    
    public BufferedImage getImage(
        BufferedImage[] bufferedImageArray,
        ImageUnifierProperties imageUnifierProperties)
    {        
        BufferedImage newBufferedImage = 
           this.imageUtil.create(
            imageUnifierProperties.getWidth(),
            imageUnifierProperties.getHeight()
            );
        
        final CommonLabels commonLabels = CommonLabels.getInstance();
        LogUtil.put(LogFactory.getInstance("Setting Image - " + commonLabels.WIDTH_LABEL + newBufferedImage.getWidth() + 
            commonLabels.HEIGHT_LABEL + newBufferedImage.getHeight(), 
                "ImageUnifierUtil", "getImage"));
        
        Graphics2D g = newBufferedImage.createGraphics();
        
        int columnIndex = 0;
        int rowIndex = 0;
        for(int index = 0; index < bufferedImageArray.length; index ++)
        {
            
            int x = imageUnifierProperties.getImageUnifierCell().getWidth().intValue() * columnIndex++;
            int y = imageUnifierProperties.getImageUnifierCell().getHeight().intValue() * rowIndex;
            //imageUnifierProperties.getRows()
            
            LogUtil.put(LogFactory.getInstance("Adding Image: " + index + " x: " + x + " y: " + y, 
                "ImageUnifierUtil", "getImage"));
            
            g.drawImage(bufferedImageArray[index], x , y,
                imageUnifierProperties.getImageUnifierCell().getWidth().intValue(),
                imageUnifierProperties.getImageUnifierCell().getHeight().intValue(), null);
            
            int totalColumnsMinusOne = (imageUnifierProperties.getColumns().intValue() - 1);
            if(columnIndex > totalColumnsMinusOne)
            {
                rowIndex++;
                columnIndex = 0;
            }
        }
        
        g.dispose();
        
        return newBufferedImage;
    }
    
}
