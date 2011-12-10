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

package org.allbinary.game.layer.pickup.points;

import allbinary.game.score.ScoreableInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerCircularPool;
import allbinary.layer.AllBinaryLayerFactoryInterface;

public class PointsLayerCircularStaticPool extends AllBinaryLayerCircularPool {
	public PointsLayerCircularStaticPool(
			AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface,
			int total) throws Exception {
		super.init(allbinaryLayerFactoryInterface, total);
	}

	public void visit(AllBinaryLayer sourceLayerInterface) throws Exception {

		PointsLayer pointsLayer = (PointsLayer) this.getNextInstance();
		
		ScoreableInterface scoreableInterface = (ScoreableInterface) sourceLayerInterface;
		
		pointsLayer.visit(scoreableInterface);
	}
}
