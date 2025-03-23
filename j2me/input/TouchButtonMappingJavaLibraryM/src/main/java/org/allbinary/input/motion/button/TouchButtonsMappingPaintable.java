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
package org.allbinary.input.motion.button;

import javax.microedition.lcdui.Graphics;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.graphics.CellPositionFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.paint.Paintable;

public class TouchButtonsMappingPaintable extends Paintable
{
    protected int foregroundColor;
    private Paintable[][] paintableTable;

    private TouchButtonLocationHelper touchButtonLocationHelper = new TouchButtonLocationHelper();
    
    public TouchButtonsMappingPaintable(
            BasicColor basicColor)
    {
        this.foregroundColor = basicColor.intValue();

        this.init();
    }

    private void init()
    {
        try
        {
            this.paintableTable = this.createPaintableTable();

            //BasicArrayList list = TouchButtonFactory.getInstance().getList();

            /*
            for (int index = list.size() - 1; index >= 0; index--)
            {
                TouchButton touchButton = (TouchButton) list.get(index);
                CellPosition cellPosition = touchButton.getCellPosition();

                this.paintableTable[cellPosition.getColumn()][cellPosition.getRow()] = touchButton; 
            }
            */

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "updateRectangle", e));
        }
    }

    private Paintable[][] createPaintableTable() throws Exception
    {
        int totalColumns = touchButtonLocationHelper.getTotalColumns();
        int totalRows = touchButtonLocationHelper.getTotalRows();

        Paintable[][] paintableTable = new Paintable[totalColumns][totalRows];

        CellPositionFactory cellPositionFactory = CellPositionFactory.getInstance();

        CommonButtons commonButtons = CommonButtons.getInstance();
        
        for (int index = totalColumns - 1; index >= 0; index--)
        {
            for (int rowIndex = totalRows - 1; rowIndex >= 0; rowIndex--)
            {
                paintableTable[index][rowIndex] = new TouchButton(
                        null, TouchButtonBlankResource.getInstance(),
                        commonButtons.NORMAL_BUTTON, 
                        cellPositionFactory.getInstance(index, rowIndex),
                        this.touchButtonLocationHelper.getColumnsRemainderHalf(), 
                        this.touchButtonLocationHelper.getRowsRemainderHalf());
            }
        }

        return paintableTable;
    }

    public void paint(Graphics graphics)
    {
        int totalColumns = touchButtonLocationHelper.getTotalColumns();
        int totalRows = touchButtonLocationHelper.getTotalRows();

        for (int index = totalColumns - 1; index >= 0; index--)
        {
            for (int rowIndex = totalRows - 1; rowIndex >= 0; rowIndex--)
            {
                paintableTable[index][rowIndex].paint(graphics);
            }
        }
    }

}
