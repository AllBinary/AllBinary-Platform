package org.allbinary.graphics.displayable;

import javax.microedition.lcdui.Image;


public class ScreenRelationalUtil
{
    private static final ScreenRelationalUtil instance = new ScreenRelationalUtil();

    public static ScreenRelationalUtil getInstance()
    {
        return instance;
    }

    public float getScale(Image image)
    {
        return this.getScale(image.getWidth(), image.getHeight());
    }

    public float getScale(int aWidth, int aHeight)
    {
        DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

        int[] last = displayInfoSingleton.getLast();
        float width = last[displayInfoSingleton.WIDTH];
        float height = last[displayInfoSingleton.HEIGHT];

        float largestSize = width;

    	if(largestSize < height)
        {
            largestSize = height;
        }

        float scale = (largestSize + 20) / aWidth;

        /*
         float scaleX = width/aWidth;
         float scaleY = height/aHeight;
         float scale = scaleX; 
    	
         if(scaleX < scaleY)
         {
         scale = scaleY; 
         }
         */
    	
        return scale;
    }

    public int getScaledHeight(int aWidth, int aHeight)
    {
        return (int) (this.getScale(aWidth, aHeight) * aHeight);
    }
}
