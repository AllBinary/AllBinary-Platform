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
package allbinary.media.image.comparison.motion;

import allbinary.media.image.comparison.pixel.PixelDelta;
import java.awt.Rectangle;

import java.util.Iterator;
import java.util.Vector;

import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.collision.RectangleCollisionUtil;
import allbinary.media.image.comparison.ImageComparisonResult;

public class MotionRectangles
{
    private final String name;
    
    private Vector rectangleVector;

    private ImageComparisonResult imageComparisonResult;
    
    protected final int MAX_PIXEL_DISTANCE_THRESHOLD = 2;

    //GenerateImageWithBoundingBoxesAroundMovingArtifacts
    public MotionRectangles(String name, 
        ImageComparisonResult imageComparisonResult)
        throws Exception
    {
        this.name = name;
        this.setImageComparisonResult(imageComparisonResult);
        this.setVector(new Vector());
    }
        
    public Vector getVector()
    {
        return rectangleVector;
    }
    
    public void setVector(Vector rectangleVector)
    {
        this.rectangleVector = rectangleVector;
    }

    public String getName()
    {
        return name;
    }
    
    public ImageComparisonResult getImageComparisonResult()
    {
        return imageComparisonResult;
    }

    public void setImageComparisonResult(ImageComparisonResult imageComparisonResult)
    {
        this.imageComparisonResult = imageComparisonResult;
    }
    
}
