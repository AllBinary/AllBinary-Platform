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
package org.allbinary.logic.math;

public class ScaleFactorFactory
{
    private static final ScaleFactorFactory instance = new ScaleFactorFactory();

    public static ScaleFactorFactory getInstance()
    {
        return instance;
    }
    
    public int DEFAULT_SCALE_FACTOR = 10;
    public int DEFAULT_SCALE_VALUE = 1024;
}
