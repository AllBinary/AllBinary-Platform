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
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.string.CommonLabels;

public class ImagesRatioUtil
{
    private static final ImagesRatioUtil instance = new ImagesRatioUtil();

    /**
     * @return the instance
     */
    public static ImagesRatioUtil getInstance() {
        return instance;
    }
    
    private ImagesRatioUtil()
    {
    }
    
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    
    public boolean isEqual(final BufferedImage[] bufferedImageArray, final int totalImages)
    {
        int end = totalImages;
        if(bufferedImageArray.length < totalImages)
            end = bufferedImageArray.length;
        
        final double ratio = ((double) bufferedImageArray[0].getWidth() / bufferedImageArray[0].getHeight());
        for(int index = 1; index < end; index++)
        {
            if(ratio != ((double) bufferedImageArray[index].getWidth() / bufferedImageArray[index].getHeight()))
            {
                return false;
            }
        }
        return true;
    }
    
    public double getAverage(final BufferedImage[] bufferedImageArray, final int totalImages)
    {
        double ratio = 0;
        int end = totalImages;
        if(bufferedImageArray.length < totalImages)
            end = bufferedImageArray.length;
        
        for(int index = 0; index < end; index++)
        {
            ratio += ((double) bufferedImageArray[index].getWidth() / bufferedImageArray[index].getHeight());
        }
        return ((double) ratio/end);
    }
    
    public BufferedImage fudge(
        BufferedImage bufferedImage, double ratio)
        throws Exception
    {
        double imageRatio =
            ((double) bufferedImage.getWidth() / bufferedImage.getHeight());
        
        Point point = new Point();
        
        //need a larger height
        int newHeight = bufferedImage.getHeight();
        int newWidth = bufferedImage.getWidth();
        
        if(ratio > imageRatio)
        {
            //need a larger width
            newWidth = (int) ((double) bufferedImage.getHeight() * ratio);
            point.x = (newWidth - bufferedImage.getWidth())/2;
        }
        else
        {
            //need a larger height
            newHeight = (int) ((double) bufferedImage.getWidth() / ratio);
            point.y = (newHeight - bufferedImage.getHeight())/2;
        }
        
        final CommonLabels commonLabels = CommonLabels.getInstance();
        LogUtil.put(LogFactory.getInstance(
            commonLabels.WIDTH_LABEL + bufferedImage.getWidth() + " newWidth: " + newWidth +
            commonLabels.HEIGHT_LABEL + bufferedImage.getHeight() + " newHeight: " + newHeight +
            " needed ratio: " + ((double) newWidth/newHeight),
            this, "fudge"));
        
        BufferedImage newBufferedImage =
            this.imageUtil.create(newWidth, newHeight);
        
        Graphics2D g = newBufferedImage.createGraphics();
        
        if(point.x > 0)
        {
            /*
            final int[] data = new int[bufferedImage.getHeight()];
            final byte[] bytes = new byte[data.length * 4];
         
            LogUtil.put(LogFactory.getInstance("Get the first column of the original of: " + bytes.length, 
                this, "fudge"));

            bufferedImage.getRGB(
                0, //startX
                0, //startY
                1, //w
                bufferedImage.getHeight(), //h
                data, 0, 0);

            for(int index = 0; index < data.length; index++)
            {
                int intValue = Color.YELLOW.getRGB(); //data[index];
                bytes[(index * 4)] = (byte) ((intValue >>> 24)); //& 0xFF);
                bytes[(index * 4) + 1] = (byte) ((intValue >>> 16)); //& 0xFF);
                bytes[(index * 4) + 2] = (byte) ((intValue >>> 8)); //& 0xFF);
                bytes[(index * 4) + 3] = (byte) ((intValue >>> 0)); //& 0xFF);
            }
            */
            
            BufferedImage firstColumnBufferedImage = bufferedImage.getSubimage(
                0, 0, 1, bufferedImage.getHeight());

            BufferedImage lastColumnBufferedImage = bufferedImage.getSubimage(
                bufferedImage.getWidth() - 1, 0, 1, bufferedImage.getHeight());
            
            LogUtil.put(LogFactory.getInstance("Draw some columns to fill in gap", this, "fudge"));
            
            //g.setColor(Color.RED);
            
            for(int index = 0; index < point.x; index++)
            {
                //g.drawLine(index, 0, index, bufferedImage.getHeight());
                //g.drawLine(newWidth - index, 0, newWidth - index, bufferedImage.getHeight());
                g.drawImage(firstColumnBufferedImage, index, 0, null);
                g.drawImage(lastColumnBufferedImage, newWidth - index, 0, null);
                //g.drawBytes(bytes, 0, bytes.length, 0, index);
            }
        }

        if(point.y > 0)
        {
            /*
            final int[] data = new int[bufferedImage.getWidth()];
            final byte[] bytes = new byte[data.length * 4];
         
            LogUtil.put(LogFactory.getInstance("Get the first row of the original of: " + bytes.length, 
                this, "fudge"));

            bufferedImage.getRGB(
                0, //startX
                0, //startY
                bufferedImage.getWidth(), //w
                1, //h
                data, 0, 0);
            
            for(int index = 0; index < data.length; index++)
            {
                int intValue = Color.YELLOW.getRGB(); //data[index];
                
                bytes[(index * 4)] = (byte) ((intValue >>> 24)); //& 0xFF);
                bytes[(index * 4) + 1] = (byte) ((intValue >>> 16)); //& 0xFF);
                bytes[(index * 4) + 2] = (byte) ((intValue >>> 8)); //& 0xFF);
                bytes[(index * 4) + 3] = (byte) ((intValue >>> 0)); //& 0xFF);
            }
             */

            BufferedImage firstRowBufferedImage = bufferedImage.getSubimage(
                0, 0, bufferedImage.getWidth(), 1);

            BufferedImage lastRowBufferedImage = bufferedImage.getSubimage(
                0, bufferedImage.getHeight() - 1, bufferedImage.getWidth(), 1);
            
            LogUtil.put(LogFactory.getInstance("Draw some rows to fill in gap", this, "fudge"));
            
            //g.setColor(Color.RED);
            
            for(int index = 0; index < point.y; index++)
            {
                //g.drawLine(0, index, bufferedImage.getWidth(), index);
                //g.drawLine(0, newHeight - index, bufferedImage.getWidth(), newHeight - index);
                g.drawImage(firstRowBufferedImage, 0, index, null);
                g.drawImage(lastRowBufferedImage, 0, newHeight - index, null);
                //g.drawBytes(bytes, 0, bytes.length, index, 0);
            }
        }
        
        g.drawImage(bufferedImage, point.x , point.y,
            bufferedImage.getWidth(),
            bufferedImage.getHeight(), null);
        
        return newBufferedImage;
    }
    
    public BufferedImage[] fudge(
        final BufferedImage[] bufferedImageArray, final int totalImages, final double ratio)
        throws Exception
    {
        int end = totalImages;
        if(bufferedImageArray.length < totalImages)
            end = bufferedImageArray.length;
        
        final BufferedImage[] fudgedBufferedImageArray = new BufferedImage[end];
        
        for(int index = 0; index < end; index++)
        {
            fudgedBufferedImageArray[index] = fudge(bufferedImageArray[index], ratio);
        }
        return fudgedBufferedImageArray;
    }
}
