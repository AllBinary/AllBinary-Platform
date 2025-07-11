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

import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.java.character.CharArrayFactory;

/**
 *
 * @author user
 */
public class KeyValueDrawCharArray {

    private final String LABEL;

    private final int labelWidth;
    private final int labelX;
    private int valueX;

    private char[] value = CharArrayFactory.getInstance().getZeroCharArray();
    private int len;
    //private String value = StringUtil.getInstance();

    public KeyValueDrawCharArray(String label, int x)
    {
        this.LABEL = label;
        this.labelWidth = MyFont.getInstance().stringWidth(LABEL) - LABEL.length();
        this.labelX = x;
        this.valueX = this.labelWidth + x;
    }

    public void paint(final Graphics graphics, final int y)
    {
        graphics.drawString(this.LABEL, this.labelX, y, 0);

        graphics.drawChars(this.value, 0, this.len, this.valueX, y, 0);
    }

    public void update(char[] value, int len)
    {
        this.value = value;
        this.len = len;
    }
}
