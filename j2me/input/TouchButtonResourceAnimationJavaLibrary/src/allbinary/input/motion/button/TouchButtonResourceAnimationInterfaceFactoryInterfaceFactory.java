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
package allbinary.input.motion.button;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import allbinary.animation.image.sprite.OneRowSpriteIndexedAnimationFactory;
import allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.configuration.feature.Features;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.image.ImageCache;
import allbinary.image.ImageCacheFactory;
import javax.microedition.lcdui.Image;

import org.allbinary.animation.image.ImageCompleteUtil;

public class TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory extends
        BaseResourceAnimationInterfaceFactoryInterfaceFactory
{
    public TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory()
    {
        super("TouchButton Animations");
    }

    public TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory(String name)
    {
        super(name);
    }
    
    public void init(int level) throws Exception
    {        
        this.init(ImageCacheFactory.getInstance(), level);
    }

    protected void init(ImageCache imageCache, int level) 
        throws Exception
    {
        if(this.isInitialized())
        {
            return;
        }
        
    // public static int add(
    // BaseResourceAnimationInterfaceFactoryInterfaceFactory
    // baseResourceAnimationInterfaceFactoryInterfaceFactory, int portion)
    // throws Exception
        final int portion = 140;

        // final String loadingString =
        // baseResourceAnimationInterfaceFactoryInterfaceFactory.toString() +
        // " Loading: ";
        final String loadingString = this.toString() + " Loading: ";

        int index = 0;

        ProgressCanvas progressCanvas = 
            ProgressCanvasFactory.getInstance();
        
        progressCanvas.addPortion(portion, loadingString, index++);
        
        Image touchButtonBlankResourceImage = imageCache.get(TouchButtonBlankResource.RESOURCE);
        Image touchButtonGenericActionResource = imageCache.get(TouchButtonGenericActionResource.RESOURCE);
        Image touchButtonStartResource = imageCache.get(TouchButtonStartResource.RESOURCE);
        Image touchButtonUpResource = imageCache.get(TouchButtonUpResource.RESOURCE);
        Image touchButtonDownResource = imageCache.get(TouchButtonDownResource.RESOURCE);
        Image touchButtonTurnLeftResource = imageCache.get(TouchButtonTurnLeftResource.RESOURCE);
        Image touchButtonTurnRightResource = imageCache.get(TouchButtonTurnRightResource.RESOURCE);
        Image touchButtonStrafeLeftResource = imageCache.get(TouchButtonStrafeLeftResource.RESOURCE);
        Image touchButtonStrafeRightResource = imageCache.get(TouchButtonStrafeRightResource.RESOURCE);

        progressCanvas.addPortion(portion, loadingString, index++);

        ImageCompleteUtil imageCompleteUtil = ImageCompleteUtil.getInstance();
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonBlankResourceImage);
        
        this.add(TouchButtonBlankResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonBlankResourceImage));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonGenericActionResource);
        
        this.add(TouchButtonGenericActionResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonGenericActionResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStartResource);
        
        this.add(TouchButtonStartResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStartResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonUpResource);
        
        this.add(TouchButtonUpResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonUpResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonDownResource);

        this.add(TouchButtonDownResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonDownResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnLeftResource);

        this.add(TouchButtonTurnLeftResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonTurnLeftResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnRightResource);

        this.add(TouchButtonTurnRightResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonTurnRightResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeLeftResource);
        
        this.add(TouchButtonStrafeLeftResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStrafeLeftResource));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeRightResource);

        this.add(TouchButtonStrafeRightResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStrafeRightResource));

        super.init(level);
    }

    public boolean isLoadingLevel(int level)
    {
        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        if(level == resourceLoadingLevelFactory.LOAD_TOUCH.getLevel())
        {
            return true;
        }
        else
        {
            return super.isLoadingLevel(level);
        }
    }
    
    public boolean isFeature()
    {
        if (!Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            return true;
        } else
        {
            return false;
        }
    }

}
