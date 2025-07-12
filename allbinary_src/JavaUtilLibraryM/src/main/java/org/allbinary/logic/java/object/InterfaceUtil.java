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
package org.allbinary.logic.java.object;

import org.allbinary.logic.string.StringMaker;

public class InterfaceUtil
{
   private InterfaceUtil()
   {
   }

   public static String viewAll(final Class myClass, final String lineBreak)
   {
      final StringMaker stringBuffer = new StringMaker();

      final Class[] interfaces = myClass.getInterfaces();

      stringBuffer.append(lineBreak);
      stringBuffer.append("Interfaces: ");
      stringBuffer.append(lineBreak);

      for(int index = 0; index < interfaces.length; index++)
      {
         stringBuffer.append("Interface: ");
         stringBuffer.append(interfaces[index].getName());
         stringBuffer.append(lineBreak);
      }

      return stringBuffer.toString();
   }

   public static Class getClass(final String interfaceName, final Class interfaces[])
   {
      if(interfaceName != null && interfaces!=null && interfaces.length>0)
      {
         for(int index =0; index<interfaces.length; index++)
         {
            if(interfaces[index].getName().indexOf(interfaceName)>=0)
            {               
               return interfaces[index];
            }
         }   
      }
      return null;
   }
   
   public static boolean isImplemented(Class clazz, Object object)
   {
      Class classes[] = object.getClass().getInterfaces();
      
      for(int index = 0; index < classes.length; index++)
      {
         if(clazz.getName().compareTo(classes[index].getName()) == 0)
            return true;
      }
      return false;
   }

   public static boolean isImplemented(String className, Object object)
   {
      Class classes[] = object.getClass().getInterfaces();
      
      for(int index = 0; index < classes.length; index++)
      {
         if(classes[index].getName().indexOf(className) == 0)
         {
             return true;
         }
      }
      return false;
   }

   public static String isImplementedView(Class clazz, Object object)
   {
      StringMaker stringBuffer = new StringMaker();

      Class classes[] = object.getClass().getInterfaces();

      stringBuffer.append("isImplementedView: \n");

      for(int index = 0; index < classes.length; index++)
      {
         stringBuffer.append(clazz.getName());
         stringBuffer.append(" should be = ");
         stringBuffer.append(classes[index].getName());
         stringBuffer.append(classes[index].getName());
         stringBuffer.append("\n");
      }

      return stringBuffer.toString();
   }
}
