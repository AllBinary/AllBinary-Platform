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
package org.allbinary.media.image.comparison.motion;

import java.awt.*;
import java.util.Vector;

public class MotionRectangle implements Cloneable
{
    private Rectangle rectangle;
    private Vector pixelDeltaVector;
    
    public MotionRectangle(Rectangle rectangle)
    {
        this.setRectangle(rectangle);
        this.setPixelDeltaVector(new Vector());
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle)
    {
        this.rectangle = rectangle;
    }

    public Vector getPixelDeltaVector()
    {
        return pixelDeltaVector;
    }

    public void setPixelDeltaVector(Vector pixelDeltaVector)
    {
        this.pixelDeltaVector = pixelDeltaVector;
    }
    
    public Object clone()
    {
        MotionRectangle motionRectangle = 
           new MotionRectangle((Rectangle) this.getRectangle().clone());
        motionRectangle.setPixelDeltaVector((Vector) this.getPixelDeltaVector().clone());
        return motionRectangle;
    }
}
