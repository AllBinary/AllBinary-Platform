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
package org.allbinary.game.collision;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;

/**
 *
 * @author user
 */
public class CollidableHelperFactory {

   public static CollisionHelper getInstance()
   {
      if(Features.getInstance().isFeature(
              GameFeatureFactory.getInstance().COLLISIONS_WITH_SOURCE))
      {
         return new CollisionHelper(null);
      }
      else
      {
         return new SourceCollisionHelper(null);
      }
   }   
}
