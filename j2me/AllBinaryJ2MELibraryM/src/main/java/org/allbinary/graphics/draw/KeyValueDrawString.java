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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.graphics.font.MyFont;

/**
 *
 * @author user
 */
public class KeyValueDrawString {

    private final String LABEL;

    private final int labelWidth;
    private final int labelX;
    private int valueX;

    private String value = StringUtil.getInstance().EMPTY_STRING;

    public KeyValueDrawString(String label, int x)
    {
        this.LABEL = label;
        this.labelWidth = MyFont.getInstance().stringWidth(LABEL) - LABEL.length();
        this.labelX = x;
        this.valueX = this.labelWidth + x;
    }

    public void paint(final Graphics graphics, final int y)
    {
        graphics.drawString(this.LABEL,
            this.labelX, y, 0);

        graphics.drawString(this.value,
            this.valueX, y, 0);
    }

    public void update(String value)
    {
        if(value != null)
        this.value = value;
    }
}
