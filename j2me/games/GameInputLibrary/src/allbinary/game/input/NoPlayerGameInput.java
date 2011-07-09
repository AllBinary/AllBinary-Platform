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
package allbinary.game.input;

import org.allbinary.util.BasicArrayList;

import allbinary.game.input.event.GameKeyEvent;

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
      super(new BasicArrayList());
   }

   public synchronized void onDownGameKeyEvent(GameKeyEvent gameKeyEvent)
   {

   }

   public synchronized void onUpGameKeyEvent(GameKeyEvent gameKeyEvent)
   {
       
   }

}
