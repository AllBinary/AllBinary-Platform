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
package org.allbinary.game.layer.weapon.mine;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerFactoryInterface;
import org.allbinary.view.ViewPositionFactoryInterface;

public class MineLayerFactory implements AllBinaryLayerFactoryInterface {

   private ViewPositionFactoryInterface viewPositionFactoryInterface;
   
   public MineLayerFactory(ViewPositionFactoryInterface viewPositionFactoryInterface)
   {
      this.viewPositionFactoryInterface = viewPositionFactoryInterface;
   }
   
   @Override
    public AllBinaryLayer getInstance() throws Exception {
        return new MineLayer(this.viewPositionFactoryInterface.getInstance());
    }
    
}
