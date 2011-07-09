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
package allbinary.game.input.event;

import allbinary.logic.basic.util.event.EventListenerInterface;

public interface DownGameKeyEventListenerInterface 
   extends EventListenerInterface
{
    void onDownGameKeyEvent(GameKeyEvent gameKeyEvent) throws Exception;
    
    //void onDownGameKey(GameKey gameKey) throws Exception;
}
