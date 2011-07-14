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
package allbinary.media.image.comparison;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface ImageComparatorConstraintsInterface
{
    int getMaxNonMatchingPixelDeltas();
    //Vector getAvoidVector();

    boolean isCollisionWithAvoidRectangles(Rectangle rectangle);
    boolean isCollisionWithAvoidRectangles(Point point);
    
    boolean isColorAllowed(int frame, Point point, Color color);
    
    boolean isImageValid(BufferedImage bufferedImage) throws Exception;
    
    boolean isFrameAllowed(int frame);
}
