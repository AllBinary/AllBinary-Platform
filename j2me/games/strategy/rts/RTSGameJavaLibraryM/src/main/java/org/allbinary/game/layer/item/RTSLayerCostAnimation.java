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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.game.layer.CostLayerInterfaceFactoryInterface;
import org.allbinary.game.rts.technology.event.TechEventListenerInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.math.PrimitiveLongUtil;
import org.allbinary.logic.util.event.EventStrings;

public class RTSLayerCostAnimation
extends Animation
implements TechEventListenerInterface, UpdateMyFontInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    private final PrimitiveLongUtil primitiveLongUtil = PrimitiveLongUtil.createPowerOfTen(10000);

    private final String DOLLAR = "$";
    
    private final Image image;
    
    private final CostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface;
    
    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private char[] costString = NullUtil.getInstance().NULL_CHAR_ARRAY;
    private int len;
    
    private int adjustedCostX;
    private int fontHeight = 0;
    
    public RTSLayerCostAnimation(Image image,
            CostLayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
    throws Exception
    {   
        this.image = image;
        this.layerInterfaceFactoryInterface = layerInterfaceFactoryInterface;
        
        this.update();
    }
    

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        this.adjustedCostX = font.stringWidth(this.DOLLAR);
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    @Override
    public void onEvent(AllBinaryEventObject event)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }
    
    @Override
    public void onTechEvent(AllBinaryEventObject event)
    {
        try
        {
            this.update();
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, "onTechEvent", e);
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
    @Override
    public void paintXY(Graphics graphics, int x, int y)
    {
        this.myFontProcessor.process(graphics);

        super.paintXY(graphics, x, y);

        final int adjustedCostY = this.image.getHeight() - this.fontHeight;
        
        int xa = x+2;
        graphics.drawString(this.DOLLAR, xa, y + adjustedCostY, 0);

        graphics.drawChars(this.costString, 0, this.len, 
                x + this.adjustedCostX, y + adjustedCostY, 0);
    }
}
