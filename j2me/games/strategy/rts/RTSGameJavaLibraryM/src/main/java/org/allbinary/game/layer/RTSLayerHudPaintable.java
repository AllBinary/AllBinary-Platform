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
import org.allbinary.AndroidUtil;
import org.allbinary.game.part.weapon.BasicWeaponPart;
import org.allbinary.graphics.font.MyFont;

/**
 * 
 * @author user
 */
public class RTSLayerHudPaintable extends SelectionHudPaintable
{
    private static final RTSLayerHudPaintable instance = new RTSLayerHudPaintable();
    
    public static final RTSLayerHudPaintable getInstance()
    {
        return instance;
    }
    
    private final MyFont myFont = MyFont.getInstance();
    
    private String[] weaponProperties = StringUtil.getInstance().getArrayInstance();

    private RTSLayer rtsLayer;
    
    protected int costY;
    protected int costY1;
        
    private RTSLayerHudPaintable()
    {
    }

    public void updateSelectionInfo()
    {        
        final int charHeight = this.myFont.DEFAULT_CHAR_HEIGHT;
        
        this.setName(this.getRtsLayer().getName());
        
        final BasicWeaponPart partInterface = (BasicWeaponPart) 
            this.getRtsLayer().getPartInterfaceArray()[0];

        this.weaponProperties = partInterface.getWeaponProperties().toStringArray();

        this.costY1 = (y + ((weaponProperties.length + 1) * charHeight));

        if(!AndroidUtil.isAndroid())
        {
            this.costY = this.costY1;
        } else
        {
            this.costY = (y + ((weaponProperties.length + 2) * charHeight));
        }
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        
        final int charHeight = this.myFont.DEFAULT_CHAR_HEIGHT;
        
        int size = weaponProperties.length;
        for (int index = 0; index < size; index++)
        {
            graphics.drawString(weaponProperties[index], this.textX, y
                    + ((index + 1) * charHeight), 0);
        }
    }

    public void setRtsLayer(RTSLayer rtsLayer)
    {
        this.rtsLayer = rtsLayer;
    }

    private RTSLayer getRtsLayer()
    {
        return rtsLayer;
    }
}
