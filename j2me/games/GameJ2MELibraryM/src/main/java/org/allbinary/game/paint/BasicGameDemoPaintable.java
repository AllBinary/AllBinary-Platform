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
package org.allbinary.game.paint;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.StatePaintable;

/**
 * 
 * @author Berthelot, Travis
 * @version 1.0
 */
public class BasicGameDemoPaintable 
    extends StatePaintable
{
    private final Paintable mainDemoStatePaintable;
    private final Paintable ownershipPaintable;
    private final Paintable helpPaintableInterface;
    
    private Paintable currentStatePaintable;

    public BasicGameDemoPaintable(
            Paintable mainDemoStatePaintable, Paintable ownershipPaintable, Paintable helpPaintableInterface)
    {
        this.mainDemoStatePaintable = mainDemoStatePaintable; 
        this.setCurrentStatePaintable(NullPaintable.getInstance());
        
        this.ownershipPaintable = ownershipPaintable;
        this.helpPaintableInterface = helpPaintableInterface;
    }
    
    public void setState(int state)
    {
        if (state == 0)
        {
            //LogUtil.put(LogFactory.getInstance("Setting Main Demo State Paintable", this, "setState"));
            this.setCurrentStatePaintable(this.getMainDemoStatePaintable());
        }
        else if (state == 1)
        {
            //LogUtil.put(LogFactory.getInstance("Setting Help Paintable", this, "setState"));
            this.setCurrentStatePaintable(this.helpPaintableInterface);
        }
        else
        {
            //LogUtil.put(LogFactory.getInstance("Setting Null Paintable", this, "setState"));
            //this.currentStatePaintable = NullPaintable.getInstance();
            this.setCurrentStatePaintable(this.ownershipPaintable);
        }
        //LogUtil.put(LogFactory.getInstance("Paintable is now: ").append(this.currentStatePaintable, this, "setState"));
    }

    public void paint(Graphics graphics)
    {
        this.getCurrentStatePaintable().paint(graphics);
    }

    /**
     * @return the helpPaintableInterface
     */
    public Paintable getHelpPaintableInterface()
    {
        return helpPaintableInterface;
    }

    protected void setCurrentStatePaintable(Paintable currentStatePaintable)
    {
        this.currentStatePaintable = currentStatePaintable;
    }

    protected Paintable getCurrentStatePaintable()
    {
        return currentStatePaintable;
    }

    protected Paintable getMainDemoStatePaintable()
    {
        return mainDemoStatePaintable;
    }
}
