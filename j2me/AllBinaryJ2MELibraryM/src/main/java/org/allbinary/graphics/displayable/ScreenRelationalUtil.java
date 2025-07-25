package org.allbinary.graphics.displayable;

import javax.microedition.lcdui.Image;


public class ScreenRelationalUtil
{
    private static final ScreenRelationalUtil instance = new ScreenRelationalUtil();

    public static ScreenRelationalUtil getInstance()
    {
        return instance;
    }

    public float getScale(final Image image)
    {
        return this.getScale(image.getWidth(), image.getHeight());
    }

    public float getScale(final int aWidth, final int aHeight) {
        return this.getScale(aWidth, aHeight, 20);
    }
        
    public float getScale(final int aWidth, final int aHeight, final int add)
    {
        final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();

        final int[] last = displayInfoSingleton.getLast();
        final float width = (float) last[displayInfoSingleton.WIDTH];
        final float height = (float) last[displayInfoSingleton.HEIGHT];

        float largestSize = width;

    	if(largestSize < height)
        {
            largestSize = height;
        }

        float scale = (largestSize) / aWidth;

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

    public int getScaledHeight(final int aWidth, final int aHeight)
    {
        return (int) (this.getScale(aWidth, aHeight) * aHeight);
    }

    public int getScaledHeight(final int aWidth, final int aHeight, final int add)
    {
        return (int) (this.getScale(aWidth, aHeight, add) * aHeight);
    }
}
