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

package org.allbinary.game.layer;

import javax.microedition.lcdui.Graphics;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class UpgradableWideRTSLayerPaintable extends RTSLayerCompositePaintable
{
    private String cost = StringUtil.getInstance().EMPTY_STRING;
    
    public UpgradableWideRTSLayerPaintable(UpgradableRTSLayerHudPaintable upgradableRTSLayerHudPaintable)
    {
        super(upgradableRTSLayerHudPaintable);
    }
    
    @Override
    public void update(RTSLayer rtsLayer)
    {
        super.update(rtsLayer);
        
        this.setCost(new StringMaker().append(this.getUpgradeCost()).append(CommonSeps.getInstance().SPACE).append(this.getDownGradeCost()).toString());
    }

    @Override
    public void paint(Graphics graphics)
    {
        graphics.drawString(this.getCost(), this.upgradableRTSLayerHudPaintable.textX, this.upgradableRTSLayerHudPaintable.costY, 0);
    }
    
    protected void setCost(String cost)
    {
        this.cost = cost;
    }

    protected String getCost()
    {
        return cost;
    }

}
