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
package allbinary.input.automation.module.generic.constraints;

import java.awt.Color;
import java.awt.Point;

import allbinary.input.automation.module.ImageComparatorConstraints;
import java.awt.image.BufferedImage;

public class NoImageComparatorConstraints
    extends ImageComparatorConstraints
{
    public NoImageComparatorConstraints()
    {
        super(1);
        this.setMaxNonMatchingPixelDeltas(Integer.MAX_VALUE);
    }
    
    public boolean isColorAllowed(int frame, Point point, Color color)
    {
        return true;
    }

    public boolean isImageValid(BufferedImage bufferedImage)
    {
        return true;
    }
}
