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
package org.allbinary.animation.resource;

import java.util.Hashtable;

import org.allbinary.animation.BasicAnimationInterfaceFactoryInterface;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;

public class BaseResourceAnimationInterfaceFactoryInterfaceFactory
        implements FeatureResourceAnimationInterfaceFactoryInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final Hashtable hashtable;
    private final Hashtable rectangleHashtable;
    private final Hashtable rectangleArrayOfArraysHashtable;

    private final String name;

    private boolean initialized;

    public BaseResourceAnimationInterfaceFactoryInterfaceFactory(final String name)
    {
        this.hashtable = new Hashtable();
        this.rectangleHashtable = new Hashtable();
        this.rectangleArrayOfArraysHashtable = new Hashtable();
        this.name = name;
    }

    public BaseResourceAnimationInterfaceFactoryInterfaceFactory(final String name, final Hashtable hashtable, final Hashtable rectangleHashtable, final Hashtable rectangleArrayHashtable)
    {
        this.hashtable = hashtable;
        this.rectangleHashtable = rectangleHashtable;
        this.rectangleArrayOfArraysHashtable = rectangleArrayHashtable;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void init(final int level) throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        logUtil.put(
                new StringMaker().append("Available List of Animations: ").append(hashtable.toString()).toString(),
                this, commonStrings.INIT);

        this.setInitialized(true);
    }

    protected void init(final ImageCache imageCache, final int level) throws Exception
    {
    }

    public void add(
            final String resource,
            final BasicAnimationInterfaceFactoryInterface animationInterfaceFactoryInterface)
            throws Exception
    {
        //PreLogUtil.put(resource, this, commonStrings.ADD);

        if (hashtable.containsKey(resource))
        {
            throw new Exception(new StringMaker().append("Resource Already Created: ").append(resource).toString());
        }

        hashtable.put(resource, animationInterfaceFactoryInterface);
    }

    public BasicAnimationInterfaceFactoryInterface getBasicAnimationInterfaceFactoryInstance(
            final String resource) throws Exception
    {
        return (BasicAnimationInterfaceFactoryInterface) hashtable.get(resource);
    }

    public Rectangle getRectangle(final String resource) throws Exception
    {
        return (Rectangle) rectangleHashtable.get(resource);
    }

    public void addRectangle(final String resource, final Rectangle rectangle) throws Exception
    {
        rectangleHashtable.put(resource, rectangle);
    }

    public Rectangle[][] getRectangleArrayOfArrays(final String resource) throws Exception
    {
        return (Rectangle[][]) rectangleArrayOfArraysHashtable.get(resource);
    }

    public void addRectangleArrayOfArrays(final String resource, final Rectangle[][] rectangleArrayOfArrays) throws Exception
    {
        rectangleArrayOfArraysHashtable.put(resource, rectangleArrayOfArrays);
    }
    
    public boolean isFeature()
    {
        return false;
    }

    public boolean isLoadingLevel(final int level)
    {
        final ResourceLoadingLevelFactory resourceLoadingLevelFactory
                = ResourceLoadingLevelFactory.getInstance();

        if (level == resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public String toString()
    {
        return new StringMaker().append(this.getClass().getName()).append(CommonSeps.getInstance().SEMICOLON).append(CommonSeps.getInstance().SPACE).append(this.name).toString();
    }

    public Hashtable getHashtable()
    {
        return hashtable;
    }

    public Hashtable getRectangleHashtable()
    {
        return rectangleHashtable;
    }

    public Hashtable getRectangleArrayOfArraysHashtable()
    {
        return this.rectangleArrayOfArraysHashtable;
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
