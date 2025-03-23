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
package org.allbinary.canvas;

import org.allbinary.string.CommonSeps;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.system.Memory;

public class SpecialMessageUtil
{
    private static final SpecialMessageUtil SINGLETON = new SpecialMessageUtil();
    
    /*
    public long totalYawLeft = 0;
    public long totalPitchUp = 0;
    public long totalRollLeft = 0;
    public long totalYawRight = 0;
    public long totalPitchDown = 0;
    public long totalRollRight = 0;
    public long totalYawNon = 0;
    public long totalPitchNon = 0;
    public long totalRollNon = 0;
    */
    
    public static final SpecialMessageUtil getInstance()
    {
        return SINGLETON;
    }
    
    public String get()
    {
        final StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(Memory.getInfo());
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(GameStatisticsFactory.getInstance().toString());

        //stringBuffer.append(" Available Sensors: ");
        //stringBuffer.append(AllBinarySensorManager.getInstance()
          //      .getSensorNamesList().toString());
        
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        
        //stringBuffer.append(OpenGLCapabilities.getInstance().toString());
        
        stringBuffer.append(" Option: ");
        if(Features.getInstance().isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT))
        {
            stringBuffer.append(OpenGLFeatureFactory.getInstance().OPENGL_AUTO_SELECT.getName());
        }
        else
        {
            stringBuffer.append(OpenGLFeatureFactory.getInstance().OPENGL_MINIMUM.getName());
        }
        
        /*
        stringBuffer.append(" TotalYawsLeft: ");
        stringBuffer.append(this.totalYawLeft);
        stringBuffer.append(" TotalPitchsUp: ");
        stringBuffer.append(this.totalPitchUp);
        stringBuffer.append(" TotalRollsLeft: ");
        stringBuffer.append(this.totalRollLeft);
        
        stringBuffer.append(" TotalYawsRight: ");
        stringBuffer.append(this.totalYawRight);
        stringBuffer.append(" TotalPitchDown: ");
        stringBuffer.append(this.totalPitchDown);
        stringBuffer.append(" TotalRollsRight: ");
        stringBuffer.append(this.totalRollRight);

        stringBuffer.append(" TotalYawsNon: ");
        stringBuffer.append(this.totalYawNon);
        stringBuffer.append(" TotalPitchNon: ");
        stringBuffer.append(this.totalPitchNon);
        stringBuffer.append(" TotalRollsNon: ");
        stringBuffer.append(this.totalRollNon);
        */

        return stringBuffer.toString();
    }
}
