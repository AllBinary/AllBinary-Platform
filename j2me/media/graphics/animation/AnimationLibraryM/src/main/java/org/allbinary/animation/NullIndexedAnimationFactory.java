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
package org.allbinary.animation;

import javax.microedition.lcdui.Graphics;

import org.allbinary.media.ScaleProperties;

public class NullIndexedAnimationFactory implements
    AnimationInterfaceFactoryInterface, ProceduralAnimationInterfaceFactoryInterface {

    public static final IndexedAnimationInterface[] NULL_INDEXED_ANIMATION_ARRAY = new IndexedAnimationInterface[0];
    private static NullIndexedAnimationFactory NULL_INDEXED_ANIMATION_FACTORY = new NullIndexedAnimationFactory();
    
    final class NullIndexedAnimationInner extends NullIndexedAnimation {

        NullIndexedAnimationInner() {
            super(new IndexedAnimationBehavior(1, 250));
        }

        @Override
        public void paintXY(final Graphics graphics, final int x, final int y) {
        }
    };
    
    private final Animation NULL_ANIMATION = new NullIndexedAnimationInner();

    private NullIndexedAnimationFactory() {
    }

    public static NullIndexedAnimationFactory getFactoryInstance() {
        return NullIndexedAnimationFactory.NULL_INDEXED_ANIMATION_FACTORY;
    }

    @Override
    public Animation getInstance(final int instanceId) throws Exception {
        return this.NULL_ANIMATION;
    }

    @Override
    public Animation getInstanceAnimation(final Animation animationInterface)
        throws Exception {
        return this.NULL_ANIMATION;
    }

    @Override
    public void setInitialScale(final ScaleProperties scaleProperties) {

    }

}
