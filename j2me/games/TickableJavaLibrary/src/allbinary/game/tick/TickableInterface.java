/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 12, 2007, 11:09:16 PM
 *
 *
 * Modified By         When       ?
 *
 */
package allbinary.game.tick;

import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.NamedInterface;

public interface TickableInterface 
extends NamedInterface
{
   void processTick(AllBinaryLayerManager allBinaryLayerManager) 
       throws Exception;
}
