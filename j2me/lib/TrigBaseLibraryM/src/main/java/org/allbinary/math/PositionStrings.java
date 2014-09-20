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
package org.allbinary.math;

public class PositionStrings
{
    private static final PositionStrings instance = new PositionStrings();

    public static PositionStrings getInstance()
    {
        return instance;
    }

    public final String X = "x";
    public final String Y = "y";

    public final String X_LABEL = "x: ";
    public final String Y_LABEL = "y: ";
    public final String Z_LABEL = "z: ";

    public final String DX_LABEL = "dx: ";
    public final String DY_LABEL = "dy: ";
    public final String DZ_LABEL = "dz: ";
}
