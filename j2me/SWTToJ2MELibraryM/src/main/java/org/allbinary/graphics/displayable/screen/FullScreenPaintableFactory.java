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
package org.allbinary.graphics.displayable.screen;

import org.allbinary.game.paint.FullScreenPaintable;
import org.allbinary.graphics.paint.Paintable;

/**
 *
 * @author User
 */
public class FullScreenPaintableFactory {

    private static final FullScreenPaintableFactory instance = new FullScreenPaintableFactory();
    
    /**
     * @return the instance
     */
    public static FullScreenPaintableFactory getInstance() {
        return instance;
    }
    
    public Paintable paintable = FullScreenPaintable.getInstance();
}
