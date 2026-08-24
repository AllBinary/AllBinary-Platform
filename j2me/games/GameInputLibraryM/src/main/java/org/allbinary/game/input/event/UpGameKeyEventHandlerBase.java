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
package org.allbinary.game.input.event;

import jsinterop.annotations.JsType;

import org.allbinary.game.input.PlayerGameInput;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventListenerInterface;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.logic.util.event.handler.BasicEventHandler;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class UpGameKeyEventHandlerBase extends BasicEventHandler
{

   private final BasicArrayList list = new BasicArrayListD();
   
   @JsConstructor
   UpGameKeyEventHandlerBase()
   {
   }
   
   @JsMethod
   public void addListener(PlayerGameInput playerGameInput)
   {
       if(!this.list.contains(playerGameInput))
       {
           this.list.add(playerGameInput);
       }
   }

   @Override
   @JsMethod
   public void removeAllListeners()
   {
       this.list.clear();
       super.removeAllListeners();
   }

   @Override
   @JsMethod
   public void removeListenerSingleThreaded(
            final EventListenerInterface eventListenerInterface)
    {
        this.list.remove(eventListenerInterface);
        super.removeListenerSingleThreaded(eventListenerInterface);
    }
    
    @Override
    @JsMethod
    public synchronized void removeListener(final EventListenerInterface eventListenerInterface)
   {
       this.list.remove(eventListenerInterface);
       super.removeListener(eventListenerInterface);
   }

   @Override
   @JsMethod
   public void fireEvent(final AllBinaryEventObject eventObject) throws Exception
   {        
       for (int index = this.list.size(); --index >= 0;)
       {
           try
           {
        	 //Add deviceId
               PlayerGameInput playerGameInput = (PlayerGameInput) this.list.objectArray[index];
               playerGameInput.onUpGameKeyEvent((GameKeyEvent) eventObject);
           }
           catch (Exception e)
           {
               this.logUtil.put(this.commonStrings.EXCEPTION, this, EventStrings.getInstance().FIRE_EVENT, e);
           }
       }

       super.fireEvent(eventObject);
   }
   
   @Override
   @JsMethod
   protected void process(final AllBinaryEventObject eventObject,
           final EventListenerInterface eventListenerInterface) throws Exception {

      final UpGameKeyEventListenerInterface upGameKeyEventListenerInterface = (UpGameKeyEventListenerInterface) eventListenerInterface;
      upGameKeyEventListenerInterface.onUpGameKeyEvent((GameKeyEvent) eventObject);

   }
   

    private static final String TOTAL_LISTENERS = " Total PlayerGameInput Listeners: ";
    private static final String LISTENER_LABEL = " PlayerGameInput Listener: ";
    
    @JsMethod
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();

        final int size = this.list.size();
        
        stringBuffer.append(super.toString());
        stringBuffer.append(UpGameKeyEventHandlerBase.TOTAL_LISTENERS);
        stringBuffer.appendint(size);

        for (int index = 0; index < size; index++)
        {
            try
            {
                EventListenerInterface eventListenerInterface = (EventListenerInterface) // enumeration.nextElement();
                    this.list.get(index);

                stringBuffer.append(UpGameKeyEventHandlerBase.LISTENER_LABEL);
                stringBuffer.append(eventListenerInterface.toString());
            }
            catch (Exception e)
            {
                this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.TOSTRING, e);
            }
        }
        return stringBuffer.toString();
    }   
}
