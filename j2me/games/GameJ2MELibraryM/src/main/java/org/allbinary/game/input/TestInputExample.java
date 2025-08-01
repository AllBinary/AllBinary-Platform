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
    
    @Override
    public void up()
    {
        
    }
    
    @Override
    public void down()
    {
        
    }
    
    @Override
    public void left()
    {
        this.testValue++;
    }
    
    @Override
    public void right()
    {
        this.testValue--;
    }
    
    @Override
    public void strafeLeft()
    {
        
    }
    
    @Override
    public void strafeRight()
    {
        
    }
    
    @Override
    public void paint(Graphics graphics)
    {
        
    }
    
    @Override
    public void paintThreed(Graphics graphics)
    {
    }    
}
