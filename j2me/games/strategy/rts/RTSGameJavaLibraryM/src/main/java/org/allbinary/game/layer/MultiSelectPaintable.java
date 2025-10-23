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

import org.allbinary.util.BasicArrayList;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.NullUtil;

public class MultiSelectPaintable 
extends SelectionHudPaintable
{
    
    private final BasicArrayList rootNameList = new BasicArrayList();

    private char[] totalCharArray = NullUtil.getInstance().NULL_CHAR_ARRAY;
    private String rootNamesString = StringUtil.getInstance().EMPTY_STRING;
    //private final char[][] totalArray = char[10];
    
    public MultiSelectPaintable()
    {
    }
    
    public void update(BasicArrayList list)
    {
        this.clear();
        
        int size = list.size();
        
        this.totalCharArray = this.getPrimitiveLongUtil().getCharArray(size);

        for(int index = list.size() - 1; index >= 0; index--)
        {
            RTSLayer rtsLayer = (RTSLayer) list.get(index);

            if(!this.rootNameList.contains(rtsLayer.getRootName()))
            {
                this.rootNameList.add(rtsLayer.getRootName());
            }
        }
     
        final String COMMA_SEP = CommonSeps.getInstance().COMMA_SEP;
        
        final StringMaker stringBuffer = new StringMaker();
        
        for(int index = this.rootNameList.size() - 1; index >= 0; index--)
        {
            String rootName = (String) this.rootNameList.get(index);
            
            stringBuffer.append(rootName);
            
            if(index != 0)
            {
                stringBuffer.append(COMMA_SEP);
            }
        }
        
        this.rootNamesString = stringBuffer.toString();
    }
    
    private void clear()
    {
        this.rootNameList.clear();
    }
    
    private final String TOTAL = "Total Selected: ";
    private final int totalWidth = MyFont.getInstance().stringWidth(TOTAL);
    
    private final int backgroundColor = BasicColorFactory.getInstance().GREY.intValue();
        //BasicColor.TRANSPARENT_GREY.intValue();
    
    @Override
    public void paint(Graphics graphics)
    {
        graphics.setColor(backgroundColor);
        //graphics.fillRect(this.getX(), y, this.getWidth(), this.getHeight());
        graphics.drawRect(this.getX(), y, this.getWidth(), this.getHeight());
        
        graphics.setColor(this.getColor());
        
        graphics.drawString(TOTAL, this.textX, y, 0);

        graphics.drawChars(this.totalCharArray, 0, 
                this.getPrimitiveLongUtil().getCurrentTotalDigits(), 
                this.textX + this.totalWidth, y, 0);
        
        final int textLine2Y = (y + myFont.DEFAULT_CHAR_HEIGHT);
        graphics.drawString(this.rootNamesString, 
                this.textX, textLine2Y, 0);
    }
}
