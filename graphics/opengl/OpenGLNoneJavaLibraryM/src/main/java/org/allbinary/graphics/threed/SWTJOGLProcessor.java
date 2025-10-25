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
package org.allbinary.graphics.threed;

import javax.microedition.midlet.MIDlet;

/**
 *
 * @author User
 */
//OpenGLNone
public class SWTJOGLProcessor implements Runnable {
    
    private static final SWTJOGLProcessor instance = new SWTJOGLProcessor();

    /**
     * @return the instance
     */
    public static SWTJOGLProcessor getInstance() {
        return instance;
    }

    public void init(final Object display, final Object comp) {
        
    }

    public boolean isHolderCreated() {
        return true;
    }

    public void setRenderer(final Object renderer) {

    }
    
    public boolean isJOGL() {
        return false;
    }
    
    public void createSurface() {
        
    }
    
    public void onSurfaceCreated() {
        
    }

    public void onSurfaceChanged(final Object event) {

    }
   
    public void onSurfaceChanged() {
        
    }
    
    public void onSurfaceChanged(final int width, final int height) {
        
    }
    
    public void addListener() {

    }

    public void addListeners(final MIDlet midlet) {
    
    }
    
    @Override
    public void run() {
                
    }

    public void clear() {
        
    }

    public void setCustom(final int width, final int height, final float ratio) {
    
    }

    public void setRatioProcessor() {
        
    }

    public boolean isPortraitRatioProcessor() {
        return true;
    }
    
}
