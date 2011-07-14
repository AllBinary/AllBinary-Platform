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
package allbinary.graphics.j2me.workarea.canvas.event;

public class MyCanvasEventService
{
   private static javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();
      
   public static String DELETE = "delete";
   public static String DUPLICATE = "duplicate";
   public static String SELECT = "select";
   public static String ROTATE = "rotate";
   public static String EXPLODE = "explode";
   public static String AUTOEXPLODE = "autoExplode";
   public static String CENTER = "center";
   
   //private static String CUT = "cut";
   //private static String PASTE = "paste";

   private MyCanvasEventService()
   {      
   }
    
   public static synchronized void addListener(MyCanvasEventListener listener) 
   {      
      listenerList.add((Class) listener.getClass(), (java.util.EventListener) listener);
   }

   public static synchronized void removeListener(MyCanvasEventListener listener) 
   {    
      listenerList.remove((Class) listener.getClass(), (java.util.EventListener) listener);
   }
       
   public static synchronized void fire(MyCanvasEvent evt) 
           throws Exception
   {
      Object[] listeners = listenerList.getListenerList();
      
      for (int i=0; i<listeners.length; i+=2) 
      {         
         if (listeners[i]==listeners[i+1].getClass()) 
         {
            if(evt.getCommand().compareTo(DELETE)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).delete(evt);
            }
            else
            if(evt.getCommand().compareTo(DUPLICATE)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).duplicate(evt);
            }
            else
            if(evt.getCommand().compareTo(SELECT)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).select(evt);
            }
            else
            if(evt.getCommand().compareTo(ROTATE)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).rotate(evt);
            }
            else
            if(evt.getCommand().compareTo(EXPLODE)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).explode(evt);
            }
            else
            if(evt.getCommand().compareTo(AUTOEXPLODE)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).autoExplode(evt);
            }
            else
            if(evt.getCommand().compareTo(CENTER)==0)
            {
               ((MyCanvasEventListener)listeners[i+1]).center(evt);
            }
         }
      }
   }   
}
