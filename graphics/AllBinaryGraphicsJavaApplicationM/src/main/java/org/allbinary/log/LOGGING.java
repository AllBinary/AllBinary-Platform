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
package org.allbinary.log;

import java.util.Vector;

public class LOGGING
{
   private String name;
   private static Vector logging = new Vector();
   
   private LOGGING(String value)
   {
      this.name = value;
      logging.add(value);
   }
   
   public static boolean contains(LOGGING logType)
   {
      return logging.contains(logType.toString());
   }
   
   public String toString()
   {
      return name;
   }
   
   //graphics
   public static final LOGGING GRAPHICSPAINT = new LOGGING("Graphics Paint");
   public static final LOGGING GRAPHICSCREATION = new LOGGING("Graphics Creation");
   public static final LOGGING GRAPHICSDELETION = new LOGGING("Graphics Deletion");
   
   public static final LOGGING LOADINGERROR = new LOGGING("Loading Error");
   
   //events
   public static final LOGGING MENUEVENT = new LOGGING("Menu Action Event");
   public static final LOGGING MOUSEEVENT = new LOGGING("Mouse Action Event");
   public static final LOGGING KEYEVENT = new LOGGING("Keyboard Action Event");
   public static final LOGGING ACTIONEVENT = new LOGGING("Action Event");
   public static final LOGGING ADJUSTMENTEVENT = new LOGGING("Adjustment Action Event");
   public static final LOGGING COMPONENTEVENT = new LOGGING("Component Action Event");
   public static final LOGGING CONTAINEREVENT = new LOGGING("Container Action Event");
   public static final LOGGING FOCUSEVENT = new LOGGING("Focus Action Event");
   public static final LOGGING HIERARCHYEVENT = new LOGGING("Hierarchy Action Event");
   public static final LOGGING INPUTEVENT = new LOGGING("Input Action Event");
   public static final LOGGING INVOCATIONEVENT = new LOGGING("Invocation Action Event");
   public static final LOGGING ITEMEVENT = new LOGGING("Item Action Event");
   public static final LOGGING PAINTEVENT = new LOGGING("Paint Action Event");
   public static final LOGGING TEXTEVENT = new LOGGING("Text Action Event");
   public static final LOGGING WINDOWEVENT = new LOGGING("Window Action Event");      
}
