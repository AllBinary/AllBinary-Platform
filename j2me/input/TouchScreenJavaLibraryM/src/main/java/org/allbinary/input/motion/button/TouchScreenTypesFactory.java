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
package org.allbinary.input.motion.button;

/**
 *
 * @author User
 */
public class TouchScreenTypesFactory {
    
    private static final TouchScreenTypesFactory instance = new TouchScreenTypesFactory();

    /**
     * @return the instance
     */
    public static TouchScreenTypesFactory getInstance() {
        return instance;
    }
    
    public final TouchScreenType FINGER = new TouchScreenType("Finger");
    
    public final TouchScreenType NOTOUCH = new TouchScreenType("No Touch");
    
    public final TouchScreenType STYLUS = new TouchScreenType("Stylus");

    public final TouchScreenType UNDEFINED = new TouchScreenType("Undefined");
    
}
