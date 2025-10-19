/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
import javax.microedition.lcdui.Screen;

import org.allbinary.graphics.form.item.CustomItem;
import org.allbinary.graphics.form.item.CustomItemInterface;

import org.allbinary.graphics.color.BasicColor;

/**
 *
 * @author user
 */
public class TileLayerItem extends CustomItem implements CustomItemInterface
{
    //private Screen owner;

    private final AllBinaryTiledLayer tiledLayer;

    public TileLayerItem(
        String label, AllBinaryTiledLayer tiledLayer, int layout, String altText,
        BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(label, backgroundBasicColor, foregroundBasicColor);

        this.tiledLayer = tiledLayer;
    }

    @Override
    public void setOwner(Screen owner)
    {
        //this.owner = owner;
    }

    @Override
    public int getMinimumWidth()
    {
        return this.tiledLayer.getWidth();
    }

    @Override
    public int getMinimumHeight()
    {
        return this.tiledLayer.getHeight();
    }

    protected int getMinContentHeight()
    {
        return this.tiledLayer.getHeight();
    }

    protected int getMinContentWidth()
    {
        return this.tiledLayer.getWidth();
    }

    protected int getPrefContentHeight(int width)
    {
        return this.tiledLayer.getHeight();
    }

    protected int getPrefContentWidth(int height)
    {
        return this.tiledLayer.getWidth();
    }

    //protected void paint(Graphics g, int w, int h);
    @Override
    public void paint(Graphics graphics, int x, int y)
    {
        //int layout = this.getLayout();

        this.tiledLayer.setPosition(x, y, this.tiledLayer.getZP());
        this.tiledLayer.paint(graphics);
    }
    
    @Override
    public void paintUnselected(Graphics graphics, int x, int y)
    {
        
    }

}
