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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.graphics.paint.InitUpdatePaintable;

public class OverlayPaintable
extends InitUpdatePaintable
{
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

    public void update()
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().UPDATE));
            //this.gameNotificationHud.processTick();
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(
                    CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

    public void paint(Graphics graphics)
    {
        //this.gameNotificationHud.paint(graphics);
    }
}
