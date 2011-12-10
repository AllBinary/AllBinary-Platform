/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.morph;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerFactoryInterface;

/**
 *
 * @author user
 */
public class MorphLayerFactory implements AllBinaryLayerFactoryInterface
{
   public AllBinaryLayer getInstance() 
      throws Exception
   {
      return new MorphPaddleLayer();
   }
}
