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
import org.allbinary.graphics.font.FontDebugFactory;

/**
 *
 * @author User
 */
public class CustomTextAnimation extends TextAnimation
{
    private final FontDebugFactory fontDebugFactory = FontDebugFactory.getInstance();
    
    private final Font font;
    
    public CustomTextAnimation(final String text, final Font font)
    {
        super(text);
        
        this.font = font;
    }
    
    public void paint(final Graphics graphics, final int x, final int y)
    {
        final Font existingFont = graphics.getFont();
        
        fontDebugFactory.setFont(this.font, graphics);
        
        super.paint(graphics, x, y);
        
        fontDebugFactory.setFont(existingFont, graphics);
    }
  
    public int getWidth() {
        return this.font.stringWidth(this.text);
    }
}
