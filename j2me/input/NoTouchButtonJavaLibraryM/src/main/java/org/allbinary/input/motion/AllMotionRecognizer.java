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
package org.allbinary.input.motion;

/**
 * 
 * @author user
 */
public class AllMotionRecognizer
{    
    private final int id;

    private static int index = 0;

    public AllMotionRecognizer()
    {
        this.id = index++;
    }
    
    public void processStartMotionEvent(int x, int y, int deviceId, int modifiers)
            throws Exception
    {
    }

    public void processEndMotionEvent(int x, int y, int deviceId, int modifiers)
            throws Exception
    {
    }

    public void processDraggedMotionEvent(int x, int y, int deviceId, int modifiers)
            throws Exception
    {
    }

    public void processMovedMotionEvent(int x, int y, int deviceId, int modifiers)
            throws Exception
    {
    }
    
    public int getId()
    {
        return id;
    }
}
