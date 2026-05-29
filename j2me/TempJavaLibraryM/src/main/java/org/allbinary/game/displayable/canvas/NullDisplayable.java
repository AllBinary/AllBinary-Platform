package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class NullDisplayable extends Canvas
{
    private static final NullDisplayable SINGLETON = new NullDisplayable();
    
    public static final NullDisplayable getInstance()
    {
        return NullDisplayable.SINGLETON;
    }
    
    private NullDisplayable()
    {
    }
    
    @Override
    public void paint(Graphics graphics)
    {
    }
}
