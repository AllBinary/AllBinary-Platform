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

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageUnifierUtil
{
    
    private ImageUnifierUtil()
    {
    }
    
    public static GraphicsConfiguration getDefaultConfiguration()
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    
    public static BufferedImage getImage(
        BufferedImage bufferedImageArray[],
        ImageUnifierProperties imageUnifierProperties)
    {        
        BufferedImage newBufferedImage = 
           BufferedImageUtil.create(
            imageUnifierProperties.getWidth(),
            imageUnifierProperties.getHeight()
            );
        
        LogUtil.put(new Log("Setting Image: Width: " + newBufferedImage.getWidth() + 
            " Height: " + newBufferedImage.getHeight(), 
                "ImageUnifierUtil", "getImage"));
        
        Graphics2D g = newBufferedImage.createGraphics();
        
        int columnIndex = 0;
        int rowIndex = 0;
        for(int index = 0; index < bufferedImageArray.length; index ++)
        {
            
            int x = imageUnifierProperties.getImageUnifierCell().getWidth().intValue() * columnIndex++;
            int y = imageUnifierProperties.getImageUnifierCell().getHeight().intValue() * rowIndex;
            //imageUnifierProperties.getRows()
            
            LogUtil.put(new Log("Adding Image: " + index + " x: " + x + " y: " + y, 
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
