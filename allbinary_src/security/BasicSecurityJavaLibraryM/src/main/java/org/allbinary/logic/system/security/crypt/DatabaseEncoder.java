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
package org.allbinary.logic.system.security.crypt;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.tokens.Tokenizer;
import org.allbinary.logic.communication.log.PreLogUtil;

public class DatabaseEncoder
{
   
   private DatabaseEncoder()
   {
   }
   
   public static String encode(byte[] value)
   {
      try
      {
         byte[] array = value;
         final StringMaker stringBuffer = new StringMaker();
         
         for(int index =0; index < array.length; index++)
         {            
            stringBuffer.append(new Byte(array[index]).toString());
            if(index < array.length-1)
            {
                stringBuffer.append(CommonSeps.getInstance().SPACE);
            }
         }
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, "DatabaseEncoder", "decode", e);
         //}
         return null;
      }
   }
   
   public static byte[] decode(String value)
   {
      try
      {
         Tokenizer tokenizer = new Tokenizer(CommonSeps.getInstance().SPACE);
         BasicArrayList vector = tokenizer.getTokens(value, new BasicArrayList());
         BasicArrayList byteVector = new BasicArrayList();
         int size = vector.size();
                  
         for(int index = 0; index< size; index++)
         {
            String byteOfData = (String) vector.objectArray[index];
            byteVector.add(new Byte(byteOfData));
         }

         byte[] decode = new byte[byteVector.size()];
         int decodeIndex = 0;

         for(int index = 0; index < size; index++)
         {
            Byte aByte = (Byte) byteVector.objectArray[index];            
            decode[decodeIndex] = aByte.byteValue();
            decodeIndex++;
         }
         return decode;
      }
      catch(Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().CRYPTERROR))
         //{
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, "DatabaseEncoder", "decode", e);
         //}
         return null;
      }
   }
}
