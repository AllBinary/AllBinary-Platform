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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.animation.AnimationInterfaceFactoryInterface;
import org.allbinary.animation.VectorAnimation;

import org.allbinary.game.configuration.GameConfigurationCentral;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.image.AnimationFrameToImageUtil;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.vector.VectorInfo;
import org.allbinary.media.image.ImageToRotationImageArrayUtil;

public class VectorToImageArrayRotationAnimationFactory 
    extends BaseImageAnimationFactory
{
    private Image[] imageArray;

    private int angleIncrement;

    public VectorToImageArrayRotationAnimationFactory(final VectorInfo vectorInfo, 
        final BasicColor basicColor, final int dx, final int dy)
        throws Exception {
        this(vectorInfo, basicColor, dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public VectorToImageArrayRotationAnimationFactory(final VectorInfo vectorInfo, 
        final BasicColor basicColor, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        this(vectorInfo, basicColor, animationBehaviorFactory);

        this.dx = dx;
        this.dy = dy;
    }

    public VectorToImageArrayRotationAnimationFactory(
        final VectorInfo vectorInfo, final BasicColor basicColor) throws Exception
    {
        this(vectorInfo, basicColor, AnimationBehaviorFactory.getInstance());
    }
    
    public VectorToImageArrayRotationAnimationFactory(
        final VectorInfo vectorInfo, final BasicColor basicColor, final AnimationBehaviorFactory animationBehaviorFactory) throws Exception
    {
        super(AnimationFrameToImageUtil.getInstance().getInstanceTranslate(
                vectorInfo.getWidth(), vectorInfo.getHeight(), 
                new VectorAnimation(vectorInfo.getPoints(), basicColor, animationBehaviorFactory.getOrCreateInstance())),
            0, 0, animationBehaviorFactory);

        this.init();
    }

    protected void init() throws Exception
    {
        final AngleFactory angleFactory = AngleFactory.getInstance();
        
        this.angleIncrement = angleFactory.TOTAL_ANGLE / GameConfigurationCentral.getInstance().getGameControlFidelity();

        this.imageArray = ImageToRotationImageArrayUtil.getInstance().generate(
                this.getImage(), this.getAngleIncrement(), angleFactory.TOTAL_ANGLE);
    }

    public Animation getInstance() throws Exception
    {
        if (dx != 0 || dy != 0) {
            return new AdjustedImageArrayRotationAnimation(
                this.imageArray, AngleInfo.getInstance((short) this.getAngleIncrement()),
                AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
        } else {
            //This still offsets.
            return new AdjustedImageArrayRotationAnimation(
                this.imageArray, AngleInfo.getInstance((short) this.angleIncrement),
                AngleFactory.getInstance().TOTAL_ANGLE, this.animationBehaviorFactory.getOrCreateInstance());
        }
    }

    protected int getAngleIncrement()
    {
        return angleIncrement;
    }
    
    public void setInitialScale(final ScaleProperties scaleProperties) {
        
    }
    
}