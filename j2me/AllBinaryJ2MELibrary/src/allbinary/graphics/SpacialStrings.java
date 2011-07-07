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
package allbinary.graphics;

public class SpacialStrings
{
    private static final SpacialStrings instance = new SpacialStrings();

    public static SpacialStrings getInstance()
    {
        return instance;
    }

    public final String WIDTH_LABEL = "Width: ";
    public final String HEIGHT_LABEL = "Height: ";
}
