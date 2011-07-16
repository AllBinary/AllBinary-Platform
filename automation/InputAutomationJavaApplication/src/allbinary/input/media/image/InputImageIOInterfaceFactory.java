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
package allbinary.input.media.image;

import allbinary.media.image.io.ImageIOInterface;
import allbinary.input.media.image.capture.CapturedImageInputOutput;
import allbinary.media.image.comparison.ComparisonImageInputOutput;
import allbinary.media.image.comparison.motion.MotionRectanglesImageInputOutput;

public class InputImageIOInterfaceFactory
{
    
    private InputImageIOInterfaceFactory()
    {
    }
    
    public static ImageIOInterface getInstance(InputImageType imageType)
    throws Exception
    {
        if(imageType == InputImageType.CAPTURE)
        {
            return new CapturedImageInputOutput();
        }
        else
        if(imageType == InputImageType.COMPARISON)
        {
            return new ComparisonImageInputOutput();
        }
        else
        if(imageType == InputImageType.MOTION)
        {
            return new MotionRectanglesImageInputOutput();
        }
        else
        {
            throw new Exception("No ImageIOInterface for: " + imageType.toString());
        }
    }
}
