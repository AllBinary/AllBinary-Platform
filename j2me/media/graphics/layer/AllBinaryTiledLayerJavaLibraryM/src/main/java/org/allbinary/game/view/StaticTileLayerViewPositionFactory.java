/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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

package org.allbinary.game.view;

import org.allbinary.view.ViewPosition;
import org.allbinary.view.ViewPositionFactoryInterface;

/**
 *
 * @author user
 */
public class StaticTileLayerViewPositionFactory implements ViewPositionFactoryInterface
{
   public ViewPosition getInstance() throws Exception
   {
      return new StaticTileLayerIntoPositionViewPosition();
   }
}
