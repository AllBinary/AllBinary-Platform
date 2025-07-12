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
package org.allbinary.media.image.analysis;

import java.awt.*;

public interface ColorRangeInterface
{
    int getMinRed();
    void setMinRed(int minRed);
    int getMaxRed();
    void setMaxRed(int maxRed);
    int getMinGreen();
    void setMinGreen(int minGreen);
    int getMaxGreen();
    void setMaxGreen(int maxGreen);
    int getMinBlue();
    void setMinBlue(int minBlue);
    int getMaxBlue();
    void setMaxBlue(int maxBlue);
    
    boolean isInRange(Color color);
}
