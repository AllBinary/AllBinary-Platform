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
package org.allbinary.view.event;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.util.event.EventStrings;

public class ViewPositionEventHandler extends BasicEventHandler
{
   private static ViewPositionEventHandler SINGLETON = new ViewPositionEventHandler();

   public static ViewPositionEventHandler getInstance()
   {
      return ViewPositionEventHandler.SINGLETON;
   }

   private final BasicArrayList list = new BasicArrayList();
   
   private ViewPositionEventHandler()
   {
   }

   public void addListener(AllBinaryLayer layerInterface)
   {
       if(!list.contains(layerInterface))
       {
           list.add(layerInterface);
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
               AllBinaryLayer layerInterface = (AllBinaryLayer) this.list.objectArray[index];
               layerInterface.onChangeEvent((ViewPositionEvent) eventObject);
           }
           catch (Exception e)
           {
               LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e));
           }
       }

       super.fireEvent(eventObject);
   }
   
   protected void process(AllBinaryEventObject eventObject,
           EventListenerInterface eventListenerInterface) throws Exception {

      ((ViewPositionEventListenerInterface) eventListenerInterface).onChangeEvent(
              (ViewPositionEvent) eventObject);
   }
   
}
