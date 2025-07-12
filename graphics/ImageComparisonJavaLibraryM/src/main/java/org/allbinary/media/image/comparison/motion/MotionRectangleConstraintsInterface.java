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
import java.awt.image.BufferedImage;

public interface MotionRectangleConstraintsInterface
{
    int getMaxMotionRectangles();
    
    Dimension getMinDimension();
    int getMinArea();
    Dimension getMaxDimension();
    
    boolean isTooSmall(Rectangle rectangle);
    boolean isAreaTooSmall(Rectangle rectangle);
    boolean isTooBig(Rectangle rectangle);
    
    boolean isValid(Long frame, BufferedImage bufferedImage, Rectangle rectangle)
    throws Exception;
}
