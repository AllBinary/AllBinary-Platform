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
package org.allbinary.image.opengles;

import javax.microedition.lcdui.Image;

/**
 *
 * @author User
 */
public class OpenGLESImageExclusionUtil {
    
    protected static final OpenGLESImageExclusionUtil instance = new OpenGLESImageExclusionUtil();


    /**
     * @return the instance
     */
    public static OpenGLESImageExclusionUtil getInstance() {
        return instance;
    }

    public boolean isCustomScaling(final Image image) {
        return false;
    }
    
}
