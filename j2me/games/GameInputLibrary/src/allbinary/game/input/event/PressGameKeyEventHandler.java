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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.PlayerGameInput;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

public class PressGameKeyEventHandler extends BasicEventHandler
{
   private static final PressGameKeyEventHandler instance = new PressGameKeyEventHandler();

   public static PressGameKeyEventHandler getInstance()
   {
      return PressGameKeyEventHandler.instance;
   }

   private final BasicArrayList list = new BasicArrayList();
   
   private PressGameKeyEventHandler()
   {
   }
   
   public void addListener(PlayerGameInput playerGameInput)
   {
       if(!list.contains(playerGameInput))
       {
           list.add(playerGameInput);
       }
   }

   public void removeAllListeners()
   {
       this.list.clear();
       super.removeAllListeners();
   }

   public void removeListener(EventListenerInterface eventListenerInterface)
   {
       this.list.remove(eventListenerInterface);
       super.removeListener(eventListenerInterface);
   }

   public void fireEvent(AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
               PlayerGameInput playerGameInput = (PlayerGameInput) this.list.get(index);
               playerGameInput.onPressGameKeyEvent((GameKeyEvent) eventObject);
           }
           catch (Exception e)
           {
               LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
           }
       }

       super.fireEvent(eventObject);
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((PressGameKeyEventListenerInterface) eventListenerInterface).onPressGameKeyEvent(
              (GameKeyEvent) eventObject);
   }
   
}
