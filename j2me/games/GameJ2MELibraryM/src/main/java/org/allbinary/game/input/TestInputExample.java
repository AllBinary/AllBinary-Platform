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
package org.allbinary.game.input;

import javax.microedition.lcdui.Graphics;

public class TestInputExample
implements TestInputInterface
{
    private int testValue;
    
    public void up()
    {
        
    }
    
    public void down()
    {
        
    }
    
    public void left()
    {
        this.testValue++;
    }
    
    public void right()
    {
        this.testValue--;
    }
    
    public void strafeLeft()
    {
        
    }
    
    public void strafeRight()
    {
        
    }
    
    public void paint(Graphics graphics)
    {
        
    }
    
    public void paintThreed(Graphics graphics)
    {
    }    
}
