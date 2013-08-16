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
package org.allbinary.game.testgamedemo.layer;

import allbinary.game.GameInfo;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.graphics.color.BasicColorFactory;

public class TestGameDemoLayerManager extends AllBinaryGameLayerManager
{
   //private PlayerLayer playerLayer;
   
   public static int MAX_LEVEL = 1; //Integer.MAX_VALUE;
   
   public TestGameDemoLayerManager(GameInfo gameInfo)
   {
      super(BasicColorFactory.getInstance().AQUA, BasicColorFactory.getInstance().YELLOW, gameInfo);
   }

   /*
   public PlayerLayer getPlayerLayer() {
      return playerLayer;
   }
    */

   /*
   public void setPlayerLayer(PlayerLayer playerLayer) {
      this.playerLayer = playerLayer;
   }
   */
}
