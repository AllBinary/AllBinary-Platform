/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Canvas;

/**
 *
 * @author User
 */
public class RepaintBehavior {

    private static final RepaintBehavior instance = new RepaintBehavior();
    
    /**
     * @return the instance
     */
    public static RepaintBehavior getInstance() {
        return instance;
    }
    
    public void repaint(final Canvas canvas) {
        
    }

    public void onChangeRepaint(final Canvas canvas) {
        canvas.repaint();
    }
    
}
