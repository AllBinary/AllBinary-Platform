package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.canvas.BaseGameStatistics;
import org.allbinary.canvas.GameStatisticsFactory;
import org.allbinary.graphics.displayable.MyCanvas;

public class NullDisplayable extends MyCanvas
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
    
    public void paint(Graphics graphics)
    {
        baseGameStatistics.nextRefresh();
    }
}
