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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.AndroidUtil;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.part.weapon.BasicWeaponPart;
import org.allbinary.graphics.font.UpdateMyFontInterface;

/**
 *
 * @author user
 */
public class RTSLayerHudPaintable extends SelectionHudPaintable 
    implements UpdateMyFontInterface {

    private static final RTSLayerHudPaintable instance = new RTSLayerHudPaintable();

    public static final RTSLayerHudPaintable getInstance() {
        return RTSLayerHudPaintable.instance;
    }
    
    private String[] weaponProperties = StringUtil.getInstance().getArrayInstance();

    private CollidableDestroyableDamageableLayer rtsLayer = CollidableDestroyableDamageableLayer.getNullInstance();

    protected int costY;
    protected int costY1;
    
    private int fontHeight;

    private RTSLayerHudPaintable() {
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        super.updateMeasurement(graphics);

        final Font font = graphics.getFont();
        this.fontHeight = font.getHeight();
        
        this.costY1 = (this.y + ((this.weaponProperties.length + 1) * this.fontHeight));

        if (!AndroidUtil.isAndroid()) {
            this.costY = this.costY1;
        } else {
            this.costY = (this.y + ((this.weaponProperties.length + 2) * this.fontHeight));
        }

    }
    
    @Override
    public void updateSelectionInfo() {

        this.setName(this.getRtsLayer().getName());

        final BasicWeaponPart partInterface = (BasicWeaponPart) this.getRtsLayer().getPartInterfaceArray()[0];

        this.weaponProperties = partInterface.getWeaponProperties().toStringArray();

        this.myFontProcessor = this.updateMyFontProcessor;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        final int size = this.weaponProperties.length;
        for (int index = 0; index < size; index++) {
            graphics.drawString(this.weaponProperties[index], this.textX, 
                this.y + ((index + 1) * this.fontHeight), 0);
        }
    }

    public void setRtsLayer(RTSLayer rtsLayer) {
        this.rtsLayer = rtsLayer;
    }

    private CollidableDestroyableDamageableLayer getRtsLayer() {
        return this.rtsLayer;
    }

}
