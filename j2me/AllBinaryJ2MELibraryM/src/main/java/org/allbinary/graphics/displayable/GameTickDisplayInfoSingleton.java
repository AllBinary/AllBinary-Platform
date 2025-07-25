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
    
    private int lastWidth = displayInfoSingleton.getLastWidth();
    private int lastHeight = displayInfoSingleton.getLastHeight();
    private int lastHalfWidth = displayInfoSingleton.getLastHalfWidth();
    private int lastHalfHeight = displayInfoSingleton.getLastHalfHeight();
    
    public void update() {
        this.lastWidth = displayInfoSingleton.getLastWidth();
        this.lastHeight = displayInfoSingleton.getLastHeight();
        this.lastHalfWidth = displayInfoSingleton.getLastHalfWidth();
        this.lastHalfHeight = displayInfoSingleton.getLastHalfHeight();        
    }
    
    public int getLastWidth() {
        return this.lastWidth;
    }

    public int getLastHeight() {
        return this.lastHeight;
    }
    
    public int getLastHalfWidth() {
        return this.lastHalfWidth;
    }

    public int getLastHalfHeight() {
        return this.lastHalfHeight;
    }

}
