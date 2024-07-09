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
