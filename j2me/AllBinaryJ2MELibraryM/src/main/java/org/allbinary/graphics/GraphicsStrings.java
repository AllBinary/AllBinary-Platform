/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.graphics;

/**
 *
 * @author User
 */
public class GraphicsStrings {
    
    private static final GraphicsStrings instance = new GraphicsStrings();

    /**
     * @return the instance
     */
    public static GraphicsStrings getInstance() {
        return instance;
    }
    
    public final String ANIMATION = "animation";
    public final String ANGLE = "angle";
    public final String MOVEMENT_ANGLE = "movement_angle";
    public final String ROTATION = "rotation";
    public final String OPACITY = "opacity";

    public final String HTML = "HTML";
    
}
