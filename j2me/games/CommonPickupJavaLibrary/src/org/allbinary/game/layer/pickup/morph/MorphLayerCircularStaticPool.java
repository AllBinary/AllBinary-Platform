/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.game.layer.pickup.morph;

import org.allbinary.animation.ThreedAnimation;

import allbinary.animation.AnimationInterfaceCompositeInterface;
import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerCircularPool;
import allbinary.layer.AllBinaryLayerFactoryInterface;

public class MorphLayerCircularStaticPool extends AllBinaryLayerCircularPool {
	public MorphLayerCircularStaticPool(
			AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface,
			int total) throws Exception {
		super.init(allbinaryLayerFactoryInterface, total);
	}

	public void visit(AllBinaryLayer sourceLayerInterface) throws Exception {
		
		AnimationInterfaceCompositeInterface animationInterfaceCompositeInterface = 
				(AnimationInterfaceCompositeInterface) sourceLayerInterface;
		
		ThreedAnimation threedAnimation = (ThreedAnimation) animationInterfaceCompositeInterface.getAnimationInterface();
		
		MorphPaddleLayer morphPaddleLayer = (MorphPaddleLayer) this.getNextInstance();
		
		morphPaddleLayer.visit((AllBinaryGameLayer) sourceLayerInterface, threedAnimation);
	}
}
