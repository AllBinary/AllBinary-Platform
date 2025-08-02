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

import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.NullUtil;
import org.allbinary.vector.VectorInfo;

public class IndependentVectorExplosionRotationAnimationFactory implements
        ProceduralAnimationInterfaceFactoryInterface
{
    private int[][][] framePoints = NullUtil.getInstance().NULL_INT_ARRAY_ARRAY_ARRAY;

    private VectorInfo vectorInfo;
    private BasicColor basicColor;

    private VectorAnimationFactoryInterface vectorAnimationFactoryInterface;
    
    public IndependentVectorExplosionRotationAnimationFactory(VectorInfo vectorInfo,
            BasicColor basicColor, 
            VectorAnimationFactoryInterface vectorAnimationFactoryInterface) 
            throws Exception
    {

        this.vectorInfo = vectorInfo;
        this.basicColor = basicColor;
        this.vectorAnimationFactoryInterface = vectorAnimationFactoryInterface;
        this.init();
    }

    protected void init() throws Exception
    {
        framePoints = VectorRotationGenerator.getInstance().getInstance(this.vectorInfo);
    }
    
    private final VectorExplosionGenerator vectorExplosionGenerator = VectorExplosionGenerator.getInstance();

    @Override    
    public Animation getInstance(Animation animationInterface)
            throws Exception
    {
        int frame = 0;
        if (animationInterface != null)
        {
            IndexedAnimation rotationAnimationInterface = (IndexedAnimation) animationInterface;
            frame = rotationAnimationInterface.getFrame();
        }
        int[][][] points = vectorExplosionGenerator.getInstance(
                framePoints[frame], 6, vectorExplosionGenerator.ROTATION);

        return this.vectorAnimationFactoryInterface.getInstance(points, this.basicColor);
    }
}