package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.canvas.BaseGameStatistics;
import org.allbinary.canvas.GameStatisticsFactory;

public class NullDisplayable extends Canvas
{
    private static final NullDisplayable SINGLETON = new NullDisplayable();
    
    public static final NullDisplayable getInstance()
    {
        return SINGLETON;
    }
    
    private NullDisplayable()
    {
        
    }
    
    private final BaseGameStatistics baseGameStatistics = 
            GameStatisticsFactory.getInstance();
    
    @Override
    public void paint(Graphics graphics)
    {
        baseGameStatistics.nextRefresh();
    }
}
