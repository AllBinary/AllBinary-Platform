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
package abcs.logic.java;

public class PrimitiveArrayToString
{
    public static String toString(float[] floats)
    {
        StringBuilder stringBuffer = new StringBuilder();
        for(int index = 0; index < floats.length; index++)
        {
            stringBuffer.append(floats[index]);
        }
        return stringBuffer.toString();
    }
}
