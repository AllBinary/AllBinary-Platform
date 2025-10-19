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
package org.allbinary.game.layer.geographic.map;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.graphics.Anchor;
import org.allbinary.image.PaintableToImageUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.view.ViewPosition;

public class ImageMiniMapLayer extends MiniMapLayer
{
    private Image image = NullCanvas.NULL_IMAGE;

    // private
    public ImageMiniMapLayer(BasicGeographicMap geographicMapInterface,
            ViewPosition viewPosition) throws Exception
    {
        super(geographicMapInterface, viewPosition);
    }

    @Override
    protected void init() throws Exception
    {
        int aWidth = allBinaryTiledLayer.getWidth();
        int aHeight = allBinaryTiledLayer.getHeight();

        this.image = PaintableToImageUtil.getImage(allBinaryTiledLayer, aWidth, aHeight);
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    @Override
    public void paint(Graphics graphics)
    {
        graphics.drawImage(image, x, y, anchor);
        this.paintDots(graphics);
    }
}
