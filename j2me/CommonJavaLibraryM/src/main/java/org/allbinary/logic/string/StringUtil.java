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
package org.allbinary.logic.string;

public class StringUtil
{
    private static final StringUtil instance = new StringUtil();
    
   public final String NULL_STRING = "null";
   public final String EMPTY_STRING = "";
   private final String[] stringArray = new String[0];

   private StringUtil()
   {
   }

   //Could Make a NulString
   public String getInstance(String string)
   {
      if(string == null)
      {
         return EMPTY_STRING;
      }
      else 
      {
         return string;
      }
   }

   public String[] getArrayInstance()
   {
      return stringArray;
   }

public static StringUtil getInstance()
{
    return instance;
}
}