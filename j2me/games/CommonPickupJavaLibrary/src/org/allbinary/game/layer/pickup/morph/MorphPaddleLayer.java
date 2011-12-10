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
 *Date: Oct 7, 2007, 11:31:14 AM
 *
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.game.layer.pickup.morph;

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.ThreedAnimation;
import org.allbinary.animation.morphing.AdjustableThreedMorphingAnimation;
import org.allbinary.game.neonpuck.layer.PlayerLayer;

import allbinary.game.layer.AllBinaryGameLayer;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.layer.AllBinaryLayer;
import allbinary.view.ViewPosition;

public class MorphPaddleLayer extends AllBinaryLayer
{
   public MorphPaddleLayer()
      throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), new ViewPosition());
   }
   
   public void paint(Graphics graphics)
   {
   }
   
   public void visit(AllBinaryGameLayer sourceLayerInterface, ThreedAnimation threedAnimation)
   {
	   //PreLogUtil.put("Morph Paddle", this, "visit");
	   
	   PadResources padResources = PadResources.getInstance();
	   
	   	if(threedAnimation.getCurrentAnimationName() == padResources.RED_SMALL_ANIMATION)
	   	{
	   		threedAnimation.setAnimation(padResources.RED_LARGE_ANIMATION);
	   		sourceLayerInterface.setWidth(PlayerLayer.WIDTH + 20);
	   		//((AdjustableThreedMorphingAnimation) threedAnimation).getDeltaPostion().x = 45; 
	   	}
	   	else
	   		if(threedAnimation.getCurrentAnimationName() == padResources.RED_LARGE_ANIMATION)
	   	{
	   			threedAnimation.setAnimation(padResources.RED_SMALL_ANIMATION);
	   			sourceLayerInterface.setWidth(PlayerLayer.WIDTH);
	   	}
	   	else
	   		if(threedAnimation.getCurrentAnimationName() == padResources.BLUE_SMALL_ANIMATION)
	   	{
	   		threedAnimation.setAnimation(padResources.BLUE_LARGE_ANIMATION);
	   		sourceLayerInterface.setWidth(PlayerLayer.WIDTH + 20);
	   	}
	   	else
	   	{
	   		threedAnimation.setAnimation(padResources.BLUE_SMALL_ANIMATION);
	   		sourceLayerInterface.setWidth(PlayerLayer.WIDTH);
	   	}
   }
}
