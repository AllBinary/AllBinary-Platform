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

        this.add(TouchButtonBlankResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonBlankResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);
        
        this.add(TouchButtonGenericActionResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        imageCache.get(TouchButtonGenericActionResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);
        
        this.add(TouchButtonStartResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        imageCache.get(TouchButtonStartResource.RESOURCE)));
        
        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonUpResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonUpResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonDownResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonDownResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonTurnLeftResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonTurnLeftResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonTurnRightResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonTurnRightResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonStrafeLeftResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonStrafeLeftResource.RESOURCE)));

        progressCanvas.addPortion(portion, loadingString, index++);

        this.add(TouchButtonStrafeRightResource.RESOURCE, 
                new OneRowSpriteIndexedAnimationFactory(
                imageCache.get(TouchButtonStrafeRightResource.RESOURCE)));
        
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
