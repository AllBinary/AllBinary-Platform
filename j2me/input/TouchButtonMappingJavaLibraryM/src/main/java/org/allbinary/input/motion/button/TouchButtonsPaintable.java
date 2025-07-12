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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.util.BasicArrayList;

public class TouchButtonsPaintable extends Paintable
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    protected final BasicColor foregroundBasicColor;
    protected final int foregroundColor;
    
    protected final BasicColorSetUtil basicSetColorUtil = 
        BasicColorSetUtil.getInstance();
    
    private final TouchButtonFactory touchButtonFactory = 
        TouchButtonFactory.getInstance();
    
    public TouchButtonsPaintable(BasicColor basicColor)
    {
        this.foregroundBasicColor = basicColor;
        this.foregroundColor = basicColor.intValue();
    }
    
    public void paint(Graphics graphics)
    {
        BasicArrayList list = touchButtonFactory.getList();

        //PreLogUtil.put("List " + commonStrings.TOTAL_LABEL + list.size(), this, canvasStrings.PAINT);

        this.basicSetColorUtil.setBasicColor(
                graphics, this.foregroundBasicColor, this.foregroundColor);

        TouchButton touchButton;
        for (int index = list.size(); --index >= 0;)
        {
            touchButton = (TouchButton) list.objectArray[index];
            touchButton.paint(graphics);
        }
        
        for (int index = list.size(); --index >= 0;)
        {
            touchButton = (TouchButton) list.objectArray[index];
            touchButton.paintHint(graphics);
        }        
    }
    
}
