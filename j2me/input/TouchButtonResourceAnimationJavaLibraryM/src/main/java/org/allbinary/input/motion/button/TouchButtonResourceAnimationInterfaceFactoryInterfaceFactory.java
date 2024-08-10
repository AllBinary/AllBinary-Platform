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
package org.allbinary.input.motion.button;

import javax.microedition.lcdui.Image;

import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.animation.image.sprite.OneRowSpriteIndexedAnimationFactory;
import org.allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageCompleteUtil;

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
        if (this.isInitialized())
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
        final String loadingString = new StringMaker().append(this.toString()).append(" Loading: ").toString();

        int index = 0;

        ProgressCanvas progressCanvas
                = ProgressCanvasFactory.getInstance();

        progressCanvas.addPortion(portion, loadingString, index++);

        TouchButtonResource touchButtonBlankResource = TouchButtonBlankResource.getInstance();
        TouchButtonResource touchButtonGenericActionResource = TouchButtonGenericActionResource.getInstance();
        TouchButtonResource touchButtonStartResource = TouchButtonStartResource.getInstance();
        TouchButtonResource touchButtonUpResource = TouchButtonUpResource.getInstance();
        TouchButtonResource touchButtonDownResource = TouchButtonDownResource.getInstance();
        TouchButtonResource touchButtonTurnLeftResource = TouchButtonTurnLeftResource.getInstance();
        TouchButtonResource touchButtonTurnRightResource = TouchButtonTurnRightResource.getInstance();
        TouchButtonResource touchButtonStrafeLeftResource = TouchButtonStrafeLeftResource.getInstance();
        TouchButtonResource touchButtonStrafeRightResource = TouchButtonStrafeRightResource.getInstance();

        Image touchButtonBlankResourceImage = imageCache.get(touchButtonBlankResource.RESOURCE);
        Image touchButtonGenericActionResourceImage = imageCache.get(touchButtonGenericActionResource.RESOURCE);
        Image touchButtonStartResourceImage = imageCache.get(touchButtonStartResource.RESOURCE);
        Image touchButtonUpResourceImage = imageCache.get(touchButtonUpResource.RESOURCE);
        Image touchButtonDownResourceImage = imageCache.get(touchButtonDownResource.RESOURCE);
        Image touchButtonTurnLeftResourceImage = imageCache.get(touchButtonTurnLeftResource.RESOURCE);
        Image touchButtonTurnRightResourceImage = imageCache.get(touchButtonTurnRightResource.RESOURCE);
        Image touchButtonStrafeLeftResourceImage = imageCache.get(touchButtonStrafeLeftResource.RESOURCE);
        Image touchButtonStrafeRightResourceImage = imageCache.get(touchButtonStrafeRightResource.RESOURCE);

        NullAnimationFactory nullAnimationFactory
                = NullAnimationFactory.getFactoryInstance();

        if (OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            imageCache.get(TouchButtonStartResource.getInstance().HINT);
        }

        progressCanvas.addPortion(portion, loadingString, index++);

        ImageCompleteUtil imageCompleteUtil = ImageCompleteUtil.getInstance();

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonBlankResourceImage, touchButtonBlankResource.RESOURCE);

        this.add(touchButtonBlankResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonBlankResourceImage));

        this.add(touchButtonBlankResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonGenericActionResourceImage, touchButtonGenericActionResource.RESOURCE);

        this.add(touchButtonGenericActionResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonGenericActionResourceImage));

        this.add(touchButtonGenericActionResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStartResourceImage, touchButtonStartResource.RESOURCE);

        this.add(touchButtonStartResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonStartResourceImage));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonUpResourceImage, touchButtonUpResource.RESOURCE);

        this.add(touchButtonUpResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonUpResourceImage));

        this.add(touchButtonUpResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonDownResourceImage, touchButtonDownResource.RESOURCE);

        this.add(touchButtonDownResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonDownResourceImage));

        this.add(touchButtonDownResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnLeftResourceImage, touchButtonTurnLeftResource.RESOURCE);

        this.add(touchButtonTurnLeftResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonTurnLeftResourceImage));

        this.add(touchButtonTurnLeftResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnRightResourceImage, touchButtonTurnRightResource.RESOURCE);

        this.add(touchButtonTurnRightResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonTurnRightResourceImage));

        this.add(touchButtonTurnRightResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeLeftResourceImage, touchButtonStrafeLeftResource.RESOURCE);

        this.add(touchButtonStrafeLeftResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonStrafeLeftResourceImage));

        this.add(touchButtonStrafeLeftResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeRightResourceImage, touchButtonStrafeRightResource.RESOURCE);

        this.add(touchButtonStrafeRightResource.RESOURCE,
                new OneRowSpriteIndexedAnimationFactory(
                        touchButtonStrafeRightResourceImage));

        this.add(touchButtonStrafeRightResource.HINT, nullAnimationFactory);

        if (OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            Image touchButtonStartHintResource = imageCache.get(TouchButtonStartResource.getInstance().HINT);

            this.add(touchButtonStartResource.HINT,
                    new OneRowSpriteIndexedAnimationFactory(
                            touchButtonStartHintResource));
        } else
        {
            this.add(touchButtonStartResource.HINT, nullAnimationFactory);
        }

        super.init(level);
    }

    public boolean isLoadingLevel(int level)
    {
        ResourceLoadingLevelFactory resourceLoadingLevelFactory
                = ResourceLoadingLevelFactory.getInstance();

        if (level == resourceLoadingLevelFactory.LOAD_TOUCH.getLevel())
        {
            return true;
        } else
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
