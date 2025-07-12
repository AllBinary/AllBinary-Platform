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
package org.allbinary.media.image.comparison;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.allbinary.graphics.GPoint;

public interface ImageComparatorConstraintsInterface
{
    int getMaxNonMatchingPixelDeltas();
    //Vector getAvoidVector();

    boolean isCollisionWithAvoidRectangles(Rectangle rectangle);
    boolean isCollisionWithAvoidRectangles(GPoint point);
    
    boolean isColorAllowed(int frame, GPoint point, Color color);
    
    boolean isImageValid(BufferedImage bufferedImage) throws Exception;
    
    boolean isFrameAllowed(int frame);
}
