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

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;

import org.allbinary.animation.BasicAnimationInterfaceFactoryInterface;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.RectangleFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.ABHashtable;

@JsType
public class BaseResourceAnimationInterfaceFactoryInterfaceFactory
        implements FeatureResourceAnimationInterfaceFactoryInterface
{

    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

    @JsProperty
    protected final CommonStrings commonStrings = CommonStrings.getInstance();

    private final ABHashtable hashtable;
    private final ABHashtable rectangleHashtable;
    private final ABHashtable rectangleArrayOfArraysHashtable;

    private final String name;

    private boolean initialized;

    @JsConstructor
    public BaseResourceAnimationInterfaceFactoryInterfaceFactory(final String name, final ABHashtable hashtable, final ABHashtable rectangleHashtable, final ABHashtable rectangleArrayHashtable)
    {
        this.hashtable = hashtable;
        this.rectangleHashtable = rectangleHashtable;
        this.rectangleArrayOfArraysHashtable = rectangleArrayHashtable;
        this.name = name;
    }
    
    @JsMethod
    public String getName() {
        return this.name;
    }
    
    @Override
    @JsMethod
    public void init(final int level) throws Exception
    {
        final CommonStrings commonStrings = CommonStrings.getInstance();
        this.logUtil.putF(new StringMaker().append("Available List of Animations: ").append(this.hashtable.toString()).toString(), this, commonStrings.INIT);

        this.setInitialized(true);
    }

    @JsMethod
    protected void initImageCache(final ImageCache imageCache, final int level) throws Exception
    {
    }

    @JsMethod
    public void add(
            final String resource,
            final BasicAnimationInterfaceFactoryInterface animationInterfaceFactoryInterface)
            throws Exception
    {
        //PreLogUtil.put(resource, this, this.commonStrings.ADD);

        if (this.hashtable.containsKey((Object) resource))
        {
            throw new Exception(new StringMaker().append("Resource Already Created: ").append(resource).toString());
        }

        this.hashtable.put(resource, animationInterfaceFactoryInterface);
    }

    @Override
    @JsMethod
    public BasicAnimationInterfaceFactoryInterface getBasicAnimationInterfaceFactoryInstance(final String resource) throws Exception
    {
        final Object basicAnimationInterfaceFactoryInterfaceCanBeNull = this.hashtable.get(resource);
        
        if(basicAnimationInterfaceFactoryInterfaceCanBeNull == null) {
            //this.logUtil.putF(new StringMaker().append("No Resource for: ").append(resource).toString(), this, "getBasicAnimationInterfaceFactoryInstance");
            return NullAnimationFactory.NULL_NOT_FOR_USE_ANIMATION_FACTORY;
        }
        
        return (BasicAnimationInterfaceFactoryInterface) basicAnimationInterfaceFactoryInterfaceCanBeNull;
    }

    @Override
    @JsMethod
    public Rectangle getRectangle(final String resource) throws Exception
    {
        final Object rectangleCanBeNull = this.rectangleHashtable.get(resource);
        
        if(rectangleCanBeNull == null) {
            return RectangleFactory.SINGLETON;
        }
        
        return (Rectangle) rectangleCanBeNull;
    }

    @JsMethod
    public void addRectangle(final String resource, final Rectangle rectangle) throws Exception
    {
        this.rectangleHashtable.put(resource, rectangle);
    }

    @Override
    @JsMethod
    public Rectangle[][] getRectangleArrayOfArrays(final String resource) throws Exception
    {
        return (Rectangle[][]) this.rectangleArrayOfArraysHashtable.get(resource);
    }

    @JsMethod
    public void addRectangleArrayOfArrays(final String resource, final Rectangle[][] rectangleArrayOfArrays) throws Exception
    {
        this.rectangleArrayOfArraysHashtable.put(resource, rectangleArrayOfArrays);
    }

    @Override    
    @JsMethod
    public boolean isFeature()
    {
        return false;
    }

    @Override
    @JsMethod
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

    @JsMethod
    public String toString()
    {
        return new StringMaker().append(this.getClass().getName()).append(CommonSeps.getInstance().SEMICOLON).append(CommonSeps.getInstance().SPACE).append(this.name).toString();
    }

    @Override
    @JsMethod
    public ABHashtable getHashtable()
    {
        return this.hashtable;
    }

    @JsMethod
    public ABHashtable getRectangleHashtable()
    {
        return this.rectangleHashtable;
    }

    @JsMethod
    public ABHashtable getRectangleArrayOfArraysHashtable()
    {
        return this.rectangleArrayOfArraysHashtable;
    }

    @JsMethod
    protected void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    @JsMethod
    public boolean isInitialized()
    {
        return this.initialized;
    }
}
