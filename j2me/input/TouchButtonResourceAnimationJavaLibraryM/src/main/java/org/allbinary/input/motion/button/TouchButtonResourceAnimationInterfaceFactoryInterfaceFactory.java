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

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.animation.image.sprite.OneRowSpriteIndexedAnimationFactory;
import org.allbinary.animation.resource.BaseResourceAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.media.image.ImageCompleteUtil;

public class TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory
        extends BaseResourceAnimationInterfaceFactoryInterfaceFactory
{

    public static TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory create()
    {
        return new TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory("TouchButton Animations");
    }

    public TouchButtonResourceAnimationInterfaceFactoryInterfaceFactory(final String name)
    {
        super(name, new Hashtable(), new Hashtable(), new Hashtable());
    }

    @Override
    public void init(final int level) throws Exception
    {
        this.initImageCache(ImageCacheFactory.getInstance(), level);
    }

    @Override
    protected void initImageCache(final ImageCache imageCache, final int level)
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

        final ProgressCanvas progressCanvas = ProgressCanvasFactory.getInstance();

        progressCanvas.addPortion(portion, loadingString, index++);

        final TouchButtonResource touchButtonBlankResource = TouchButtonBlankResource.getInstance();
        final TouchButtonResource touchButtonGenericActionResource = TouchButtonGenericActionResource.getInstance();
        final TouchButtonResource touchButtonStartResource = TouchButtonStartResource.getInstance();
        final TouchButtonResource touchButtonUpResource = TouchButtonUpResource.getInstance();
        final TouchButtonResource touchButtonDownResource = TouchButtonDownResource.getInstance();
        final TouchButtonResource touchButtonTurnLeftResource = TouchButtonTurnLeftResource.getInstance();
        final TouchButtonResource touchButtonTurnRightResource = TouchButtonTurnRightResource.getInstance();
        final TouchButtonResource touchButtonStrafeLeftResource = TouchButtonStrafeLeftResource.getInstance();
        final TouchButtonResource touchButtonStrafeRightResource = TouchButtonStrafeRightResource.getInstance();

        final Image touchButtonBlankResourceImage = imageCache.getWithKey(touchButtonBlankResource.RESOURCE);
        final Image touchButtonGenericActionResourceImage = imageCache.getWithKey(touchButtonGenericActionResource.RESOURCE);
        final Image touchButtonStartResourceImage = imageCache.getWithKey(touchButtonStartResource.RESOURCE);
        final Image touchButtonUpResourceImage = imageCache.getWithKey(touchButtonUpResource.RESOURCE);
        final Image touchButtonDownResourceImage = imageCache.getWithKey(touchButtonDownResource.RESOURCE);
        final Image touchButtonTurnLeftResourceImage = imageCache.getWithKey(touchButtonTurnLeftResource.RESOURCE);
        final Image touchButtonTurnRightResourceImage = imageCache.getWithKey(touchButtonTurnRightResource.RESOURCE);
        final Image touchButtonStrafeLeftResourceImage = imageCache.getWithKey(touchButtonStrafeLeftResource.RESOURCE);
        final Image touchButtonStrafeRightResourceImage = imageCache.getWithKey(touchButtonStrafeRightResource.RESOURCE);

        final NullAnimationFactory nullAnimationFactory = NullAnimationFactory.getFactoryInstance();

        if (OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            imageCache.getWithKey(TouchButtonStartResource.getInstance().HINT);
        }

        progressCanvas.addPortion(portion, loadingString, index++);

        final ImageCompleteUtil imageCompleteUtil = ImageCompleteUtil.getInstance();

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonBlankResourceImage, touchButtonBlankResource.RESOURCE);

        this.add(touchButtonBlankResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonBlankResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonBlankResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonGenericActionResourceImage, touchButtonGenericActionResource.RESOURCE);

        this.add(touchButtonGenericActionResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonGenericActionResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonGenericActionResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStartResourceImage, touchButtonStartResource.RESOURCE);

        this.add(touchButtonStartResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonStartResourceImage, AnimationBehaviorFactory.getInstance()));

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonUpResourceImage, touchButtonUpResource.RESOURCE);

        this.add(touchButtonUpResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonUpResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonUpResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonDownResourceImage, touchButtonDownResource.RESOURCE);

        this.add(touchButtonDownResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonDownResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonDownResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnLeftResourceImage, touchButtonTurnLeftResource.RESOURCE);

        this.add(touchButtonTurnLeftResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonTurnLeftResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonTurnLeftResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonTurnRightResourceImage, touchButtonTurnRightResource.RESOURCE);

        this.add(touchButtonTurnRightResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonTurnRightResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonTurnRightResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeLeftResourceImage, touchButtonStrafeLeftResource.RESOURCE);

        this.add(touchButtonStrafeLeftResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonStrafeLeftResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonStrafeLeftResource.HINT, nullAnimationFactory);

        progressCanvas.addPortion(portion, loadingString, index++);

        imageCompleteUtil.waitFor(touchButtonStrafeRightResourceImage, touchButtonStrafeRightResource.RESOURCE);

        this.add(touchButtonStrafeRightResource.RESOURCE,
                OneRowSpriteIndexedAnimationFactory.create(
                        touchButtonStrafeRightResourceImage, AnimationBehaviorFactory.getInstance()));

        this.add(touchButtonStrafeRightResource.HINT, nullAnimationFactory);

        if (OperatingSystemFactory.getInstance().getOperatingSystemInstance().isOverScan())
        {
            final Image touchButtonStartHintResource = imageCache.getWithKey(TouchButtonStartResource.getInstance().HINT);

            this.add(touchButtonStartResource.HINT,
                    OneRowSpriteIndexedAnimationFactory.create(
                            touchButtonStartHintResource, AnimationBehaviorFactory.getInstance()));
        } else
        {
            this.add(touchButtonStartResource.HINT, nullAnimationFactory);
        }

        super.init(level);
    }

    @Override
    public boolean isLoadingLevel(final int level)
    {
        final ResourceLoadingLevelFactory resourceLoadingLevelFactory
                = ResourceLoadingLevelFactory.getInstance();

        if (level == resourceLoadingLevelFactory.LOAD_TOUCH.getLevel())
        {
            return true;
        } else
        {
            return super.isLoadingLevel(level);
        }
    }

    @Override
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
