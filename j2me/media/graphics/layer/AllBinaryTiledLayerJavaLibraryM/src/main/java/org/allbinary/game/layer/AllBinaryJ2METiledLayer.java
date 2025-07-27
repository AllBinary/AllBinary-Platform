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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.TiledLayer;

import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

public class AllBinaryJ2METiledLayer extends AllBinaryTiledLayer
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    //private final StringMaker stringMaker = new StringMaker();
    
    protected final TiledLayer tiledLayer;
    protected final int debugColor;

    public AllBinaryJ2METiledLayer(final Integer dataId, final TiledLayer tiledLayer, final int[][] mapTwoDArray, final int debugColor)
    {
        //TWB LayerInterface to Layer
        //super(tiledLayer.getWidth(), tiledLayer.getHeight());
        super(dataId, 
                tiledLayer.getWidth(), tiledLayer.getHeight(), 
                tiledLayer.getCellWidth(), tiledLayer.getCellHeight());

        this.tiledLayer = tiledLayer;
        this.debugColor = debugColor;

        this.setCells(mapTwoDArray);
        //final CommonStrings commonStrings = CommonStrings.getInstance();
        //logUtil.put(stringMaker.toString(), this, commonStrings.PROCESS);
    }

    //TWB LayerInterface to Layer
   /*   
    public int getHeight()
    {
    return this.tiledLayer.getHeight();
    }

    //TWB LayerInterface to Layer
    public int getWidth()
    {
    return this.tiledLayer.getWidth();
    }

    //TWB LayerInterface to Layer
    public int getX()
    {
    return this.tiledLayer.getXP();
    }

    //TWB LayerInterface to Layer
    public int getY()
    {
    return this.tiledLayer.getYP();
    }

    public int getZ()
    {
    return this.tiledLayer.getZP();
    }

    //TWB LayerInterface to Layer
    public boolean isVisible()
    {
    return this.tiledLayer.isVisible();
    }
     */

    public void paint(final Graphics graphics)
    {
        this.tiledLayer.paint(graphics);
        
        //this.paintDebug(graphics);

    }

    final Font font2 = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, 4);
    public void paintDebug(final Graphics graphics)
    {
        //logUtil.put(commonStrings.START, this, "paint");
        final Font font = graphics.getFont();

        graphics.setFont(font2);
        graphics.setColor(debugColor);
        //graphics.drawString("TiledLayer: (" + this.getX() + ", " + this.getY() + ")", this.getX(), this.getY(), 0);
        
        int x = this.tiledLayer.getX();
        int y = this.tiledLayer.getY();

        int firstColumn = 0;
        int firstRow = 0;
        final int totalColumns = this.tiledLayer.getColumns();
        final int totalRows = this.tiledLayer.getRows();

        int x0 = x;

        //int imgCols = img.getWidth() / tileWidth;
        //int imgRows = img.getHeight() / tileHeight;
        int tile = 0;

        final int tileHeight = this.tiledLayer.getCellHeight();
        final int tileWidth = this.tiledLayer.getCellWidth();

        final CommonSeps commonSeps = CommonSeps.getInstance();
        final StringMaker stringMaker = new StringMaker();
        for (int rowIndex = firstRow; rowIndex < totalRows; rowIndex++, y += tileHeight) {
            x = x0;
            for (int columnIndex = firstColumn; columnIndex < totalColumns; columnIndex++, x += tileWidth) {
                tile = this.tiledLayer.getCell(columnIndex, rowIndex);
                if (tile < 0) {
                    tile = this.tiledLayer.getAnimatedTile(tile);
                }
                if (tile == 0) {
                    continue;
                }

                tile--;
                stringMaker.delete(0, stringMaker.length());
                graphics.drawString(stringMaker.append(columnIndex).append(commonSeps.COMMA).append(rowIndex).append(commonSeps.COLON).append(tile).toString(), x + 5, y + 10, 0);
            }
        }
     
        graphics.setFont(font);
    }
    
    public void move(final int dx, final int dy)
    {
        //logUtil.put(new StringMaker().append("TiledLayer: ").append(PositionStrings.getInstance().X_LABEL).append(dx).append(PositionStrings.getInstance().Y_LABEL).append(dy).toString(), this, "move");
        this.tiledLayer.move(dx, dy);
        //TWB LayerInterface to Layer
        super.move(-dx, -dy);
    }

    public void setPosition(final int x, final int y, final int z)
    {
        //logUtil.put(new StringMaker().append("TiledLayer: x: ").append(x).append(" y: ").append(y).append(" z: ").append(z).toString(), this, "setPosition");
        this.tiledLayer.setPosition(x, y);
        super.setPosition(-x, -y, z);
    }

    //public void setVisible(boolean visible)
    //{
        ////this.tiledLayer.setVisible(true);
        //TWB LayerInterface to Layer

        //TWB - Close to release Change from true to a variable
        //super.setVisible(visible);
    //}

    public TiledLayer getTiledLayer()
    {
        return tiledLayer;
    }

    public int getCellWidth()
    {
        return this.tiledLayer.getCellWidth();
    }

    public int getCellHeight()
    {
        return this.tiledLayer.getCellHeight();
    }

    public int getCell(final int col, final int row)
    {
        return this.tiledLayer.getCell(col, row);
    }

    public int getColumns()
    {
        return this.tiledLayer.getColumns();
    }

    public int getRows()
    {
        return this.tiledLayer.getRows();
    }

    public void setCell(final int col, final int row, final int index)
    {
//        if(tileIndex != 0) {
//            final CommonStrings commonStrings = CommonStrings.getInstance();
//            logUtil.put(new StringMaker().append("tileIndex: ").append(tileIndex).toString(), this, commonStrings.PROCESS);
//        }

        //stringMaker.append(index).append(',');

        this.tiledLayer.setCell(col, row, index);
    }
    
    public int getAnimatedTile(final int animationTileIndex) {
        return this.tiledLayer.getAnimatedTile(animationTileIndex);
    }
    
}
