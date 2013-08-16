package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import allbinary.graphics.displayable.DisplayInfoSingleton;

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

    	float width = displayInfoSingleton.getLastWidth();
    	float height = displayInfoSingleton.getLastHeight();
    	
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
