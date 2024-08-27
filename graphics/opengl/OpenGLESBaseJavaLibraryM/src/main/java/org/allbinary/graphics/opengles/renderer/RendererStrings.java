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
package org.allbinary.graphics.opengles.renderer;

/**
 *
 * @author User
 */
public class RendererStrings {
 
    private static final RendererStrings instance = new RendererStrings();

    /**
     * @return the instance
     */
    public static RendererStrings getInstance() {
        return instance;
    }
    
    public final String ON_SURFACE_CREATED = "onSurfaceCreated";
    public final String ON_SURFACE_CHANGED = "onSurfaceChanged";
    public final String ON_DRAW_FRAME = "onDrawFrame";
    public final String ON_DRAW_FRAME_THREED = "onDrawFrameThreed";
    public final String ON_DRAW_FRAME_2D = "onDrawFrame2d";
    
}
