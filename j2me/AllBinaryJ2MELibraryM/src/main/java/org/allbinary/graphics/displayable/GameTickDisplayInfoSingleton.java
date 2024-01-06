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
package org.allbinary.graphics.displayable;

/**
 *
 * @author User
 */
public class GameTickDisplayInfoSingleton {
    
    private static final GameTickDisplayInfoSingleton instance = new GameTickDisplayInfoSingleton();

    /**
     * @return the instance
     */
    public static GameTickDisplayInfoSingleton getInstance() {
        return instance;
    }
    
    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    
    public int lastWidth = displayInfoSingleton.getLastWidth();
    public int lastHeight = displayInfoSingleton.getLastHeight();
    
    public void update() {
        this.lastWidth = displayInfoSingleton.getLastWidth();
        this.lastHeight = displayInfoSingleton.getLastHeight();
    }
}
