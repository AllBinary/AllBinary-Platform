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
package org.allbinary.input.automation;

import java.awt.Point;
import java.awt.Rectangle;
import javax.help.HelpSet;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class AbstractInputRobot
{
    private HelpSet helpSet;
    
    public AbstractInputRobot(HelpSet helpSet)
    {
        this.setHelpSet(helpSet);
    }
    
    public HelpSet getHelpSet()
    {
        return this.helpSet;
    }
    
    public void mouseMove(Point point)
    {
        this.mouseMove(point.x, point.y);
        
        LogUtil.put(LogFactory.getInstance(
            "Moved Mouse To: x: " + point.x + " y: " + point.y,
            this, "moveMouse"));
    }
    
    public void mouseMoveToTarget(Rectangle rectangle, Integer x, Integer y) throws Exception
    {
        Point point = PointHelper.getCenterPoint(rectangle);
        
        this.mouseMove(point.x + x, point.y + y);
        
        LogUtil.put(LogFactory.getInstance(
            "Moved Mouse To: x: " + point.x + " y: " + point.y + " in the middle of: " + rectangle,
            this, "moveMouseToTarget"));
    }
    
    public void mouseMoveToTarget(Rectangle rectangle) throws Exception
    {
        this.mouseMoveToTarget(rectangle, 0, 0);
    }
    
    public void mouseMove(Integer x, Integer y) {
        throw new RuntimeException();
    }

    private void setHelpSet(HelpSet helpSet)
    {
        this.helpSet = helpSet;
    }
}
