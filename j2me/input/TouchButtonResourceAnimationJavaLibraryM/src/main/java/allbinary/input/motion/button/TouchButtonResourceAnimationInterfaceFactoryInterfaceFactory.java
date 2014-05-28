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

import abcs.logic.system.os.OperatingSystemFactory;
import allbinary.animation.NullAnimationFactory;
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

public class TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory 
    extends BaseResourceAnimationInterfaceFactoryInterfaceFactory
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
        
        Image touchButtonBlankResourceImage = imageCache.get(TouchButtonBlankResource.getInstance().RESOURCE);
        Image touchButtonGenericActionResource = imageCache.get(TouchButtonGenericActionResource.getInstance().RESOURCE);
        Image touchButtonStartResource = imageCache.get(TouchButtonStartResource.getInstance().RESOURCE);
        Image touchButtonUpResource = imageCache.get(TouchButtonUpResource.getInstance().RESOURCE);
        Image touchButtonDownResource = imageCache.get(TouchButtonDownResource.getInstance().RESOURCE);
        Image touchButtonTurnLeftResource = imageCache.get(TouchButtonTurnLeftResource.getInstance().RESOURCE);
        Image touchButtonTurnRightResource = imageCache.get(TouchButtonTurnRightResource.getInstance().RESOURCE);
        Image touchButtonStrafeLeftResource = imageCache.get(TouchButtonStrafeLeftResource.getInstance().RESOURCE);
        Image touchButtonStrafeRightResource = imageCache.get(TouchButtonStrafeRightResource.getInstance().RESOURCE);
        
        if(OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            imageCache.get(TouchButtonStartResource.getInstance().HINT);
        }

        progressCanvas.addPortion(portion, loadingString, index++);

        ImageCompleteUtil imageCompleteUtil = ImageCompleteUtil.getInstance();
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonBlankResourceImage);
        
        this.add(TouchButtonBlankResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonBlankResourceImage));

        this.add(TouchButtonBlankResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonGenericActionResource);
        
        this.add(TouchButtonGenericActionResource.getInstance().RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonGenericActionResource));

        this.add(TouchButtonGenericActionResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStartResource);
        
        this.add(TouchButtonStartResource.getInstance().RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStartResource));
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonUpResource);
        
        this.add(TouchButtonUpResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonUpResource));

        this.add(TouchButtonUpResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonDownResource);

        this.add(TouchButtonDownResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonDownResource));

        this.add(TouchButtonDownResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnLeftResource);

        this.add(TouchButtonTurnLeftResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonTurnLeftResource));

        this.add(TouchButtonTurnLeftResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnRightResource);

        this.add(TouchButtonTurnRightResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonTurnRightResource));

        this.add(TouchButtonTurnRightResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeLeftResource);
        
        this.add(TouchButtonStrafeLeftResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStrafeLeftResource));

        this.add(TouchButtonStrafeLeftResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeRightResource);

        this.add(TouchButtonStrafeRightResource.getInstance().RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                touchButtonStrafeRightResource));

        this.add(TouchButtonStrafeRightResource.getInstance().HINT, 
                NullAnimationFactory.getFactoryInstance());
        
        if(OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            Image touchButtonStartHintResource = imageCache.get(TouchButtonStartResource.getInstance().HINT);            

            this.add(TouchButtonStartResource.getInstance().HINT, 
                    new OneRowSpriteIndexedAnimationFactory(
                            touchButtonStartHintResource));
        }
        else
        {
            this.add(TouchButtonStartResource.getInstance().HINT, 
                    NullAnimationFactory.getFactoryInstance());
        }
        
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
