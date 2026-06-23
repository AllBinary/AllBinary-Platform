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
package org.allbinary.graphics.draw;

import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author user
 */
public class KeyValueDrawString implements UpdateMyFontInterface {
    
    private MyFontProcessor myFontProcessor = new UpdateMyFontProcessor(this);
    
    private final String LABEL;

    private final int labelX;
    private int valueX;

    private String value = StringUtil.getInstance().EMPTY_STRING;

    public KeyValueDrawString(String label, int x)
    {
        this.LABEL = label;
        this.labelX = x;
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        final int labelWidth = graphics.getFont().stringWidth(this.LABEL) - this.LABEL.length();
        this.valueX = labelWidth + this.labelX;
        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    public void paint(final Graphics graphics, final int y)
    {
        this.myFontProcessor.process(graphics);
        
        graphics.drawString(this.LABEL,this.labelX, y, 0);

        graphics.drawString(this.value,this.valueX, y, 0);
    }

    public void update(String value)
    {
        if(value != null) {
            this.value = value;
        }
    }
}
