/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.points;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerFactoryInterface;

/**
 *
 * @author user
 */
public class PointsLayerFactory 
implements AllBinaryLayerFactoryInterface
{
	private final int points;
	
	public PointsLayerFactory(int points)
	{
		this.points = points;
	}
	
   public AllBinaryLayer getInstance() 
      throws Exception
   {
      return new PointsLayer(points);
   }
}
