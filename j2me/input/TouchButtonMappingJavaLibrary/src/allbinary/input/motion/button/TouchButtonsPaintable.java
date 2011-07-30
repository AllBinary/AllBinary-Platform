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
package allbinary.input.motion.button;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorSetUtil;
import allbinary.graphics.paint.Paintable;

public class TouchButtonsPaintable extends Paintable
{
    protected final BasicColor foregroundBasicColor;
    protected final int foregroundColor;
    
    protected final BasicColorSetUtil basicColorUtil = 
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

        //PreLogUtil.put("List " + CommonStrings.getInstance().TOTAL_LABEL + list.size(), this, "paint");

        this.basicColorUtil.setBasicColor(
                graphics, this.foregroundBasicColor, this.foregroundColor);

        TouchButton touchButton;
        for (int index = list.size(); --index >= 0;)
        {
            touchButton = (TouchButton) list.get(index);
            touchButton.paint(graphics);
        }
    }
    
}
