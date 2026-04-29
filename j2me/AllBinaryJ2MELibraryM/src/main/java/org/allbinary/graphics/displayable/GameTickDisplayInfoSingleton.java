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
        return GameTickDisplayInfoSingleton.instance;
    }
    
    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    
    private int lastWidth = this.displayInfoSingleton.getLastWidth();
    private int lastHeight = this.displayInfoSingleton.getLastHeight();
    private int lastHalfWidth = this.displayInfoSingleton.getLastHalfWidth();
    private int lastHalfHeight = this.displayInfoSingleton.getLastHalfHeight();
    
    public void update() {
        this.lastWidth = this.displayInfoSingleton.getLastWidth();
        this.lastHeight = this.displayInfoSingleton.getLastHeight();
        this.lastHalfWidth = this.displayInfoSingleton.getLastHalfWidth();
        this.lastHalfHeight = this.displayInfoSingleton.getLastHalfHeight();        
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
