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
package org.allbinary.game.input;

import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class NoPlayerGameInput extends PlayerGameInput
{
    private static final NoPlayerGameInput SINGLETON = new NoPlayerGameInput();

    public static NoPlayerGameInput getInstance()
    {
        return SINGLETON;
    }
    
   private NoPlayerGameInput()
   {
      super(new BasicArrayList(), -1);
   }

   @Override
   public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
   {

   }

   @Override
   public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       
   }

}
