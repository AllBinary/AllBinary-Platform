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

import org.allbinary.graphics.form.item.CustomImageItem;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.layer.LayerInterfaceFactoryInterface;

/**
 *
 * @author user
 */
public class LayerInterfaceFactoryImageItem extends CustomImageItem
{
    private final Animation[] animationArray;
    private final LayerInterfaceFactoryInterface layerInterfaceFactoryInterface;

    public LayerInterfaceFactoryImageItem(
       String label, Image img, int layout, String altText, 
       BasicColor basicColor, Animation[] animationArray,
       LayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
       throws Exception
    {
        super(label, img, layout, altText, basicColor);

        this.animationArray = animationArray;
        this.layerInterfaceFactoryInterface = layerInterfaceFactoryInterface;
    }

    public LayerInterfaceFactoryInterface getLayerInterfaceFactoryInterface()
    {
        return this.layerInterfaceFactoryInterface;
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        super.paint(graphics, x, y);
        
        //-yOffset
        for(int index = this.animationArray.length; --index >= 0;)
        {
            this.animationArray[index].paint(graphics, x, y);
        }
    }
}
