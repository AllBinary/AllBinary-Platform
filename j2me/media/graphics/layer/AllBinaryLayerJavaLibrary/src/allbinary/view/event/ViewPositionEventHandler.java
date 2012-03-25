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
package allbinary.view.event;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.Log;
import abcs.logic.communication.log.LogUtil;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.EventListenerInterface;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;

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
               LogUtil.put(new Log(CommonStrings.getInstance().EXCEPTION, this, "fireEvent", e));
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
