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
package allbinary.game.layer.geographic.map;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import allbinary.graphics.Anchor;
import allbinary.image.PaintableToImageUtil;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.view.ViewPosition;

public class ImageMiniMapLayer extends MiniMapLayer
{
    private Image image;

    // private
    public ImageMiniMapLayer(BasicGeographicMap geographicMapInterface,
            ViewPosition viewPosition) throws Exception
    {
        super(geographicMapInterface, viewPosition);
    }

    protected void init() throws Exception
    {
        this.image = PaintableToImageUtil.getImage(
                allBinaryTiledLayer, 
                allBinaryTiledLayer.getWidth(),
                allBinaryTiledLayer.getHeight());
    }
    
    private int anchor = Anchor.TOP_LEFT;
    
    public void paint(Graphics graphics)
    {
        graphics.drawImage(image, x, y, anchor);
        this.paintDots(graphics);
    }
}
