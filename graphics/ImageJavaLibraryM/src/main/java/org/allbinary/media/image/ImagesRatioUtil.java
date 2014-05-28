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
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ImagesRatioUtil
{
    private ImagesRatioUtil()
    {
    }
    
    public static boolean isEqual(BufferedImage bufferedImageArray[], int totalImages)
    {
        int end = totalImages;
        if(bufferedImageArray.length < totalImages)
            end = bufferedImageArray.length;
        
        double ratio = ((double) bufferedImageArray[0].getWidth() / bufferedImageArray[0].getHeight());
        for(int index = 1; index < end; index++)
        {
            if(ratio != ((double) bufferedImageArray[index].getWidth() / bufferedImageArray[index].getHeight()))
            {
                return false;
            }
        }
        return true;
    }
    
    public static double getAverage(BufferedImage bufferedImageArray[], int totalImages)
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
    
    public static BufferedImage fudge(
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
        
        LogUtil.put(new Log(
            "Height: " + bufferedImage.getHeight() + " newHeight: " + newHeight +
            "Width: " + bufferedImage.getWidth() + " newWidth: " + newWidth +
            " needed ratio: " + ((double) newWidth/newHeight),
            "ImageRatioUtil", "fudge"));
        
        BufferedImage newBufferedImage =
            BufferedImageUtil.create(newWidth, newHeight);
        
        Graphics2D g = newBufferedImage.createGraphics();
        
        if(point.x > 0)
        {
            /*
            int data[] = new int[bufferedImage.getHeight()];
            byte bytes[] = new byte[data.length * 4];
         
            LogUtil.put(new Log("Get the first column of the original of: " + bytes.length, 
                "ImageRatioUtil", "fudge"));

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
            
            LogUtil.put(new Log("Draw some columns to fill in gap", "ImageRatioUtil", "fudge"));
            
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
            int data[] = new int[bufferedImage.getWidth()];
            byte bytes[] = new byte[data.length * 4];
         
            LogUtil.put(new Log("Get the first row of the original of: " + bytes.length, 
                "ImageRatioUtil", "fudge"));

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
            
            LogUtil.put(new Log("Draw some rows to fill in gap", "ImageRatioUtil", "fudge"));
            
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
    
    public static BufferedImage[] fudge(
        BufferedImage bufferedImageArray[], int totalImages, double ratio)
        throws Exception
    {
        int end = totalImages;
        if(bufferedImageArray.length < totalImages)
            end = bufferedImageArray.length;
        
        BufferedImage fudgedBufferedImageArray[] = new BufferedImage[end];
        
        for(int index = 0; index < end; index++)
        {
            fudgedBufferedImageArray[index] = fudge(bufferedImageArray[index], ratio);
        }
        return fudgedBufferedImageArray;
    }
}
