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

import java.lang.reflect.Method;

import org.allbinary.logic.string.StringMaker;

public class MethodUtil
{
   private MethodUtil()
   {
   }
   
   public static String viewAll(Class myClass, String lineBreak)
   {
      StringMaker stringBuffer = new StringMaker();
      Method method[] = myClass.getMethods();
      stringBuffer.append("Methods: ");
      stringBuffer.append(lineBreak);
      for(int index = 0; index < method.length; index++)
      {
         stringBuffer.append(method[index].getReturnType().getName());
         stringBuffer.append(" ");
         stringBuffer.append(method[index].getName());
         stringBuffer.append("(");
         ParamsUtil.viewParams(method[index].getParameterTypes());
         stringBuffer.append(")");
         stringBuffer.append(lineBreak);
      }
      return stringBuffer.toString();
   }
}
