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
package org.allbinary.canvas;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;

/**
 *
 * @author User
 */
public class GameGlobalsFactory {

    private static final GameGlobalsFactory instance = new GameGlobalsFactory();

    /**
     * @return the instance
     */
    public static GameGlobalsFactory getInstance() {
        return instance;
    }

    public boolean newCanvas = true;
    public long newDisplaybleTime = (long) Integer.MIN_VALUE;
    public GPoint point = PointFactory.getInstance().ZERO_ZERO;
}
