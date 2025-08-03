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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.paint.InitUpdatePaintable;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class OverlayPaintable
extends InitUpdatePaintable
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    //private final GameNotificationHud gameNotificationHud;
    
    public OverlayPaintable()
    throws Exception
    {
        //BasicHudFactory basicHudFactory =
          //  BasicHudFactory.getInstance();
        
        //this.gameNotificationHud = new PlayerGameNotificationHud(
          //      basicHudFactory.TOPCENTER,
            //    basicHudFactory.HORIZONTAL,
              //  14, 40, 2, BasicColor.RED);
    }

    @Override
    public void update()
    {
        try
        {
            //logUtil.put(commonStrings.START, this, commonStrings.UPDATE);
            //this.gameNotificationHud.processTick();
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }

    @Override
    public void paint(Graphics graphics)
    {
        //this.gameNotificationHud.paint(graphics);
    }
}
