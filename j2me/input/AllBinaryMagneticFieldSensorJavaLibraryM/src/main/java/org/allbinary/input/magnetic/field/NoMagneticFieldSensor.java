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
package org.allbinary.input.magnetic.field;

import org.allbinary.input.AllBinarySensorListener;
import org.allbinary.logic.math.Orientation;

public class NoMagneticFieldSensor extends AllBinaryMagneticFieldSensor
implements AllBinarySensorListener
{
    public Orientation getOrientation() throws Exception
    {
        return Orientation.NONE;
    }
    
    @Override
    public void onSensorChange()
    {
    }
}
