/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

package org.allbinary.game.layer.item;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.game.layer.CostLayerInterfaceFactoryInterface;
import org.allbinary.game.rts.technology.event.TechEventListenerInterface;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.math.PrimitiveLongUtil;
import org.allbinary.logic.util.event.EventStrings;

public class RTSLayerCostAnimation
extends Animation
implements TechEventListenerInterface
{
    private final MyFont myFont = MyFont.getInstance();
    
    private final PrimitiveLongUtil primitiveLongUtil = new PrimitiveLongUtil(10000);

    private final Image image;
    
    private char[] costString;
    private int len;

    private final String DOLLAR = "$";

    private final int adjustedCostX = myFont.stringWidth(DOLLAR);
    
    private final CostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface;
    
    public RTSLayerCostAnimation(Image image,
            CostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
    throws Exception
    {   
        this.image = image;
        this.layerInterfaceFactoryInterface = layerInterfaceFactoryInterface;
        
        this.update();
    }
    
    public void onEvent(AllBinaryEventObject event)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    public void onTechEvent(AllBinaryEventObject event)
    {
        try
        {
            this.update();
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onTechEvent", e));
        }
    }

    public void update()
    throws Exception
    {
        this.len = 0;
        
        this.costString = this.primitiveLongUtil.getCharArray(
                this.layerInterfaceFactoryInterface.getCost());
            
        this.len = this.primitiveLongUtil.getCurrentTotalDigits();
    }
    
    //-yOffset
    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x, y);

        final int adjustedCostY = image.getHeight() - (myFont.DEFAULT_CHAR_HEIGHT);
        
        int xa = x+2;
        graphics.drawString(DOLLAR, xa, y + adjustedCostY, 0);

        graphics.drawChars(costString, 0, this.len, 
                x + adjustedCostX, y + adjustedCostY, 0);
    }
}
