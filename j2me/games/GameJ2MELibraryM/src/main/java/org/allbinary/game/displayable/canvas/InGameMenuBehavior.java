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

/**
 *
 * @author User
 */
public class InGameMenuBehavior extends BaseMenuBehavior {
    
    private static final InGameMenuBehavior instance = new InGameMenuBehavior();

    /**
     * @return the instance
     */
    public static InGameMenuBehavior getInstance() {
        return instance;
    }    
    
    @Override
    public void onDisplayChangeEvent(final AllBinaryGameCanvas allBinaryGameCanvas, final DisplayChangeEvent displayChangeEvent) throws Exception {
        allBinaryGameCanvas.updateMenu(displayChangeEvent);
    }

    @Override
    public void initSpecialPaint(final AllBinaryGameCanvas allBinaryGameCanvas) {
        allBinaryGameCanvas.setNonBotPaintableP(new GameCanvasNonBotPaintable(allBinaryGameCanvas));
    }
    
    @Override
    public void initMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.initMenu2();
    }

    @Override
    public void updateMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.initMenu2();
    }
    
    @Override
    public void popupMenu(final AllBinaryGameCanvas allBinaryGameCanvas) throws Exception {
        allBinaryGameCanvas.popupMenu2();
    }
    
    @Override
    public void closeMenu(final AllBinaryGameCanvas allBinaryGameCanvas) {
        allBinaryGameCanvas.closeMenu2();
    }
}
