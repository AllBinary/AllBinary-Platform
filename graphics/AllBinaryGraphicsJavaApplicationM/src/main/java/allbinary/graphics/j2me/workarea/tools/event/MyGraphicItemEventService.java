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
package allbinary.graphics.j2me.workarea.tools.event;

public class MyGraphicItemEventService
{
   private static javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();
      
   public static String DELETE = "delete";
   public static String DESELECT = "deselect";
   public static String SELECT = "select";
   public static String DUPLICATE = "duplicate";
   public static String ROTATE = "rotate";
   //private static String CUT = "cut";
   //private static String PASTE = "paste";

   private MyGraphicItemEventService()
   {      
   }
    
   public static synchronized void addListener(MyGraphicItemEventListener listener) 
   {
      //MyGraphicItemEventListener.class
      listenerList.add((Class) listener.getClass(), (java.util.EventListener) listener);
   }

   public static synchronized void removeListener(MyGraphicItemEventListener listener) 
   {
      //MyGraphicItemEventListener.class
      listenerList.remove((Class) listener.getClass(), (java.util.EventListener) listener);
   }
       
   public static synchronized void fire(MyGraphicItemEvent evt) 
           throws Exception
   {
      Object[] listeners = listenerList.getListenerList();
      
      for (int i=0; i<listeners.length; i+=2) 
      {
         //MyGraphicItemEventListener.class
         if (listeners[i]==listeners[i+1].getClass()) 
         {
            if(evt.getCommand().compareTo(SELECT)==0)
            {
               ((MyGraphicItemEventListener)listeners[i+1]).highlight(evt);
            }
            else
            if(evt.getCommand().compareTo(DESELECT)==0)
            {
               ((MyGraphicItemEventListener)listeners[i+1]).deselect(evt);
            }
            else
            if(evt.getCommand().compareTo(DELETE)==0)
            {
               ((MyGraphicItemEventListener)listeners[i+1]).delete(evt);
            }
            else
            if(evt.getCommand().compareTo(DUPLICATE)==0)
            {
               ((MyGraphicItemEventListener)listeners[i+1]).duplicate(evt);
            }               
            else
            if(evt.getCommand().compareTo(ROTATE)==0)
            {
               ((MyGraphicItemEventListener)listeners[i+1]).rotate(evt);
            }               
         }
      }
   }   
}
