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

import java.lang.reflect.Constructor;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonSeps;


public class ConstructorUtil
{
   private ConstructorUtil()
   {
   }

   public static String viewAll(final Class myClass, final String lineBreak)
   {
      final StringMaker stringBuffer = new StringMaker();
      final Constructor[] constructor = myClass.getConstructors();
      stringBuffer.append("Constructors: ");
      stringBuffer.append(lineBreak);
      for(int index = 0; index < constructor.length; index++)
      {
         stringBuffer.append(ConstructorUtil.view(constructor[index], lineBreak));
      }
      return stringBuffer.toString();
   }
   
   public static String view(final Constructor constructor, final String lineBreak)
   {
      if(constructor != null)
      {
         final CommonSeps commonSeps = CommonSeps.getInstance();
         final StringMaker stringBuffer = new StringMaker();
         stringBuffer.append(constructor.getName());
         final Class[] classes = constructor.getParameterTypes();
         for(int index = 0; index < classes.length; index++)
         {
            stringBuffer.append(commonSeps.SPACE);
            stringBuffer.append(classes[index].getName());
            stringBuffer.append(commonSeps.COLON);
            stringBuffer.append(index);
         }
         stringBuffer.append(lineBreak);
         return stringBuffer.toString();
      }
      else return "Constructor Null";
   }   
}
