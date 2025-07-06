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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.logic.string.StringMaker;

public class RTSLayerCompositePaintable extends Paintable
{
    private String upgradeCost = StringUtil.getInstance().EMPTY_STRING;
    private String downGradeCost = StringUtil.getInstance().EMPTY_STRING;

    private final String UPGRADE = "Up: $";
    private final String DOWNGRADE = "Down: $-";

    protected final UpgradableRTSLayerHudPaintable upgradableRTSLayerHudPaintable;
    
    public RTSLayerCompositePaintable(UpgradableRTSLayerHudPaintable upgradableRTSLayerHudPaintable)
    {
        this.upgradableRTSLayerHudPaintable = upgradableRTSLayerHudPaintable;
    }
    
    public void update(RTSLayer rtsLayer)
    {
        if (rtsLayer.isUpgradeable())
        {
            this.setUpgradeCost(new StringMaker().append(UPGRADE).append(rtsLayer.getUpgradeCost()).toString());
        } else
        {
            this.setUpgradeCost(StringUtil.getInstance().EMPTY_STRING);
        }

        if (rtsLayer.isDowngradeable())
        {
            this.setDownGradeCost(new StringMaker().append(DOWNGRADE).append(rtsLayer.getDowngradeCost()).toString());
        } else
        {
            this.setDownGradeCost(StringUtil.getInstance().EMPTY_STRING);
        }
    }
    
    public void paint(Graphics graphics)
    {
        graphics.drawString(this.getDownGradeCost(), this.upgradableRTSLayerHudPaintable.textX, this.upgradableRTSLayerHudPaintable.costY1, 0);
        graphics.drawString(this.getUpgradeCost(), this.upgradableRTSLayerHudPaintable.textX, this.upgradableRTSLayerHudPaintable.costY, 0);
    }

    protected void setUpgradeCost(String upgradeCost)
    {
        this.upgradeCost = upgradeCost;
    }

    protected String getUpgradeCost()
    {
        return upgradeCost;
    }

    protected void setDownGradeCost(String downGradeCost)
    {
        this.downGradeCost = downGradeCost;
    }

    protected String getDownGradeCost()
    {
        return downGradeCost;
    }    
    
}
