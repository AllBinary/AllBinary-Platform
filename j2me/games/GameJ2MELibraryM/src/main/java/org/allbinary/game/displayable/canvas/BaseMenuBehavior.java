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

import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.paint.NullPaintable;

/**
 *
 * @author User
 */
public class BaseMenuBehavior {
    
    private static final BaseMenuBehavior instance = new BaseMenuBehavior();

    /**
     * @return the instance
     */
    public static BaseMenuBehavior getInstance() {
        return instance;
    }

    public void onDisplayChangeEvent(final AllBinaryGameCanvas allBinaryGameCanvas, final DisplayChangeEvent displayChangeEvent) throws Exception {
        
    }
    
    public void initSpecialPaint(final AllBinaryGameCanvas allBinaryGameCanvas) {
        allBinaryGameCanvas.setNonBotPaintableP(NullPaintable.getInstance());
    }

    public void initMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {

    }

    public void updateMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
    
    }

    public void popupMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        
    }
    
    public void closeMenu(final AllBinaryGameCanvas allBinaryGameCanvas) {
        
    }
}
