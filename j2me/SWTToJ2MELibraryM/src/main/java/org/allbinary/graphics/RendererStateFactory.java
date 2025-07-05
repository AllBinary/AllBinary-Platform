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
package org.allbinary.graphics;

public class RendererStateFactory
{
    private static final RendererStateFactory instance = new RendererStateFactory();

    public static RendererStateFactory getInstance()
    {
        return instance;
    }
    
    private boolean loaded = false;
    private boolean surfaceChanged = false;
    
    public void setLoaded(boolean loaded)
    {
        this.loaded = loaded;
    }

    public boolean isLoaded()
    {
        return loaded;
    }
    
    public void setSurfaceChanged(boolean initialized)
    {
        surfaceChanged = initialized;
    }

    public boolean isSurfaceChanged()
    {
        return surfaceChanged;
    }
}
