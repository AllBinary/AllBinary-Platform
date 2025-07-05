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
package org.allbinary.device;

/**
 *
 * @author User
 */
public class OpenGLESGraphicsCompositeFactory {
    
    private static final OpenGLESGraphicsCompositeFactory instance = new OpenGLESGraphicsCompositeFactory();

    /**
     * @return the instance
     */
    public static OpenGLESGraphicsCompositeFactory getInstance()
    {
        return instance;
    }
    
    private OpenGLESGraphicsFactory openGLESGraphicsFactory = new OpenGLESGraphicsFactory();
    
    public void set(OpenGLESGraphicsFactory openGLESGraphicsFactory)
    {
        this.openGLESGraphicsFactory = openGLESGraphicsFactory;
    }
    
    public OpenGLESGraphicsFactory get() {
        return this.openGLESGraphicsFactory;
    }
}
