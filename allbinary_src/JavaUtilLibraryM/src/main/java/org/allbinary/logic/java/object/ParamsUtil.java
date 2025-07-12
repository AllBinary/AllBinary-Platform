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

public class ParamsUtil
{
   private ParamsUtil()
   {
   }

   public static String viewParams(Class params[])
   {
      if(params!=null)
      {
         StringMaker stringBuffer = new StringMaker();
         for(int index = 0; index < params.length; index++)
         {
            stringBuffer.append(" ");
            stringBuffer.append(params[index].getName());
         }         
         return stringBuffer.toString();
      }
      else return "Params Null";
   }
   
}
