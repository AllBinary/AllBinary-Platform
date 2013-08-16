/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.health;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerFactoryInterface;

/**
 *
 * @author user
 */
public class HealLayerFactory implements AllBinaryLayerFactoryInterface
{
   public AllBinaryLayer getInstance() 
      throws Exception
   {
      return new HealLayer();
   }
}
