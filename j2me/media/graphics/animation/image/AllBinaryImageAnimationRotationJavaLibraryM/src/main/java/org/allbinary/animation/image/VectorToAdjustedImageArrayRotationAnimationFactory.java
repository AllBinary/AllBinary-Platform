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

import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehaviorFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.math.AngleFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.vector.VectorInfo;

public class VectorToAdjustedImageArrayRotationAnimationFactory extends
    VectorToImageArrayRotationAnimationFactory {

    private int dx;
    private int dy;
    private final AnimationBehaviorFactory animationBehaviorFactory;

    public VectorToAdjustedImageArrayRotationAnimationFactory(final VectorInfo vectorInfo, 
        final BasicColor basicColor, final int dx, final int dy)
        throws Exception {
        this(vectorInfo, basicColor, dx, dy, AnimationBehaviorFactory.getInstance());
    }
    
    public VectorToAdjustedImageArrayRotationAnimationFactory(final VectorInfo vectorInfo, 
        final BasicColor basicColor, final int dx, final int dy, final AnimationBehaviorFactory animationBehaviorFactory)
        throws Exception {

        super(vectorInfo, basicColor);

        this.animationBehaviorFactory = animationBehaviorFactory;
        this.dx = dx;
        this.dy = dy;

        this.init();
    }

    public Animation getInstance() throws Exception {
        //return new AllBinarySpriteRotationAnimation(new MESprite(image, width, height), dx, dy);

        return new AdjustedImageArrayRotationAnimation(
            this.getImageArray(), AngleInfo.getInstance((short) this.getAngleIncrement()),
            AngleFactory.getInstance().TOTAL_ANGLE, dx, dy, this.animationBehaviorFactory.getOrCreateInstance());
    }

}
