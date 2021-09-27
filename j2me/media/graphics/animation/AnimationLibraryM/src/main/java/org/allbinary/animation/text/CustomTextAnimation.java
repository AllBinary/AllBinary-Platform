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
package org.allbinary.animation.text;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author User
 */
public class CustomTextAnimation extends TextAnimation
{
    private final Font font;
    
    public CustomTextAnimation(final String text, final Font font)
    {
        super(text);
        
        this.font = font;
    }
    
    public void paint(Graphics graphics, int x, int y)
    {
        final Font existingFont = graphics.getFont();
        
        graphics.setFont(this.font);
        
        super.paint(graphics, x, y);
        
        graphics.setFont(existingFont);
    }
  
    public int getWidth() {
        return this.font.stringWidth(this.text);
    }
}
