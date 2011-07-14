/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package allbinary.animation.resource;

import java.util.Hashtable;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.animation.BasicAnimationInterfaceFactoryInterface;
import allbinary.graphics.Rectangle;
import allbinary.image.ImageCache;

public class BaseResourceAnimationInterfaceFactoryInterfaceFactory 
    implements FeatureResourceAnimationInterfaceFactoryInterface
{
    private final Hashtable hashtable = new Hashtable();
    private final Hashtable rectangleHashtable = new Hashtable();

    private final String name;
    
    private boolean initialized;

    public BaseResourceAnimationInterfaceFactoryInterfaceFactory(String name)
    {
        this.name = name;
    }

    public void init(int level) throws Exception
    {
        LogUtil.put(LogFactory.getInstance(
                "Available List of Animations: " + hashtable.toString(),
                this, CommonStrings.getInstance().INIT));

        this.setInitialized(true);
    }
    
    protected void init(ImageCache imageCache, int level) throws Exception
    {
    }
        
    public void add(
            String resource,
            BasicAnimationInterfaceFactoryInterface animationInterfaceFactoryInterface)
    throws Exception
    {
        if(hashtable.containsKey(resource))
        {
            throw new Exception("Resource Already Created: " + resource);
        }
        
        hashtable.put(resource, animationInterfaceFactoryInterface);
    }

    public BasicAnimationInterfaceFactoryInterface getBasicAnimationInterfaceFactoryInstance(
            String resource) throws Exception
    {
        return (BasicAnimationInterfaceFactoryInterface) hashtable.get(resource);
    }

    public Rectangle getRectangle(String resource) throws Exception
    {
        return (Rectangle) rectangleHashtable.get(resource);
    }

    public void addRectangle(String resource, Rectangle rectangle) throws Exception
    {
        rectangleHashtable.put(resource, rectangle);
    }

    public boolean isFeature()
    {
        return false;
    }

    public boolean isLoadingLevel(int level)
    {   
        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        if(level == resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String toString()
    {
        return this.name;
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }

    protected void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
