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
package org.allbinary.animation;

/**
 *
 * @author User
 */
public class AnimationBehavior {

    private static final AnimationBehavior instance = new AnimationBehavior();

    /**
     * @return the instance
     */
    public static AnimationBehavior getInstance() {
        return instance;
    }

    public void reset() {
    }
    
}
