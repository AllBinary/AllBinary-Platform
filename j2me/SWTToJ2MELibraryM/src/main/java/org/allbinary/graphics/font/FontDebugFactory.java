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
package org.allbinary.graphics.font;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author User
 */
public class FontDebugFactory {
    
    private static final FontDebugFactory instance = new FontDebugFactory();

    /**
     * @return the instance
     */
    public static FontDebugFactory getInstance() {
        return instance;
    }
        
    public void setFont(final Font font, final Graphics graphics) {
        graphics.setFont(font);
    }
}
